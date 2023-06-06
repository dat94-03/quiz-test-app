package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Question {
    int id = -1;
    public String title;
    public String category;
    public ArrayList<String> choices = new ArrayList<>();
    public String correctAnswer;
    Question(){
        this.title = "";
        this.category= "";
        this.correctAnswer = "";

    }
    public Question(int id, String category,String title,String correctAnswer,String[] choices){
        this.title = title;
        this.id = id;
        this.category = category;
        this.choices.addAll(Arrays.asList(choices));
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Question{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", choices=").append(choices);
        sb.append(", correctAnswer='").append(correctAnswer).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
