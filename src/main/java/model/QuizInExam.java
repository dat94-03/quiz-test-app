package model;

import java.util.ArrayList;

public class QuizInExam {
    public  ArrayList<Integer> correctQuestions = new ArrayList<>();
    public ArrayList<Integer> userChoice = new ArrayList<>();
    public double userPoint = 0;
    public double maxPoint = 0;
    public double timeUsed = 0;

    public String startExam = new String();
    public String endExam = new String();

    public void saveStateQuizExam(ArrayList<Integer> correctQuestion,ArrayList<Integer> userChoice, double userPoint, double maxPoint){
        this.maxPoint = maxPoint;
        this.userPoint = userPoint;
        this.correctQuestions = correctQuestion;
        this.userChoice = userChoice;
    }
    public void deleteData(){
        maxPoint = 0;
        userPoint = 0;
        correctQuestions.clear();
        userChoice.clear();
        startExam = "";
        endExam = "";
        timeUsed = 0;
    }
}
