package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

public class GUI64 implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Quiz currentQuiz = GUI1_1_Controller.currentQuiz;
    private ArrayList<Integer> idQuestionsForQuiz ;
    private ArrayList<Integer> idQuestionDelete =  new ArrayList<>();
    private ArrayList<ImageView> binImageViews = new ArrayList<>();
    private ArrayList<VBox> vBoxesQuestion = new ArrayList<>();
    @FXML
    private VBox questionBox;
    @FXML
    private AnchorPane menuPane ;
    @FXML
    private CheckBox shuffleQuestion;
    @FXML
    private Text textNumQuestions;
    @FXML
    private Text textMark;
    @FXML
    private Label labelQuizName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelQuizName.setText(currentQuiz.quizName);
        idQuestionsForQuiz = LibraryForUs.getQuestionIdFromQuiz(currentQuiz);
        textNumQuestions.setText("" + idQuestionsForQuiz.size());
        double mark =  idQuestionsForQuiz.size() * 1.0;
        textMark.setText(String.format("%.2f",mark));

        QuestionManage questionManage = null;
        try {
            questionManage = new QuestionManage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        drawQuestionInVBox();
    }

    public void saveQuestions(ActionEvent event) throws IOException {
        QuestionManage questionManage = new QuestionManage();
        for(int i : idQuestionDelete){
            LibraryForUs.deleteQuestionOnQuiz(QuestionManage.questionsList.get(i), currentQuiz);
        }

//        switch to scene 6.1
        root = FXMLLoader.load(getClass().getResource("gui6.1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setShuffleQuestion(ActionEvent event) throws IOException {
        if(shuffleQuestion.isSelected()){
            Collections.shuffle(idQuestionsForQuiz);
            String tmp = LibraryForUs.changeArrayListToString(idQuestionsForQuiz);
            drawQuestionInVBox();

            QuizzesManage quizzesManage = new QuizzesManage();
            quizzesManage.editingQuiz(currentQuiz.id, tmp, "will fix");

        }
    }

    public void drawQuestionInVBox(){
        questionBox.getChildren().clear();
        idQuestionDelete.clear();
        binImageViews.clear();
        vBoxesQuestion.clear();
        int i = 1;
        for(int j : idQuestionsForQuiz) {
            VBox vBox = new VBox() ;
            vBox.setPrefHeight(56);
            vBox.setPrefWidth(915);
            vBox.setMaxHeight(Double.POSITIVE_INFINITY);
            vBox.setMaxWidth(Double.POSITIVE_INFINITY);
            vBox.setMinHeight(Double.NEGATIVE_INFINITY);
            vBox.setMinWidth(Double.NEGATIVE_INFINITY);


            HBox hBox = new HBox();
            hBox.setPrefWidth(915);
            hBox.setPrefHeight(48);
            hBox.setStyle("-fx-background-color: #e7e7e7");

            Image image1 = new Image(getClass().getResourceAsStream("/Img.img/add.png"));
            ImageView add = new ImageView();
            add.setPickOnBounds(true);
            add.setPreserveRatio(true);
            add.setImage(image1);
            add.setFitHeight(19);
            add.setFitWidth(25);
            HBox.setMargin(add, new Insets(5, 0, 0, 5));
            hBox.getChildren().add(add);

            Label numberLabel = new Label(String.valueOf(i));
            numberLabel.setAlignment(Pos.CENTER);
            numberLabel.setPrefWidth(40);
            numberLabel.setPrefHeight(31);
            numberLabel.setStyle("-fx-background-color: #b1b1b1");
            numberLabel.setFont(Font.font(18));
            HBox.setMargin(numberLabel, new Insets(8, 0, 0, 5));
            hBox.getChildren().add(numberLabel);

            Image image2 = new Image(getClass().getResourceAsStream("/Img.img/3.png"));
            ImageView threedot = new ImageView();
            threedot.setPreserveRatio(true);
            threedot.setPickOnBounds(true);
            threedot.setImage(image2);
            threedot.setFitHeight(23);
            threedot.setFitWidth(30);
            HBox.setMargin(threedot, new Insets(12, 0, 0, 10));
            hBox.getChildren().add(threedot);

            Image image3 = new Image(getClass().getResourceAsStream("/Img.img/setting.png"));
            ImageView setting = new ImageView(image3);
            setting.setPickOnBounds(true);
            setting.setPreserveRatio(true);
            setting.setFitHeight(20);
            setting.setFitWidth(31);
            HBox.setMargin(setting, new Insets(17, 0, 0, 6));
            hBox.getChildren().add(setting);

            Label questionLabel = new Label(QuestionManage.questionsList.get(j).title);
            questionLabel.setFont(Font.font(18));
            questionLabel.setPrefHeight(46);
            questionLabel.setPrefWidth(531);
            questionLabel.setFont(Font.font(18));
            HBox.setMargin(questionLabel, new Insets(1, 0, 0, 10));
            hBox.getChildren().add(questionLabel);

            Pane pane = new Pane();
            pane.setPrefHeight(48);
            pane.setPrefWidth(81);
            hBox.getChildren().add(pane);

            Image image4 = new Image(getClass().getResourceAsStream("/Img.img/magnifying glass.png"));
            ImageView zoom = new ImageView(image4);
            zoom.setPickOnBounds(true);
            zoom.setPreserveRatio(true);
            zoom.setFitWidth(23);
            zoom.setFitHeight(21);
            HBox.setMargin(zoom, new Insets(12, 8, 0, 0));
            hBox.getChildren().add(zoom);

            Image image5 = new Image(getClass().getResourceAsStream("/Img.img/bin.png"));
            ImageView bin = new ImageView(image5) ;
            bin.setPickOnBounds(true);
            bin.setPreserveRatio(true);
            bin.setFitHeight(22);
            bin.setFitWidth(23);
            binImageViews.add(bin);
            int finalI = i;
            binImageViews.get(i - 1).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    idQuestionDelete.add(j);
                    questionBox.getChildren().remove(vBoxesQuestion.get(finalI - 1));
//                    System.out.println("i la " + finalI + "j la " + j);
                }
            });
            binImageViews.get(i - 1).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    binImageViews.get(finalI - 1).setStyle("-fx-cursor: hand;");
                }
            });
            HBox.setMargin(binImageViews.get(i - 1),new Insets(12,10,0,5));
            hBox.getChildren().add(binImageViews.get(i - 1)) ;

            TextField textField = new TextField() ;
            textField.setPrefHeight(30);
            textField.setPrefWidth(42);
            textField.setStyle("-fx-background-color: #FFFFFF");
            textField.setText("1.00");
            HBox.setMargin(textField,new Insets(8,0,0,0));
            hBox.getChildren().add(textField) ;

            Label label = new Label() ;
            label.setPrefWidth(24);
            label.setPrefHeight(30);
            label.setStyle("-fx-background-color: #FFFFFF");
            Image image6 = new Image(getClass().getResourceAsStream("/Img.img/edit.png")) ;
            ImageView edit = new ImageView(image6) ;
            edit.setPickOnBounds(true);
            edit.setPreserveRatio(true);
            edit.setFitHeight(20);
            edit.setFitWidth(18);
            label.setGraphic(edit);
            HBox.setMargin(label,new Insets(8,0,0,0));
            hBox.getChildren().add(label) ;

            Label spacing = new Label() ;
            spacing.setFont(Font.font(5));
            spacing.setStyle("-fx-background-color: #FFFFFF");
            spacing.setPrefWidth(915);

            vBox.getChildren().add(hBox) ;
            vBox.getChildren().add(spacing) ;
            vBoxesQuestion.add(vBox);

            questionBox.getChildren().add(vBoxesQuestion.get(i - 1)) ;
            i++;
        }
    }

}


