package model;


public class Quiz {
    public int id;
    public String quizName;
    public String openingTime,closingTime;
    public int timeLimit;

    public String quizQuestions;
    public String questionsMark;

    public Quiz(int id, String quizName, String openingTime, String closingTime, int timeLimit, String quizQuestions, String questionsMark){
        this.id = id;
        this.quizName = quizName;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.timeLimit = timeLimit;
        this.quizQuestions = quizQuestions;
        this.questionsMark=questionsMark;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Quiz{");
        sb.append("id=").append(id);
        sb.append(", quizName='").append(quizName).append('\'');
        sb.append(", openingTime='").append(openingTime).append('\'');
        sb.append(", closingTime='").append(closingTime).append('\'');
        sb.append(", timeLimit=").append(timeLimit);
        sb.append(", quizQuestions='").append(quizQuestions).append('\'');
        sb.append('}');
        return sb.toString();
    }
}