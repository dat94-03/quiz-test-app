package javaFx.bundle.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;



public class GUI6_5_Controller implements Initializable {
    @FXML
    public TreeView treeView;
    @FXML
    private AnchorPane menuPane ;
    @FXML
    private ComboBox comboBox ;
    @FXML
    private HBox numberHBox ;
    @FXML
    private VBox questionVBox ;
    @FXML
    private Button buttonCate;
    @FXML
    private CheckBox subCate;
    Integer h = 0 ;
    Integer k = 0 ;
    Integer i =0 ;
    Button[] button = new Button[40] ;

    private String selectCate = new String("Default");
    private String fullyCate = new String();
    private Quiz currentQuiz = GUI1_1_Controller.currentQuiz;

    private ArrayList<Integer> idQuestionInCate = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeView.setStyle("-fx-font-size: 16px;");
        treeView.setShowRoot(false);

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

        treeView.setRoot(rootItem);
    }

    @FXML
    public void displayTreeView() {
        treeView.setVisible(true);
    }
    @FXML
    public void displayTreeViewOff() {
        treeView.setVisible(false);
    }
    Integer count = 0 ;


    public void selectItem() {
        treeView.setOnMouseClicked(mouseEvent -> {
            TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                questionVBox.getChildren().clear();
                numberHBox.getChildren().clear();
                idQuestionInCate.clear();

                selectCate = selectedItem.getValue();
                fullyCate = LibraryForUs.getFullyCategory(selectedItem);
                buttonCate.setText(selectCate);
                displayTreeViewOff();
                for(Question question : QuestionManage.questionsList){
                    if(question.category.equals(fullyCate)){
                        idQuestionInCate.add(question.id);
                    }
                }
                appear();
            }
        });
    }

    public void showQuestionFromSubCate() throws IOException {
        QuestionManage qm = new QuestionManage();
        questionVBox.getChildren().clear();
        numberHBox.getChildren().clear();
        idQuestionInCate.clear();

        if(subCate.isSelected() == false){
            for(Question question : QuestionManage.questionsList){
                if(question.category.equals(fullyCate)){
                    idQuestionInCate.add(question.id);
                }
            }
            appear();
        }
        else {
            ArrayList<Question> subCate = qm.getQuestionsOfCategoryAndSubcategory(fullyCate);
            for(Question question : subCate){
                idQuestionInCate.add(question.id);
            }
            appear();
        }
    }
    public void appear() {
        int numberQues = idQuestionInCate.size();
        for( i = 0; i< (numberQues / 10 + 1); i++) {
            button[i] = new Button(String.valueOf(i+1)) ;
            button[i].setMnemonicParsing(false);
            button[i].setPrefWidth(38);
            button[i].setPrefHeight(41);
            button[i].setFont(Font.font(15));
            button[i].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #d1d1d1; -fx-cursor: hand");
            button[i].setTextFill(Paint.valueOf("#009FE5"));

            int finalI = i;
            button[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    button[finalI].setStyle("-fx-background-color: #FF0000; -fx-border-color: #d1d1d1; -fx-cursor: hand");
                }
            });
            button[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    button[finalI].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #d1d1d1; -fx-cursor: hand");
                }
            });
            int k = i ;
            button[i].setOnAction(event -> {
                action(k);
            });
            numberHBox.getChildren().add(button[i]) ;
        }

        numberHBox.setSpacing(1);

        for(i = 0; i < ((10 < numberQues)? 10 : numberQues) ; i++) {
            VBox vBox = new VBox() ;
            vBox.setPrefWidth(905);
            vBox.setPrefHeight(46);
            vBox.setMaxHeight(Double.POSITIVE_INFINITY);
            vBox.setMaxWidth(Double.POSITIVE_INFINITY);
            vBox.setMinHeight(Double.NEGATIVE_INFINITY);
            vBox.setMinWidth(Double.NEGATIVE_INFINITY);

            HBox hBox = new HBox() ;
            hBox.setPrefWidth(46);
            hBox.setPrefWidth(905);
            hBox.setStyle("-fx-background-color: #FFFFFF");

            Image image = new Image(getClass().getResourceAsStream("/Img.img/3.png")) ;
            ImageView threeDots = new ImageView(image) ;
            threeDots.setFitWidth(20);
            threeDots.setFitHeight(20);
            threeDots.setPreserveRatio(true);
            threeDots.setPickOnBounds(true);
            HBox.setMargin(threeDots,new Insets(8,0,0,20));
            hBox.getChildren().add(threeDots) ;

            Question question = QuestionManage.questionsList.get(idQuestionInCate.get(i));
            Label questionLabel = new Label(question.title) ;
            questionLabel.setPrefHeight(40);
            questionLabel.setPrefWidth(720);
            questionLabel.setFont(Font.font(17));
            HBox.setMargin(questionLabel,new Insets(0,0,0,18));
            hBox.getChildren().add(questionLabel) ;
            Color color = Color.web("#d1d1d1") ;
            BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5)); // set kích thước và màu cho border
            Border border = new Border(borderStroke);

            vBox.getChildren().add(hBox) ;
            vBox.setBorder(border);
            questionVBox.getChildren().add(vBox) ;

            menuPane.setPrefHeight(552+i*42);
        }

        ObservableList<Integer> number = FXCollections.observableArrayList() ;
        for(int i =1 ;i <= numberQues ;i++)
            number.add(new Integer(i)) ;
        comboBox.setItems(number);
        comboBox.setValue(0);

        count++ ;
    }

    public void action(int i) {
        int numQues = idQuestionInCate.size();
        for (int j = 0; j < (numQues / 10) + 1; j++) {
            button[j].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #d1d1d1");
            button[j].setTextFill(Paint.valueOf("#009FE5"));
            int finalJ = j;
            button[j].setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    button[finalJ].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #d1d1d1");
                }
            });
        }
        button[i].setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button[i].setStyle("-fx-background-color: #009FE5; -fx-border-color: #d1d1d1");
            }
        });
        button[i].setStyle("-fx-background-color: #009FE5; -fx-border-color: #d1d1d1");
        button[i].setTextFill(Paint.valueOf("#FFFFFF"));
        h = i ;
        questionVBox.getChildren().clear();

        int dem = 0 ;
        for (int h = 0; h != i; h++)
            dem = dem + 10;
        for (k = dem; k < dem + 10; k++) {
            VBox vBox = new VBox();
            vBox.setPrefWidth(905);
            vBox.setPrefHeight(46);
            vBox.setMaxHeight(Double.POSITIVE_INFINITY);
            vBox.setMaxWidth(Double.POSITIVE_INFINITY);
            vBox.setMinHeight(Double.NEGATIVE_INFINITY);
            vBox.setMinWidth(Double.NEGATIVE_INFINITY);

            HBox hBox = new HBox();
            hBox.setPrefWidth(46);
            hBox.setPrefWidth(905);
            hBox.setStyle("-fx-background-color: #FFFFFF");

            Image image = new Image(getClass().getResourceAsStream("/Img.img/3.png"));
            ImageView threeDots = new ImageView(image);
            threeDots.setFitWidth(20);
            threeDots.setFitHeight(20);
            threeDots.setPreserveRatio(true);
            threeDots.setPickOnBounds(true);
            HBox.setMargin(threeDots, new Insets(8, 0, 0, 20));
            hBox.getChildren().add(threeDots);

            if(k >= idQuestionInCate.size())     break;
            Question question = QuestionManage.questionsList.get(idQuestionInCate.get(k));
            Label questionLabel = new Label(question.title);
            questionLabel.setPrefHeight(40);
            questionLabel.setPrefWidth(720);
            questionLabel.setFont(Font.font(17));
            HBox.setMargin(questionLabel, new Insets(0, 0, 0, 18));
            hBox.getChildren().add(questionLabel);
            Color color = Color.web("#d1d1d1");
            BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5));
            Border border = new Border(borderStroke);

            vBox.getChildren().add(hBox);
            vBox.setBorder(border);
            questionVBox.getChildren().add(vBox);

            menuPane.setPrefHeight(552 + 10*40);
        }
    }


    public void selectNumberQuesToQuiz(){
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int numberQuesRandom = (int)comboBox.getValue();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Do you want to import random " + numberQuesRandom + " question");

                if(alert.showAndWait().get() == ButtonType.OK){
                    Collections.shuffle(idQuestionInCate);
//                    ArrayList<Integer> tmp = (ArrayList<Integer>) idQuestionInCate.subList(0, numberQuesRandom - 1);
                    int demtam = 0;
                    for(int t : idQuestionInCate){
                        if(demtam == numberQuesRandom )      break;
                        Question question = QuestionManage.questionsList.get(t);
                        QuizzesManage quizzesManage = null;
                        try {
                            quizzesManage = new QuizzesManage();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            if (LibraryForUs.checkQuestionExistOnQuiz(question, currentQuiz) == false) {
                                currentQuiz.quizQuestions =
                                        currentQuiz.quizQuestions + "," + Integer.toString(question.id);
                                quizzesManage.editingQuiz(currentQuiz.id, currentQuiz.quizQuestions, "will fix");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        demtam++;
                    }
                }
            }
        });
    }

    public void buttonLeft() {
        if(h>0)
            action(h-1);
    }
    public void buttonRight() {
        if(h<9)
            action(h+1);
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGUIHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
