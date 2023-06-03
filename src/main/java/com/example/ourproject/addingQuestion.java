package com.example.ourproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addingQuestion implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ChoiceBox<String> choiceBox1;
    private String[] percent = {"None","100%","90%","83.33333%","80%","75%","70%","67.66667%","60%","50%","40%","33.3333%","30%","25%","20%","16.66667%","14.28571%","12.5%","11.11111%","10%","5%","-5%",};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(percent);
        choiceBox.setValue("None");
        choiceBox1.getItems().addAll(percent);
        choiceBox1.setValue("None");
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void switchToSceneList(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("listQuestion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMoreChoice(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("blanksFor3Choices.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField myTextField;
    @FXML
    private void initialize() {
        myTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.trim().isEmpty()){
                myTextField.setStyle("-fx-border-color: red;");
            }else{
                myTextField.setStyle("");
            }
        });
    }
}
