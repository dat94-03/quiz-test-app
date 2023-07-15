package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Question;
import model.QuestionManage;

import java.io.IOException;
import java.util.ArrayList;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label label;

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Popup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToQuestionBank(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("QuestionBank.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   public void switchToAddNewQuiz(ActionEvent event) throws  IOException {
       root = FXMLLoader.load(getClass().getResource("AddNewQuiz.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
    public void switchToCategory(MouseEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("AddingCategory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToImport(MouseEvent event) throws  IOException {
        if(GUI1_1_Controller.isSelectedCategory){
            root = FXMLLoader.load(getClass().getResource("ImportQuestion.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select category before import file");
            alert.showAndWait();
        }
    }
    public void switchTo7(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("Gui7_3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGUI6_1(MouseEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("gui6.1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void textUnderlineIn() {
        label.setUnderline(true);
    }
    public void textUnderlineOut() {
        label.setUnderline(false);
    }

}