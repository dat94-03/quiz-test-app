package model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class QuizzesManage {
    String dataPath = "src/main/java/data/QuizTestAppData.xlsx";
    XSSFWorkbook data;
    Sheet quizzes;
    public static ArrayList<Quiz> quizzesList= new ArrayList<>();

    public QuizzesManage() throws IOException {
        this.loadQuiz();
    }

    //get arrayList of  quiz object from database
    public void loadQuiz() throws IOException {
        quizzesList.clear();
        FileInputStream ips = new FileInputStream(dataPath);
        data = new XSSFWorkbook(ips);
        quizzes = data.getSheet("Quizzes");
        int i = 0;
        for (Row row : quizzes) {
            Quiz quiz = new Quiz(
                    i,
                    String.valueOf(row.getCell(0)),
                    String.valueOf(row.getCell(1)),
                    String.valueOf(row.getCell(2)),
                    (int) row.getCell(3).getNumericCellValue(),
                    String.valueOf(row.getCell(4)),
                    String.valueOf(row.getCell(5))
            );

            i++;
            quizzesList.add(quiz);
        }
    }

    //add new quiz to database
    public void addQuiz(Quiz addingQuiz) throws IOException {
    FileInputStream fis = new FileInputStream(dataPath);
    data = new XSSFWorkbook(fis);
    quizzes = data.getSheet("Quizzes");
    int addingRow = quizzes.getLastRowNum() +1;
    fis.close();

    Row row = quizzes.createRow(addingRow);
    row.createCell(0).setCellValue(addingQuiz.quizName);
    row.createCell(1).setCellValue(addingQuiz.openingTime);
    row.createCell(2).setCellValue(addingQuiz.closingTime);
    row.createCell(3).setCellValue(addingQuiz.timeLimit);
    row.createCell(4).setCellValue(addingQuiz.quizQuestions);
    row.createCell(5).setCellValue(addingQuiz.questionsMark);
    FileOutputStream fos = new FileOutputStream(dataPath);
    data.write(fos);
    fos.close();
    loadQuiz();
    }

    //add quiz' questions to exiting quiz in database
    public void editingQuiz(int id, String quizQuestions, String questionsMark) throws IOException {
        FileInputStream fis = new FileInputStream(dataPath);
        data = new XSSFWorkbook(fis);
        quizzes = data.getSheet("Quizzes");

        fis.close();

        Row row = quizzes.getRow(id);
        row.createCell(4).setCellValue(quizQuestions);
        row.createCell(5).setCellValue(questionsMark);
        FileOutputStream fos = new FileOutputStream(dataPath);
        data.write(fos);
        fos.close();
        loadQuiz();
    }
}

