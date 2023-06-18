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
import javafx.scene.layout.*;
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

    public static Question q ;

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
//        remove when editquestion done
        QuestionManage qm = null;
        try {
            qm = new QuestionManage();
            for(Question ques : QuestionManage.questionsList){
                if (ques.category.equals(TreeView.fullyCategory) && (ques.title.equals("") == false)){
                    q = ques;
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        categoryLabel.setText("  " + TreeView.currentCategory);

//        enable question from category
        try {
            QuestionManage questionManage = new QuestionManage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int dem = 0;
        for (Question question : QuestionManage.questionsList) {
            if (question.category.equals(TreeView.fullyCategory) && (question.title.equals("") == false)) {
                HBox hBoxrow = new HBox();

//                Draw question in checkBox
                HBox hboxQuestion = new HBox();
                Image threeDots = new Image("3.png");
                ImageView imageView = new ImageView(threeDots);
                imageView.setFitWidth(10);
                imageView.setFitHeight(10);
                CheckBox checkBox = new CheckBox(question.title);

                checkBox.setGraphic(imageView);

                checkBox.setFont(Font.font(20));
//                checkBox.setPrefWidth(150);

                hboxQuestion.getChildren().add(checkBox);
                if(dem % 2 == 0){
                    BackgroundFill backgroundFill = new BackgroundFill(Color.web("#CCCCCC"), null, null);
                    Background background = new Background(backgroundFill);
                    hboxQuestion.setBackground(background);
                }
                hboxQuestion.setPrefWidth(800);
                HBox.setHgrow(hboxQuestion, Priority.NEVER);

//                Draw button edit beside
                HBox hBoxEdit = new HBox();
                Text text = new Text("Edit   ");
                text.setFont(Font.font(20));
                Color color = Color.web("#00a2e9");
                text.setFill(color);
                ImageView imageView1 = new ImageView("arrowblue.png");

                hBoxEdit.getChildren().addAll(text, imageView1);

                Button button = new Button();
                button.setGraphic(hBoxEdit);
                if(dem % 2 ==0){
                    BackgroundFill backgroundFill2 = new BackgroundFill(Color.web("#CCCCCC"), null, null);
                    Background background2 = new Background(backgroundFill2);
                    button.setBackground(background2);
                }
                else {
                    button.setBackground(Background.fill(null));
                }
//                set switch to EditQuestion when we click Edit
                button.setOnMouseClicked(e -> {
                    try {
                        switchToEditQuestion(e);
                    } catch (IOException even) {
                        even.printStackTrace();
                    }
                });

//                add component
                hBoxrow.getChildren().addAll(hboxQuestion, button);
                questionBox.getChildren().add(hBoxrow);

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