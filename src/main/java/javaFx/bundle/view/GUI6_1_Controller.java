package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI6_1_Controller implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label quizName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizName.setText(GUI1_1_Controller.currentQuiz.quizName);
    }
    public void switchToGUI6_2a(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("gui6.2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchTo7(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("Gui7_3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
