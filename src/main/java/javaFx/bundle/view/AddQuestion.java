package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.LibraryForUs;
import model.Question;
import model.QuestionManage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddQuestion implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    TextField[] textChoice = new TextField[10];  // dua vao mang
    String[] answerChoice = new String[10];
    private ChoiceBox<String>[] selectPercent = new ChoiceBox[10];
    private ArrayList<String> answerChoices = new ArrayList<>();
    private String[] percent = {"None","100%","90%","83.33333%","80%","75%","70%","67.66667%","60%","50%","40%","33.3333%","30%","25%","20%","16.66667%","14.28571%","12.5%","11.11111%","10%","5%","-5%",};
    int indexChoice = 3;
    @FXML
    private Label categoryLabel;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private VBox moreChoice;
    @FXML
    private TextField choiceAnswer1;
    @FXML
    private TextField choiceAnswer2;
    
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        categoryLabel.setText("  " + QuestionBankTree.currentCategory);
        choiceBox1.getItems().addAll(percent);
        choiceBox1.setValue("None");
        choiceBox2.getItems().addAll(percent);
        choiceBox2.setValue("None");
        moreChoice.setSpacing(15);
    }

    @FXML
    public void saveChanges(ActionEvent event) throws IOException {
        int currentID = 0;
        String rightAnswer = new String(""); //  maybe multiple choice
        boolean existCorrectAnswer = false;  // flag
        boolean isHaveText = true;
        QuestionManage questionManage = new QuestionManage();

        for(Question q : questionManage.questionsList){
            currentID++;
        }

        if(choiceAnswer1 != null)
            answerChoice[0] = choiceAnswer1.getText();
        if(choiceAnswer2 != null)
            answerChoice[1] = choiceAnswer2.getText();

        if(choiceBox1 != null){
            if(choiceBox1.getValue().equals("100%")){
                rightAnswer += choiceAnswer1.getText(0, 1)  + ", ";
                existCorrectAnswer = true;
            }
        }

        if(choiceBox2 != null){
            if(choiceBox2.getValue().equals("100%")){
                rightAnswer += choiceAnswer2.getText(0, 1) + ", ";
                existCorrectAnswer = true;
            }
        }


        for(int i = 3; i < indexChoice; i++){
            if(textChoice[i] == null)   continue;
            answerChoice[i - 1] = textChoice[i].getText();

            if(selectPercent[i] != null){
                if(selectPercent[i].getValue().equals("100%")) {
                    rightAnswer += textChoice[i].getText(0, 1) + ", ";
                    existCorrectAnswer = true;
                }
            }
        }
        if(rightAnswer.length() > 0){
            rightAnswer = rightAnswer.substring(0, rightAnswer.length() - 2);
        }
        if(questionText.getText().equals("")){
            isHaveText = false;
            Alert isFormatQuestionRight = new Alert(Alert.AlertType.ERROR);
            isFormatQuestionRight.setContentText("You don't set text");
            isFormatQuestionRight.showAndWait();
        }

        if(existCorrectAnswer == false){
            Alert isFormatQuestionRight = new Alert(Alert.AlertType.ERROR);
            isFormatQuestionRight.setContentText("You didn't set up correct answer");
            isFormatQuestionRight.showAndWait();
        }

        if(existCorrectAnswer == true && isHaveText == true){
//            Update option for question, without null
            for(String tmp : answerChoice){
                if(tmp != null && (tmp.equals("") == false))     answerChoices.add(tmp);
            }

//            Add question to database
            Question newQuestion = new Question(++currentID, QuestionBankTree.fullyCategory, questionText.getText() , rightAnswer, answerChoices.toArray(answerChoices.toArray(new String[0])));
            questionManage.addQuestion(newQuestion);

//           update number question of current category
            QuestionBankTree.currentCategory = LibraryForUs.updateNumberQuestion(QuestionBankTree.currentCategory, 1);

//            switch scene
            root = FXMLLoader.load(getClass().getResource("QuestionList.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void saveChangesAndContinueEditting(ActionEvent event) throws IOException {
        int currentID = 0;
        String rightAnswer = new String();
        boolean existCorrectAnswer = false;  // flag
        boolean isHaveText = true;
        QuestionManage questionManage = new QuestionManage();

        for(Question q : questionManage.questionsList){
            currentID++;
        }

        if(choiceAnswer1 != null)
            answerChoice[0] = choiceAnswer1.getText();
        if(choiceAnswer2 != null)
            answerChoice[1] = choiceAnswer2.getText();

        if(choiceBox1 != null){
            if(choiceBox1.getValue().equals("100%")){
                rightAnswer = choiceAnswer1.getText(0, 1);
                existCorrectAnswer = true;
            }
        }

        if(choiceBox2 != null){
            if(choiceBox2.getValue().equals("100%")){
                rightAnswer = choiceAnswer2.getText(0, 1);
                existCorrectAnswer = true;
            }
        }


        for(int i = 3; i < indexChoice; i++){
            if(textChoice[i] == null)   continue;
            answerChoice[i - 1] = textChoice[i].getText();

            if(selectPercent[i] != null){
                if(selectPercent[i].getValue().equals("100%")){
                    rightAnswer = textChoice[i].getText(0, 1);
                    existCorrectAnswer = true;
                }
            }
        }

        if(questionText.getText().equals("")){
            isHaveText = false;
            Alert isFormatQuestionRight = new Alert(Alert.AlertType.ERROR);
            isFormatQuestionRight.setContentText("You don't set text");
            isFormatQuestionRight.showAndWait();
        }

        if(existCorrectAnswer == false){
            Alert isFormatQuestionRight = new Alert(Alert.AlertType.ERROR);
            isFormatQuestionRight.setContentText("You did'nt set up correct answer");
            isFormatQuestionRight.showAndWait();
        }

        if(existCorrectAnswer == true && isHaveText == true){
            Question newQuestion = new Question(++currentID, QuestionBankTree.fullyCategory, questionText.getText() , rightAnswer, answerChoice);
            questionManage.addQuestion(newQuestion);
        }
    }

    public void getMoreChoice(ActionEvent event) throws IOException {
        if(indexChoice >= 4 && textChoice[indexChoice - 1].getText().equals("")){
            return;
        }
        else {  // if if previous choice is filled
            VBox vBox = new VBox();
            HBox hBox = new HBox();
            HBox hBoxContainChoice = new HBox();
            Label label = new Label("     Choice " + indexChoice);
            Label labelGrade = new Label("     Grade");
            Label spacer = new Label();
            Label spacerTag = new Label();
//        Declare ChoiceBox selectPercent and TextField textChoice for each Choice
            selectPercent[indexChoice] = new ChoiceBox<>();
            textChoice[indexChoice] = new TextField();

//        Draw one more choice
            selectPercent[indexChoice].setValue("None");
            selectPercent[indexChoice].getItems().addAll(percent);
            textChoice[indexChoice].setFont(Font.font(18));
            label.setFont(Font.font(18));
            labelGrade.setFont(Font.font(18));
            spacer.setFont(Font.font(5));
            spacerTag.setFont(Font.font(14));

            hBox.getChildren().addAll(labelGrade, selectPercent[indexChoice]);
            hBox.setSpacing(50);
            hBoxContainChoice.getChildren().addAll(label, textChoice[indexChoice]);
            hBoxContainChoice.setSpacing(50);
            vBox.getChildren().addAll(spacer,hBoxContainChoice, hBox, spacerTag);
            vBox.setSpacing(15);

            vBox.setBackground(Background.fill(Color.WHITE));
            moreChoice.getChildren().add(vBox);
            moreChoice.setPrefHeight(moreChoice.getHeight() + 150);

//        Number of choice plus 1
            indexChoice++;
        }
    }


    public void switchToSceneList(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("QuestionList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToQuestionList(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("QuestionList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToQuestionBank(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("QuestionBank.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private ImageView alert1;
    @FXML
    private ImageView alert2;
    @FXML
    private ImageView alert3;
    @FXML
    private TextField myTextField;
    @FXML
    private TextField myTextField2;
    @FXML
    private TextArea questionText;
    public void blankCheck(KeyEvent event) {
        if (myTextField.getText().isBlank()) {
            alert1.setVisible(true);
        } else  {
            alert1.setVisible(false);
        }
    }
    public void blankCheck2(KeyEvent event) {
        if (questionText.getText().isBlank()) {
            alert2.setVisible(true);
        } else  {
            alert2.setVisible(false);
        }
    }
    public void blankCheck3(KeyEvent event) {
        if (myTextField2.getText().isBlank()) {
            alert3.setVisible(true);
        } else  {
            alert3.setVisible(false);
        }
    }

}
