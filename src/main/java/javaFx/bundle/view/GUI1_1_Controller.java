package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Quiz;
import model.QuizzesManage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUI1_1_Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<Label> labelQuizzes = new ArrayList<>();
//    public static String currentQuiz = new String("Default");
    static Quiz currentQuiz;
    static boolean isSelectedCategory = false;
    @FXML
    private Label label;
    @FXML
    private VBox vBoxQuiz;
    @FXML
    private Label labelUserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUserName.setText(QuizTestApplication.currentUser);

        vBoxQuiz.getChildren().clear();
        labelQuizzes.clear();

        QuizzesManage quizzesManage ;
        try {
            quizzesManage = new QuizzesManage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vBoxQuiz.setSpacing(30);

        HBox topSpacer1 = new HBox();
        HBox topSpacer2 = new HBox();
        vBoxQuiz.getChildren().addAll(topSpacer1, topSpacer2);

        int i = 0;
        for (Quiz quiz : QuizzesManage.quizzesList){
            HBox hBox = new HBox();
            Image image = new Image(getClass().getResource("/Img.img/fileIcon.png").toExternalForm());
            ImageView imageView = new ImageView(image);
            Label spacer = new Label("                              ");
            Label newLabel = new Label("  " + quiz.quizName);

            newLabel.setFont(Font.font(20));
            labelQuizzes.add(newLabel);

            int finalI = i;
            labelQuizzes.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    currentQuiz = quiz;
                    try {
                        root = FXMLLoader.load(getClass().getResource("gui6.1.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });

            labelQuizzes.get(i).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    labelQuizzes.get(finalI).setStyle("-fx-cursor: hand;");
                    labelQuizzes.get(finalI).setUnderline(true);
                }
            });

            labelQuizzes.get(i).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    labelQuizzes.get(finalI).setUnderline(false);
                }
            });

            hBox.getChildren().addAll(spacer,imageView,labelQuizzes.get(i));
            vBoxQuiz.getChildren().add(hBox);
            i++;
        }
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

}
