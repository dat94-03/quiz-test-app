package model;

import java.util.ArrayList;

public class QuizInExam {
    public  ArrayList<Integer> correctQuestions = new ArrayList<>();
    public ArrayList<String> userChoice = new ArrayList<>();
    public double userPoint = 0;
    public double maxPoint = 0;
    public String timeTaken = new String();
    public String startExam = new String();
    public String endExam = new String();

    public void deleteData(){
        maxPoint = 0;
        userPoint = 0;
        correctQuestions.clear();
        userChoice.clear();
        startExam = "";
        endExam = "";
        timeTaken = "";
    }
}
