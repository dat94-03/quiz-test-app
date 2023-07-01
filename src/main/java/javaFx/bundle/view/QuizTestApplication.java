package javaFx.bundle.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class QuizTestApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizTestApplication.class.getResource("Home.fxml"));

        Image image = new Image(getClass().getResource("/Img.img/moodle.png").toExternalForm());
        stage.setTitle("Quiz Test App");
        Scene scene = new Scene(fxmlLoader.load(), 960, 760 );
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.show();
    }
    public static void main(String[] args) throws IOException {
        QuestionManage questionManage = new QuestionManage();
        QuizzesManage quizzesManage = new QuizzesManage();
        Question question = QuestionManage.questionsList.get(3);
        Quiz quiz = QuizzesManage.quizzesList.get(0);
        LibraryForUs.deleteQuestionInQuiz(question, quiz);
        System.out.println(quiz.quizQuestions);
//        launch();
    }
}