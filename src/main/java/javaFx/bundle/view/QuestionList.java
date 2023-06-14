package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Question;
import model.QuestionManage;
//import model.QuestionManage;

import java.io.IOException;

public class QuestionList {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button chooseAFileButton;
    private Button arrowButton;
    @FXML
    private VBox questionBox;
    @FXML
    private CheckBox checkBox1;

    @FXML
    private VBox editBox;

    private Button click;  // delete when done




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
    public void openFile(ActionEvent event) throws IOException {
        chooseAFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Word Documents", "*.docx");
            fileChooser.getExtensionFilters().add(filter);
            java.io.File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                String tmp = selectedFile.getAbsolutePath();
                System.out.println(tmp);
                QuestionManage questionManage = null;
                try {
                    questionManage = new QuestionManage();
                    questionManage.importQuestions(selectedFile.getAbsolutePath(), "test ter");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public void setClick(ActionEvent event) throws IOException {
        for(Question question : QuestionManage.questionsList){
            if(question.category.equals("test ter")){
//                Draw question in checkBox
                Image threedots = new Image("E:\\btl oop\\quiz-test-app\\src\\main\\resources\\Img.img\\3.png");
                ImageView imageView = new ImageView(threedots);
                imageView.setFitWidth(10);
                imageView.setFitHeight(10);
                CheckBox checkBox = new CheckBox(question.title);
                checkBox.setGraphic(imageView);
                checkBox.setFont(Font.font(20));
                questionBox.getChildren().add(checkBox);

//
//                Draw button edit beside
                HBox hBox = new HBox();
                Text text = new Text("Edit   ");
                text.setFont(Font.font(20));
                Color color = Color.web("#00a2e9");
                text.setFill(color);
                ImageView imageView1 = new ImageView("E:\\btl oop\\quiz-test-app\\src\\main\\resources\\Img.img\\arrowbluewhitebg.png");

                hBox.getChildren().addAll(text, imageView1);
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
            }

        }
    }

}