package javaFx.bundle.view;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Question;
import model.QuestionManage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionList implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label categoryLabel;
    @FXML
    private VBox questionBox;
    @FXML
    private VBox editBox;

    private Button click;  // delete when done
    @FXML
    private ImageView dropDown;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryLabel.setText("  " + TreeView.currentCategory);

//        enable question from category
        try {
            QuestionManage questionManage = new QuestionManage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int dem = 0;
        for (Question question : QuestionManage.questionsList) {
            if (question.category.equals(TreeView.currentCategory)) {
                //                Draw question in checkBox
                HBox hbox = new HBox();
                Image threeDots = new Image("3.png");
                ImageView imageView = new ImageView(threeDots);
                imageView.setFitWidth(10);
                imageView.setFitHeight(10);
                CheckBox checkBox = new CheckBox(question.title);

                checkBox.setGraphic(imageView);

                checkBox.setFont(Font.font(20));
                hbox.getChildren().add(checkBox);
                if(dem % 2 == 0){
                    hbox.setBackground(Background.fill(Color.GRAY));
                }

                questionBox.getChildren().add(hbox);


//                Draw button edit beside
                HBox hBox = new HBox();
                Text text = new Text("Edit   ");
                text.setFont(Font.font(20));
                Color color = Color.web("#00a2e9");
                text.setFill(color);
                ImageView imageView1 = new ImageView("arrowblue.png");

                hBox.getChildren().addAll(text, imageView1);
                if(dem % 2 ==0){
                    hBox.setBackground(Background.fill(Color.BROWN));
                }

                Button button = new Button();
                button.setGraphic(hBox);
                button.setBackground(Background.fill(null));
//                set switch to EditQuestion when we click Edit
                button.setOnMouseClicked(e -> {
                    try {
                        switchToEditQuestion(e);
                    } catch (IOException even) {
                        even.printStackTrace();
                    }
                });

                editBox.getChildren().add(button);
                dem ++;
            }
        }
    }


    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("QuestionList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddingQuestion.fxml"));
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
    public void switchToEditQuestion(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EditQuestion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCategory(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddingCategory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToImportQuestion(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ImportQuestion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}