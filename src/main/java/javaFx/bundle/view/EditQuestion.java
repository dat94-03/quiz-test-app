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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditQuestion implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private TextField[] textChoice = new TextField[10];  // dua vao mang
    private ChoiceBox<String>[] selectPercent = new ChoiceBox[10];
    String[] answerChoice = new String[10];

    private String[] percent = {"None","100%","90%","83.33333%","80%","75%","70%","67.66667%","60%","50%","40%","33.3333%","30%","25%","20%","16.66667%","14.28571%","12.5%","11.11111%","10%","5%","-5%",};
    int indexChoice = 0;
    int numOldChoice;
    private String selectCategory = new String();
    private String fullyPath = TreeView.fullyCategory;
    private String oldPath = TreeView.fullyCategory;
    Question q = QuestionList.qStatic;
    @FXML
    private Label categoryLabel;

    @FXML
    private VBox moreChoice;
    @FXML
    private TextArea questionText;
    @FXML
    private javafx.scene.control.TreeView<String> categoryTreeView;
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {

        categoryLabel.setText("  " + TreeView.currentCategory);
        moreChoice.setSpacing(15);
        questionText.setText(q.title);

//        setup TreeView
        categoryTreeView.setStyle("-fx-font-size: 16px;");
        categoryTreeView.setShowRoot(false);

        TreeItem<String> rootItem = new TreeItem<>("root");
        rootItem.setExpanded(true);

        CategoriesManage build = null;
        try {
            build = new CategoriesManage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Category rootCategory = build.getRoot();
        LibraryForUs.accessChildrenTreeView(rootCategory, rootItem);

        categoryTreeView.setRoot(rootItem);

//        draw answer of old question
        for(String answer : q.choices){
            if(answer.equals("null"))  continue;
            VBox vBox = new VBox();
            HBox hBox = new HBox();
            HBox hBoxContainChoice = new HBox();
            Label label = new Label("     Choice " + (indexChoice + 1));
            Label labelGrade = new Label("     Grade");
            Label spacer = new Label();
            Label spacerTag = new Label();
//        Declare ChoiceBox selectPercent and TextField textChoice for each Choice
            selectPercent[indexChoice] = new ChoiceBox<>();
            textChoice[indexChoice] = new TextField();

//        Draw old choice
            selectPercent[indexChoice].setValue("None");
            selectPercent[indexChoice].getItems().addAll(percent);
            textChoice[indexChoice].setFont(Font.font(18));
            textChoice[indexChoice].setText(answer);
            // set correct answer
            if(textChoice[indexChoice].getText().charAt(0) == q.correctAnswer.charAt(0)){
                selectPercent[indexChoice].setValue("100%");
            }
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

    public void selectItem() {
        categoryTreeView.setOnMouseClicked(mouseEvent -> {
            TreeItem<String> selectedItem = (TreeItem<String>) categoryTreeView.getSelectionModel().getSelectedItem();
            selectCategory = selectedItem.getValue();

//            get fully path of category

            fullyPath = LibraryForUs.getFullyCategory(selectedItem);

//            disable TreeView when we done
            categoryTreeView.setVisible(false);
//            set label after change parent category
            categoryLabel.setText("  " + selectedItem.getValue());
        });
    }

    @FXML
    public void displayTreeView() {
        categoryTreeView.setVisible(true);
    }
    @FXML
    public void displayTreeViewOff() {
        categoryTreeView.setVisible(false);
    }

    @FXML
    public void saveChages(ActionEvent event) throws IOException {
//        declare flag
        boolean flagTitle = true,  flagCorectAnswer = false;
        int flagChoices = 0; // valid question must have more than two answer
//        update category, title, choices, corect answer
        q.category = fullyPath;
        if(questionText == null){
            flagTitle = false;
        }
        else
            q.title = questionText.getText();

        q.choices.clear();  // delete all in order to easy work
        for(int i = 0; i < indexChoice; i++){
            if(textChoice[i] == null)  continue;
            q.choices.add(textChoice[i].getText());
            flagChoices++;
            if(selectPercent[i].getValue().equals("100%")){
                q.correctAnswer = textChoice[i].getText(0,1);
                flagCorectAnswer = true;
            }
        }

        if((flagTitle == true) && (flagChoices >= 2) && (flagCorectAnswer == true)){
            QuestionManage questionManage = new QuestionManage();
            System.out.println(q);
            questionManage.editQuestion(q);
//            set alert to inform
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You edit question successfully");
            alert.showAndWait();

//            switch scene
            root = FXMLLoader.load(getClass().getResource("QuestionList.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You did'nt finish edit question");
            alert.showAndWait();
        }
    }

    public void saveChangesAndContinueEditting(ActionEvent event) throws IOException {
        //        declare flag
        boolean flagTitle = true,  flagCorectAnswer = false;
        int flagChoices = 0; // valid question must have more than two answer
//        update category, title, choices, corect answer
        q.category = fullyPath;
        if(questionText == null){
            flagTitle = false;
        }
        else
            q.title = questionText.getText();

        q.choices.clear();  // delete all in order to easy work
        for(int i = 0; i < indexChoice; i++){
            if(textChoice[i] == null)  continue;
            q.choices.add(textChoice[i].getText());
            flagChoices++;
            if(selectPercent[i].getValue().equals("100%")){
                q.correctAnswer = textChoice[i].getText(0,1);
                flagCorectAnswer = true;
            }
        }
    }

    public void getMoreChoice(ActionEvent event) throws IOException {
        if(indexChoice >= 4 && textChoice[indexChoice-1].getText().equals("")){
            return;
        }
        else {  // if if previous choice is filled
            VBox vBox = new VBox();
            HBox hBox = new HBox();
            HBox hBoxContainChoice = new HBox();
            Label label = new Label("     Choice " + (indexChoice + 1));
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

    @FXML
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