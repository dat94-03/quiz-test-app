package javaFx.bundle.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUI63 implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String selectCate = new String("Default");
    private String fullyCate = new String();
    private Quiz currentQuiz = GUI1_1_Controller.currentQuiz;

    private ArrayList<CheckBox> listCheckBox = new ArrayList<>();
    private ArrayList<Question> listQuestion = new ArrayList<>();
//    public static ArrayList<Question> questionsForQuiz = new ArrayList<>();
    @FXML
    private AnchorPane anchorPane ;
    @FXML
    private VBox questionBox ;
    @FXML
    private VBox zoomBox ;
    @FXML
    public javafx.scene.control.TreeView treeView;
    @FXML
    public Button button;
    @FXML
    private CheckBox showQuesSubCate;
    @FXML
    private Label labelNameQuiz;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelNameQuiz.setText(currentQuiz.quizName + "  /  Edit Quiz");

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
                try {
                    selectCate = selectedItem.getValue();
                    fullyCate = LibraryForUs.getFullyCategory(selectedItem);
                    button.setText(selectCate);
                    addQuestionBank();
                    displayTreeViewOff();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void addQuestionBank() throws IOException {
        QuizzesManage quizzesManage = new QuizzesManage();
        questionBox.getChildren().clear();
        int i = 1;
        QuestionManage questionManage = new QuestionManage();
         for(Question question : QuestionManage.questionsList) {
             if(question.category.equals(fullyCate)){
                 addToVBox(question, i);
                 i++;
             }
         }

         addAddSelectedButton();
    }

    public void showQuestionFromSubCate() throws IOException {
        questionBox.getChildren().clear();
        listCheckBox.clear();
        listQuestion.clear();

        if(showQuesSubCate.isSelected()){
            int i = 1;
            QuestionManage questionManage = new QuestionManage();
            ArrayList<Question> subCate = questionManage.getQuestionsOfCategoryAndSubcategory(fullyCate);;

            for(Question question : subCate){
                addToVBox(question, i);
                i++;
            }
            addAddSelectedButton();
        }
        else {
            int i = 1;
            for(Question question : QuestionManage.questionsList) {
                if(question.category.equals(fullyCate)){
                    addToVBox(question, i);
                    i++;
                }
            }

            addAddSelectedButton();
        }

    }

    private void addToVBox(Question question, int i) throws IOException {
        listQuestion.add(question);

        VBox vBox = new VBox();
        vBox.setPrefWidth(924);
        vBox.setPrefHeight(45);
        vBox.setMaxHeight(Double.POSITIVE_INFINITY);
        vBox.setMaxWidth(Double.POSITIVE_INFINITY);
        vBox.setMinHeight(Double.NEGATIVE_INFINITY);
        vBox.setMinWidth(Double.NEGATIVE_INFINITY);

        HBox hBox = new HBox();
        hBox.setPrefHeight(45);
        hBox.setPrefWidth(924);

        Image image1 = new Image(getClass().getResourceAsStream("/Img.img/add.png"));
        ImageView add = new ImageView(image1);
        add.setPreserveRatio(true);
        add.setPickOnBounds(true);
        add.setFitHeight(19);
        add.setFitWidth(22);
        HBox.setMargin(add, new Insets(10, 0, 0, 0));
        hBox.getChildren().add(add);

        CheckBox checkBox = new CheckBox();
        checkBox.setMnemonicParsing(false);
        checkBox.setFont(Font.font(14));
        if(LibraryForUs.checkQuestionExistOnQuiz(question, currentQuiz) == true){
            checkBox.setSelected(true);
        }
        listCheckBox.add(checkBox);
        int finalI = i;
        HBox.setMargin(listCheckBox.get(i - 1), new Insets(8, 0, 0, 8));
        hBox.getChildren().add(checkBox);

        Image image2 = new Image(getClass().getResourceAsStream("/Img.img/3.png"));
        ImageView threedot = new ImageView(image2);
        threedot.setPickOnBounds(true);
        threedot.setPreserveRatio(true);
        threedot.setFitHeight(18);
        threedot.setFitWidth(26);
        HBox.setMargin(threedot, new Insets(14, 0, 0, 2));
        hBox.getChildren().add(threedot);

//                 For question text
        Label questionLabel = new Label(" ");
        questionLabel.setText(question.title);
        questionLabel.setFont(Font.font(18));
//                 Text text1 = new Text(question.title + " ");
//                 text1.setFont(Font.font("System", FontWeight.BOLD, 18));
//
//                 Text text2 = new Text(question.title);
//                 text2.setFont(Font.font("System", FontWeight.NORMAL, 18));
//
//                 TextFlow textFlow = new TextFlow(text1, text2);
//                 questionLabel.setGraphic(textFlow);
        questionLabel.setPrefWidth(812);
        questionLabel.setPrefHeight(46);
        HBox.setMargin(questionLabel,new Insets(0,0,0,10));
        hBox.getChildren().add(questionLabel) ;
//              end question text

        Image image3 = new Image(getClass().getResourceAsStream("/Img.img/magnifying glass.png")) ;
        ImageView zoom = new ImageView(image3) ;
        zoom.setPreserveRatio(true);
        zoom.setPickOnBounds(true);
        zoom.setFitHeight(17);
        zoom.setFitWidth(18);
        Label zoomLabel = new Label() ;
        zoomLabel.setFont(Font.font(34));
        zoomLabel.setPrefWidth(29);
        zoomLabel.setGraphic(zoom);
        hBox.getChildren().add(zoomLabel) ;

        if(i%2 == 0) {
            hBox.setStyle("-fx-background-color: #E6E6E6");
        }
        else if (i%2 == 1) {
            hBox.setStyle("-fx-background-color: #FFFFFF");
        }
        vBox.getChildren().add(hBox) ;
        VBox.setMargin(vBox,new Insets(0,0,0,5));
        questionBox.getChildren().add(vBox) ;
        anchorPane.setPrefHeight(760+110+(i-4)*45) ;
    }

    private void addAddSelectedButton() {
        VBox vBox2 = new VBox();
        vBox2.setPrefHeight(100);
        vBox2.setPrefWidth(924);
        vBox2.setMaxHeight(Double.POSITIVE_INFINITY);
        vBox2.setMaxWidth(Double.POSITIVE_INFINITY);
        vBox2.setMinHeight(Double.NEGATIVE_INFINITY);
        vBox2.setMinWidth(Double.NEGATIVE_INFINITY);

        Label buttonLabel = new Label() ;
        buttonLabel.setPrefWidth(924);
        buttonLabel.setPrefHeight(27);
        buttonLabel.setStyle("-fx-background-color: #DDDDDD");
        VBox.setMargin(buttonLabel,new Insets(4,0,0,0));
        vBox2.getChildren().add(buttonLabel) ;

        Button buttonAddQuiz = new Button() ;
        buttonAddQuiz.setMnemonicParsing(false);
        buttonAddQuiz.setPrefHeight(41);
        buttonAddQuiz.setPrefWidth(342);
        buttonAddQuiz.setStyle("-fx-background-color: #0073A5");
        buttonAddQuiz.setText("ADD SELECTED QUESTIONS TO THE QUIZ ");
        buttonAddQuiz.setTextFill(Color.WHITE);
        buttonAddQuiz.setFont(Font.font(17)) ;
        buttonAddQuiz.setCursor(Cursor.HAND);
        buttonAddQuiz.setOnAction(event -> {
            try {
                addSelectedQuestionToQuiz(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        VBox.setMargin(buttonAddQuiz,new Insets(10,0,0,0));
        vBox2.getChildren().add(buttonAddQuiz) ;
        questionBox.getChildren().add(vBox2) ;

        count ++ ;

    }

    public void addSelectedQuestionToQuiz(ActionEvent event) throws IOException {
        QuizzesManage quizzesManage = new QuizzesManage();
        int i = 0;
        for(CheckBox checkBox : listCheckBox){
            if(checkBox.isSelected()) {
                try {
                    if (LibraryForUs.checkQuestionExistOnQuiz(listQuestion.get(i), currentQuiz) == false) {
                        currentQuiz.quizQuestions =
                                currentQuiz.quizQuestions + "," + Integer.toString(listQuestion.get(i).id);
                        quizzesManage.editingQuiz(currentQuiz.id, currentQuiz.quizQuestions, "will fix");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            i++;
        }

//        switch to GUI 6.4
        root = FXMLLoader.load(getClass().getResource("GUI6.4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGUIHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
