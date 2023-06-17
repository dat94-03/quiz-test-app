package model;


public class Quiz {
    int id;
    String quizName;
    String openingTime,closingTime;
    int timeLimit;

    String quizQuestions;

    public Quiz(int id, String quizName, String openingTime, String closingTime, int timeLimit, String quizQuestions){
        this.id = id;
        this.quizName = quizName;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.timeLimit = timeLimit;
        this.quizQuestions = quizQuestions;
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