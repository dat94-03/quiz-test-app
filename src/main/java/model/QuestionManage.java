package model;

import javaFx.bundle.view.QuestionBankTree;
import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionManage {
    String dataPath = "src/main/java/data/QuizTestAppData.xlsx";
    public static ArrayList<Question> questionsList = new ArrayList<>();
    XSSFWorkbook data;
    Sheet questionBank;
    Sheet categories;


    public QuestionManage() throws IOException {
        FileInputStream readDataStream = new FileInputStream(dataPath);
        data = new XSSFWorkbook(readDataStream);
        questionBank = data.getSheet("QuestionBank");
        this.loadQuestion();
        readDataStream.close();

    }

    //reload question to questionList every time change the database
    public void loadQuestion() {
        questionsList.clear();
        int id = 0;
        for (Row questionData : questionBank) {
            Question question = new Question();
            question.id = id;
            question.title = String.valueOf(questionData.getCell(0));
            question.category = String.valueOf((questionData.getCell(1)));
            question.correctAnswer = String.valueOf((questionData.getCell(2)));
            String[] choices = String.valueOf(questionData.getCell(3)).split("\n");
            question.choices.addAll(Arrays.asList(choices));
            questionsList.add(question);
            id++;

        }
    }

    //remove single question when we get id of question
    public void deleteQuestion(int questionId, String questionCategory) throws IOException {
        FileOutputStream writeDataStream = new FileOutputStream(dataPath);
        int lastRowNum = questionBank.getLastRowNum();
        updateCategory(questionCategory, -1);
        if (questionId >= 0 && questionId < lastRowNum) {
            questionBank.shiftRows(questionId + 1, lastRowNum, -1);
        }
        if (questionId == lastRowNum) {
            Row removingRow = questionBank.getRow(questionId);
            if (removingRow != null) {
                questionBank.removeRow(removingRow);
            }
        }
        data.write(writeDataStream);

        writeDataStream.close();
        loadQuestion();
    }

    //add single questions
    public void addQuestion(Question q) throws IOException {
        StringBuilder choices = new StringBuilder();
        int insertingRow = questionBank.getLastRowNum() + 1;
        FileOutputStream writeDataStream = new FileOutputStream(dataPath);
        Row newQuestion = questionBank.createRow(insertingRow);
        newQuestion.createCell(0).setCellValue(q.title);
        newQuestion.createCell(1).setCellValue(q.category);
        newQuestion.createCell(2).setCellValue(q.correctAnswer);
        for (String choice : q.choices) {
            choices.append(choice).append("\n");
        }
        newQuestion.createCell(3).setCellValue(choices.toString());
        data.write(writeDataStream);
        updateCategory(q.category, 1);
        writeDataStream.close();
        loadQuestion();
    }

    //edit question with given question id and edited Question object
    public void editQuestion(Question editedQuestion) throws IOException {
        FileInputStream fis = new FileInputStream(dataPath);
        XSSFWorkbook data = new XSSFWorkbook(fis);
        Sheet questionBank = data.getSheet("QuestionBank");
        Row editingQuestion = questionBank.getRow(editedQuestion.id);
        editingQuestion.getCell(0).setCellValue(editedQuestion.title);
        String oldCategory = editingQuestion.getCell(1).getStringCellValue();
        editingQuestion.getCell(1).setCellValue(editedQuestion.category);

        editingQuestion.getCell(2).setCellValue(editedQuestion.correctAnswer);
        StringBuilder choices = new StringBuilder();
        for(String choice :editedQuestion.choices){
            choices.append(choice).append("\n");
        }
        choices.delete(choices.length()-1,choices.length() +1);
        editingQuestion.getCell(3).setCellValue(choices.toString());
        fis.close();

        FileOutputStream fos = new FileOutputStream(dataPath);
        data.write(fos);
        fos.close();

        updateCategory(oldCategory,-1);
        updateCategory(editedQuestion.category,1);
        loadQuestion();
    }

    //import a file of questions in .docx format
    //return number of question have imported if the format is corrSect, otherwise return -1
    public void importQuestions(String importingPath, String category) throws IOException {
        if (checkAikenFormat(importingPath)) {
            FileInputStream fis = new FileInputStream(dataPath);
            XSSFWorkbook data = new XSSFWorkbook(fis);
            Sheet questionBank = data.getSheet("QuestionBank");
            int startInsertingRow = questionBank.getLastRowNum() +1;
            fis.close();

            int insertingRow = startInsertingRow;
            
            FileInputStream importingFileStream = new FileInputStream(importingPath);
            XWPFDocument importingData = new XWPFDocument(importingFileStream);

            StringBuilder choices = new StringBuilder();
            Row newQuestion = questionBank.createRow(startInsertingRow +1);
            String pattern = "^[A-Z](\\.)";
            Pattern regex = Pattern.compile(pattern);

            for (XWPFParagraph paragraph : importingData.getParagraphs()) {
                String line = paragraph.getText();
                line = line.trim();
                if (!line.isEmpty()) {
                    //pattern to check if a line start with an uppercase alphabet and follow by a fot and space

                    Matcher matcher = regex.matcher(line);

                    if (matcher.find()) {
                        if (choices.isEmpty()) {
                            choices.append(line);
                        } else {
                            choices.append("\n").append(line);
                        }
                    } else if (line.startsWith("ANSWER: ")) {
                        newQuestion.createCell(3).setCellValue(choices.toString());
                        choices = new StringBuilder();
                        newQuestion.createCell(2).setCellValue(String.valueOf(line.substring(8)));
                        newQuestion.createCell(1).setCellValue(category);
                        insertingRow++;
                    } else {
                        newQuestion = questionBank.createRow(insertingRow);
                        newQuestion.createCell(0).setCellValue(line);
                    }

                }

            }
            importingFileStream.close();

            FileOutputStream writeDataStream = new FileOutputStream(dataPath);
            data.write(writeDataStream);
            writeDataStream.close();
            updateCategory(category, insertingRow - startInsertingRow);
            loadQuestion();
        }
    }

    //check if a file is in Aiken format, return boolean value
    public boolean checkAikenFormatTxt(String path) throws IOException{
        String tmp = new String();
        int numQuestion = 0 , i = 0, flag = 0;
        boolean isAiken = true;

        FileReader fileReader = new FileReader(path);
        int tam = fileReader.read();
        String content = new String("");
        while(tam != -1){
            content = (content + (char) tam);
            tam = fileReader.read();
        }
        String[] paragraphs = content.split("\n");
        for(String paragraph : paragraphs){
            i++;
            String text = paragraph; // text get content of paragraph
            text = text.trim();
//
            if(text.length() == 0)  continue; // skip space line

//                if text is title
            if(flag == 0){
                if((text.charAt(6) != ':')){
                    if((text.charAt(1) == '.') && (Character.isLetter(text.charAt(0)))){
                        tmp = text;
                        isAiken = false;
                        break;
                    }
                    flag = 1;
                    continue;
                }
                else {
                    System.out.println("This is error 1");
                    tmp = text;
                    isAiken = false;
                    break;
                }
            }
//                if text start with "A."
            else if(flag == 1){
                if(text.startsWith("A.")){
                    flag = 2;
                    continue;
                }
                else {
                    System.out.println("This is error 2");
                    tmp = text;
                    isAiken = false;
                    break;
                }
            }
//                if text start with "B." or "C.", "D.", ....
            else if((flag == 2 || flag == 3) && (text.startsWith("ANSWER:") == false)){
                if (text.charAt(1) == '.' && Character.isLetter(text.charAt(0))){
                    flag = 3;
                    continue;
                }
                else {
                    System.out.println("This is error 3");
                    tmp = text;
                    isAiken = false;
                    break;
                }
            }
//                if text start with "ANSWER: ..."
            else if(text.startsWith("ANSWER:") && flag == 3){
                flag = 0;
                numQuestion++;
                continue;
            }
//                else : break loop, announce paragraph i is wrong in aiken format
            else {
                System.out.println("This is error 4");
                tmp = text;
                isAiken = false;
                break;
            }
        }

        if(flag != 0)   isAiken = false;

        if(isAiken ){
            System.out.println("Success " + numQuestion);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("Success " + numQuestion);
//            alert.showAndWait();
        }
        else {
            System.out.println("Error at " + i + "\nContent Error : " + tmp);
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Error at " + i + "\nContent Error : " + tmp);
//            alert.showAndWait();
        }
        return isAiken;
    }

    public boolean checkAikenFormat(String path) throws IOException {
        String tmp = new String();
        int numQuestion = 0 , i = 0, flag = 0;
        boolean isAiken = true;

        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        XWPFDocument docx = new XWPFDocument(fis);

//            loop for each paragraph in file docx,
        for(XWPFParagraph paragraph : docx.getParagraphs()){
            i++;
            String text = paragraph.getText(); // text get content of paragraph
            text = text.trim();
//
            if(text.length() == 0)  continue; // skip space line

//                if text is title
            if(flag == 0){
                if((text.charAt(6) != ':')){
                    if((text.charAt(1) == '.') && (Character.isLetter(text.charAt(0)))){
                        tmp = text;
                        isAiken = false;
                        break;
                    }
                    flag = 1;
                    continue;
                }
                else {
//                    System.out.println("This is error 1");
                    tmp = text;
                    isAiken = false;
                    break;
                }
            }
//                if text start with "A."
            else if(flag == 1){
                if(text.startsWith("A.")){
                    flag = 2;
                    continue;
                }
                else {
//                    System.out.println("This is error 2");
                    tmp = text;
                    isAiken = false;
                    break;
                }
            }
//                if text start with "B." or "C.", "D.", ....
            else if((flag == 2 || flag == 3) && (text.startsWith("ANSWER:") == false)){
                if (text.charAt(1) == '.' && Character.isLetter(text.charAt(0))){
                    flag = 3;
                    continue;
                }
                else {
//                    System.out.println("This is error 3");
                    tmp = text;
                    isAiken = false;
                    break;
                }
            }
//                if text start with "ANSWER: ..."
            else if(text.startsWith("ANSWER:") && flag == 3){
                flag = 0;
                numQuestion++;
                continue;
            }
//                else : break loop, announce paragraph i is wrong in aiken format
            else {
//                System.out.println("This is error 4");
                tmp = text;
                isAiken = false;
                break;
            }
        }

        if(flag != 0)   isAiken = false;

        if(isAiken ){
//            System.out.println("Success " + numQuestion);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Success " + numQuestion);
            alert.showAndWait();
//            update number question is imported success
//            QuestionBankTree.currentCategory = LibraryForUs.updateNumberQuestion(
//                    QuestionBankTree.currentCategory, numQuestion);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error at " + i + "\nContent Error : " + tmp);
            alert.showAndWait();
        }
        return isAiken;
    }

    //update number of question of each category
    public void updateCategory(String categoryPath, int amountChange) throws IOException {
        FileInputStream readDataStream = new FileInputStream(dataPath);
        XSSFWorkbook data = new XSSFWorkbook(readDataStream);
        Sheet categories = data.getSheet("Category");
        for(Row r: categories){
            if(categoryPath.contains(r.getCell(0).getStringCellValue())){
                r.getCell(1).setCellValue(r.getCell(1).getNumericCellValue()+(double)amountChange);
            }
        }
        readDataStream.close();
        FileOutputStream fos = new FileOutputStream(dataPath);
        data.write(fos);
        fos.close();
    }

    //add a new category with the param is category path start with root node
    public void addCategory(String addingCategoryPath) throws IOException {
        FileInputStream readDataStream = new FileInputStream(dataPath);
        data = new XSSFWorkbook(readDataStream);
        categories = data.getSheet("Category");
        int lastRow = categories.getLastRowNum() +1;
        readDataStream.close();
        Row newCategory = categories.createRow(lastRow);
        newCategory.createCell(0).setCellValue(addingCategoryPath);
        newCategory.createCell(1).setCellValue(0);
        FileOutputStream fos = new FileOutputStream(dataPath);
        data.write(fos);
        fos.close();
    }

    //return ArrayList of question object with the given category
    public ArrayList<Question> getQuestionsOfCategory(String categoryPath){
        ArrayList<Question> categoryQuestion = new ArrayList<>();
        for(Question q : questionsList){
            if (q.category.equals(categoryPath)){
                categoryQuestion.add(q);
            }
        }
        return categoryQuestion;
    }

    //return ArrayList of question object with the given category and also question of its subcategory
    public ArrayList<Question> getQuestionsOfCategoryAndSubcategory(String category) throws IOException {
        ArrayList<Question> categoryAndSubcategoryQuestions = new ArrayList<>();
        for(Question q : questionsList){
            if(q.category.contains(category)){
                categoryAndSubcategoryQuestions.add(q);
            }
        }
        return categoryAndSubcategoryQuestions;
    }
}