package javaFx.bundle.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.QuestionManage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class QuizTestApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizTestApplication.class.getResource("Home.fxml"));

        Image image = new Image(getClass().getResource("/Img.img/moodle.jpg").toExternalForm());
        stage.setTitle("Quiz Test App");
        Scene scene = new Scene(fxmlLoader.load(), 960, 760 );
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.show();
    }
    public static void main(String[] args) throws IOException, InvalidFormatException {

//
        QuestionManage qm = new QuestionManage();
//

        launch();

    }
}