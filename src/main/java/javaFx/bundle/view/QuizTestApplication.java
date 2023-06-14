package javaFx.bundle.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CheckAikenFormat;
import model.Question;
import model.QuestionManage;
//import model.QuestionManage;
import java.io.IOException;

public class QuizTestApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizTestApplication.class.getResource("Home.fxml"));

        stage.setTitle("Quiz Test App");
        Scene scene = new Scene(fxmlLoader.load(), 960, 760 );
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}