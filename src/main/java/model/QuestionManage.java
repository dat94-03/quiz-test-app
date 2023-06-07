package model;

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
        int id =0;
        for(Row questionData : questionBank) {
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
    public void deleteQuestion(int questionId,String questionCategory) throws  IOException {
            FileOutputStream writeDataStream = new FileOutputStream(dataPath);
            int lastRowNum=questionBank.getLastRowNum();
            updateCategory(questionCategory,-1);
            if(questionId>=0&&questionId<lastRowNum){
                questionBank.shiftRows(questionId+1,lastRowNum, -1);
            }
            if(questionId==lastRowNum){
                Row removingRow=questionBank.getRow(questionId);
                if(removingRow!=null){
                    questionBank.removeRow(removingRow);
                }
            }
            data.write(writeDataStream);

            writeDataStream.close();
            loadQuestion();
    }

    //add single questionS
    public void addQuestion(Question q) throws IOException {
        StringBuilder choices= new StringBuilder();
        int insertingRow = questionBank.getLastRowNum() + 1;
        FileOutputStream writeDataStream = new FileOutputStream(dataPath);
        Row newQuestion = questionBank.createRow(insertingRow);
        newQuestion.createCell(0).setCellValue(q.title);
        newQuestion.createCell(1).setCellValue(q.category);
        newQuestion.createCell(2).setCellValue(q.correctAnswer);
        for(String choice : q.choices){
            choices.append("\n").append(choice);
        }
        newQuestion.createCell(3).setCellValue(choices.toString());
       data.write(writeDataStream);
       updateCategory(q.category, 1);
        writeDataStream.close();
        loadQuestion();
    }

    //import a file of questions in .docx format
    //return number of question have imported if the format is corrSect, otherwise return -1
    // parameter category will be a string that represent a tree of category and all category have a parent is root
    // it needs to concat "root/" before
    public int importQuestions(String importingPath, String category) throws IOException {
        if(checkAikenFormat(importingPath)){
            //----------------
            FileInputStream importingFileStream = new FileInputStream(importingPath);
            XWPFDocument importingData = new XWPFDocument(importingFileStream);

            int startInsertingRow = questionBank.getLastRowNum() +1;
            int insertingRow = startInsertingRow;
            FileOutputStream writeDataStream = new FileOutputStream(dataPath);
            Row newQuestion = questionBank.createRow(insertingRow);
            StringBuilder choices= new StringBuilder();
            for(XWPFParagraph paragraph : importingData.getParagraphs()){
                String line = paragraph.getText();
                line = line.trim();
                //pattern to check if a line start with an uppercase alphabet and follow by a fot and space
                String pattern = "^[A-Z]\\.";
                Pattern regex = Pattern.compile(pattern);
                Matcher matcher = regex.matcher(line);

                if(line.isEmpty()) {

                    insertingRow ++;
                    newQuestion = questionBank.createRow(insertingRow);

                }else if(matcher.find()){
                    if(choices.isEmpty()){
                        choices.append(line);
                    }else{
                        choices.append("\n").append(line);
                    }


                }else if(line.startsWith("ANSWER: ")){
                    newQuestion.createCell(3).setCellValue(choices.toString());
                    choices = new StringBuilder();
                    newQuestion.createCell(2).setCellValue(String.valueOf(line.charAt(8)));
                    newQuestion.createCell(1).setCellValue(category);
                }else{
                    newQuestion.createCell(0).setCellValue(line);
                }

            }

            data.write(writeDataStream);
            writeDataStream.close();
            loadQuestion();
            updateCategory(category,insertingRow-startInsertingRow +1);
            return (insertingRow-startInsertingRow +1);
        }
        else {
            return -1;
        }
    }

    //check if a file is in Aiken format, return boolean value
    public boolean checkAikenFormat(String importingPath) throws IOException {
        FileInputStream importingFileStream = new FileInputStream(importingPath);
        XWPFDocument importingData = new XWPFDocument(importingFileStream);

        return true;
    }

    public void updateCategory(String category,int amountChange) throws IOException {
        FileInputStream readDataStream = new FileInputStream(dataPath);
        data = new XSSFWorkbook(readDataStream);
        categories = data.getSheet("Category");
        for(Row row: categories){
            if(category.contains(String.valueOf(row.getCell(0))) ){
                row.getCell(1).setCellValue(row.getCell(1).getNumericCellValue() + amountChange);
            }

        }
        FileOutputStream fos = new FileOutputStream(dataPath);
        data.write(fos);
        fos.close();
    }

    public void addCategory(String parentCategory,String addingCategory) throws IOException {
        FileInputStream readDataStream = new FileInputStream(dataPath);
        data = new XSSFWorkbook(readDataStream);
        categories = data.getSheet("Category");
        int lastRow = categories.getLastRowNum() +1;
        Row newCategory = categories.createRow(lastRow);
        newCategory.createCell(0).setCellValue(parentCategory + "/" + addingCategory);
        newCategory.createCell(1).setCellValue(0);
        FileOutputStream fos = new FileOutputStream(dataPath);
        data.write(fos);
        fos.close();
    }


    /////print out data to test(delete when done project)
    public void test(){
     for(Question question:questionsList){
         System.out.println(question.toString());
     }
    }
}
