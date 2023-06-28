package javaFx.bundle.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Question;
import model.QuestionManage;
import model.Quiz;
import model.QuizzesManage;

import java.io.IOException;

public class QuizTestApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizTestApplication.class.getResource("Home.fxml"));

        Image image = new Image(getClass().getResource("/javaFx/bundle/view/moodle.jpg").toExternalForm());
        stage.setTitle("Quiz Test App");
        Scene scene = new Scene(fxmlLoader.load(), 960, 760 );
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.show();
    }
    public static void main(String[] args) throws IOException {
        QuestionManage questionManage = new QuestionManage();
       //questionManage.importQuestions("src/main/java/data/testInput.docx","sinh học 7");
        //questionManage.checkAikenFormat("src/main/java/data/testInput.docx");
//        for(Question q : QuestionManage.questionsList){
//            System.out.println(q.toString());
//        }

        launch();
    }
}