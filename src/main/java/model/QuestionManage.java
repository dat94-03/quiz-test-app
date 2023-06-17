package model;

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
            choices.append("\n").append(choice);
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
        data = new XSSFWorkbook(fis);
        questionBank = data.getSheet("QuestionBank");
        Row editingQuestion = questionBank.getRow(editedQuestion.id);
        editingQuestion.getCell(0).setCellValue(editedQuestion.title);
        editingQuestion.getCell(1).setCellValue(editedQuestion.category);
        editingQuestion.getCell(2).setCellValue(editedQuestion.correctAnswer);
        StringBuilder choices = new StringBuilder();
        for(String choice :editedQuestion.choices){
            choices.append(choice).append("\n");
        }
        choices.delete(choices.length()-1,choices.length() +1);
        editingQuestion.getCell(3).setCellValue(choices.toString());
        FileOutputStream fos = new FileOutputStream(dataPath);
        data.write(fos);
        loadQuestion();
    }

    //import a file of questions in .docx format
    //return number of question have imported if the format is corrSect, otherwise return -1
    public void importQuestions(String importingPath, String category) throws IOException {
        if (checkAikenFormat(importingPath)) {
            //----------------
            FileInputStream importingFileStream = new FileInputStream(importingPath);
            XWPFDocument importingData = new XWPFDocument(importingFileStream);

            int startInsertingRow = questionBank.getLastRowNum() + 1;
            int insertingRow = startInsertingRow;
            FileOutputStream writeDataStream = new FileOutputStream(dataPath);
            Row newQuestion = questionBank.createRow(insertingRow);
            StringBuilder choices = new StringBuilder();
            for (XWPFParagraph paragraph : importingData.getParagraphs()) {
                String line = paragraph.getText();
                line = line.trim();
                //pattern to check if a line start with an uppercase alphabet and follow by a fot and space
                String pattern = "^[A-Z]\\.";
                Pattern regex = Pattern.compile(pattern);
                Matcher matcher = regex.matcher(line);

                if (line.isEmpty()) {

                    insertingRow++;
                    newQuestion = questionBank.createRow(insertingRow);

                } else if (matcher.find()) {
                    if (choices.isEmpty()) {
                        choices.append(line);
                    } else {
                        choices.append("\n").append(line);
                    }


                } else if (line.startsWith("ANSWER: ")) {
                    newQuestion.createCell(3).setCellValue(choices.toString());
                    choices = new StringBuilder();
                    newQuestion.createCell(2).setCellValue(String.valueOf(line.charAt(8)));
                    newQuestion.createCell(1).setCellValue(category);
                } else {
                    newQuestion.createCell(0).setCellValue(line);
                }

            }

            data.write(writeDataStream);
            writeDataStream.close();
            loadQuestion();
            updateCategory(category, insertingRow - startInsertingRow + 1);
        }
    }

    //check if a file is in Aiken format, return boolean value
    public boolean checkAikenFormat(String path) throws IOException {
        int numQuestion = 0;
        boolean isAiken;

        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        XWPFDocument docx = new XWPFDocument(fis);

        int flag = 0, i = 0;
        boolean flag2 = true;

//            loop for each paragraph in file docx,
        for (XWPFParagraph paragraph : docx.getParagraphs()) {
            i++;
            String text = paragraph.getText(); // text get content of paragraph
            text = text.trim();
            System.out.println("Dong nay la : " + text);
//
            if (text.length() <= 2) continue; // skip space line
//
//                if text isn't answer A,B,C,C or "ANSWER: ....."
            if (text.charAt(1) != '.' && text.charAt(6) != ':' && flag == 0) {
            }
//                if text start with "A."
            else if (text.startsWith("A.")) {
                flag = 1;
            }
//                if text start with "B." or "C.", "D.", ....
            else if (text.charAt(1) == '.' && (flag == 1 || flag == 2)) {
                flag = 2;
            }
//                if text start with "ANSWER: ..."
            else if (text.startsWith("ANSWER:") && flag == 2) {
                flag = 0;
                numQuestion++;
            }
//                else : break loop, announce paragraph is wrong in aiken format
            else {
                System.out.println("Error at " + i + " " + text);
                flag2 = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error at " + i + " " + text);
                alert.showAndWait();
                break;
            }
//
        }
        if (flag2 == true) {
            System.out.println("Success " + numQuestion);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Success " + numQuestion);
            alert.showAndWait();
        }
        isAiken = flag2;
        return isAiken;
    }

    //update number of question of each category
    public void updateCategory(String categoryPath, int amountChange) throws IOException {
        FileInputStream readDataStream = new FileInputStream(dataPath);
        data = new XSSFWorkbook(readDataStream);
        categories = data.getSheet("Category");
        for(Row r: categories){
            if(categoryPath.contains(r.getCell(0).getStringCellValue())){
                r.getCell(1).setCellValue(r.getCell(1).getNumericCellValue()+(double)amountChange);
            }
        }
        FileOutputStream fos = new FileOutputStream(dataPath);
        data.write(fos);
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