package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Question;
import model.QuestionManage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestion implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ChoiceBox<String> choiceBox1;
    private ChoiceBox<String>[] selectPercent = new ChoiceBox[10];
    @FXML
    private VBox moreChoice;
    @FXML
    private TextField choiceAnswer1;
    @FXML
    private TextField choiceAnswer2;

    TextField[] Choice = new TextField[10];  // dua vao mang

    private String[] percent = {"None","100%","90%","83.33333%","80%","75%","70%","67.66667%","60%","50%","40%","33.3333%","30%","25%","20%","16.66667%","14.28571%","12.5%","11.11111%","10%","5%","-5%",};
    int indexChoice = 3;
    String[] answerChoice = new String[10];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(percent);
        choiceBox.setValue("None");
        choiceBox1.getItems().addAll(percent);
        choiceBox1.setValue("None");
        moreChoice.setSpacing(15);
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void switchToSceneList(ActionEvent event) throws IOException {
        int currentID = 0;
        String rightAnswer = new String();
        for(Question q : QuestionManage.questionsList){
            currentID++;
        }

        if(choiceAnswer1 != null)
            answerChoice[0] = choiceAnswer1.getText();
        if(choiceAnswer2 != null)
            answerChoice[1] = choiceAnswer2.getText();

        for(int i = 3; i < indexChoice; i++){
            if(Choice[i] == null)   continue;
//            System.out.println(Choice[i].getText());
            answerChoice[i - 1] = Choice[i].getText();

            if(selectPercent[i].getValue().equals("100%")){
                rightAnswer = Choice[i].getText(0, 1);
                System.out.println(rightAnswer);
            }
        }
//        System.out.println(answerChoice[0] + "\n" + answerChoice[1]);
        Question newQuestion = new Question(++currentID, "Default",questionText.getText() , rightAnswer, answerChoice);
        QuestionManage questionManage = new QuestionManage();
        questionManage.addQuestion(newQuestion);

        root = FXMLLoader.load(getClass().getResource("QuestionList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMoreChoice(ActionEvent event) throws IOException {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        HBox hBoxContainChoice = new HBox();
        Label label = new Label("     Choice " + indexChoice);
        Label labelGrade = new Label("     Grade");
        Label spacer = new Label();
        selectPercent[indexChoice] = new ChoiceBox<>();
        Choice[indexChoice] = new TextField();

        selectPercent[indexChoice].setValue("None");
//        Region spacer = new Region();
        selectPercent[indexChoice].getItems().addAll(percent);
        label.setFont(Font.font(18));
        labelGrade.setFont(Font.font(18));
        spacer.setFont(Font.font(10));

        hBox.getChildren().addAll(labelGrade, selectPercent[indexChoice]);
        hBox.setSpacing(50);
        hBoxContainChoice.getChildren().addAll(label, Choice[indexChoice]);
        hBoxContainChoice.setSpacing(50);
//        VBox.setVgrow(spacer, Priority.ALWAYS);
        vBox.getChildren().addAll(spacer,hBoxContainChoice, hBox);
        vBox.setSpacing(15);

        vBox.setBackground(Background.fill(Color.WHITE));
        moreChoice.getChildren().add(vBox);

        indexChoice++;
    }

//    public void

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
