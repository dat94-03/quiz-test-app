package model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class QuizzesManage {
    String dataPath = "src/main/java/data/QuizTestAppData.xlsx";
    XSSFWorkbook data;
    Sheet quizzes;

    public QuizzesManage(){

    }
    //add new quiz to database
    public void AddQuiz(){

    }
    //get quiz from database
    public Quiz getQuiz(String name) throws IOException {
        Quiz quiz;
        FileInputStream ips = new FileInputStream(dataPath);
        data = new XSSFWorkbook(ips);
        quizzes = data.getSheet("Quizzes");

        for(Row row : quizzes){
            if(Objects.equals(name, String.valueOf(row.getCell(0)))){
                quiz = new Quiz(
                        name,
                        String.valueOf(row.getCell(1)),
                        String.valueOf(row.getCell(2)),
                        (int) row.getCell(3).getNumericCellValue(),
                        String.valueOf(row.getCell(4))
                );
                return quiz;
            }
        }
    return null;
    }
    //add quiz' questions to exiting quiz in database
    public void editingQuiz(String quizQuestions){

    }
}


