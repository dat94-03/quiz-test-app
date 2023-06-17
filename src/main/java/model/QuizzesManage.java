package model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

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
    //add new quiz to database
    public void AddQuiz(){

    }
    //get arrayList of  quiz object from database
    public void loadQuiz() throws IOException {
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
                    String.valueOf(row.getCell(4))
            );

            i++;
            quizzesList.add(quiz);
        }
    }
    //add quiz' questions to exiting quiz in database
    public void editingQuiz(String quizQuestions){

    }
}

