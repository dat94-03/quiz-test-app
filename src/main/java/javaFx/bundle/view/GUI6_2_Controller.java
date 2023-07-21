package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI6_2_Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    String[] choices = {" a new question", " from question bank", " a random question"};

    @FXML
    private Label label;
    @FXML
    private ListView<Label> addListView;
    @FXML
    private Label labelAdd;
    @FXML
    private Label labelNameQuiz;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("Editing quiz: " + GUI1_1_Controller.currentQuiz.quizName);
        labelNameQuiz.setText(GUI1_1_Controller.currentQuiz.quizName + "  /  Edit Quiz");

        boolean flag = false;
        Label label1 = getItem(choices[0]);
        Label label2 = getItem(choices[1]);
        Label label3 = getItem(choices[2]);

        addListView.getItems().addAll(label1, label2, label3);
    }

    public void selectItem() {
        addListView.setOnMouseClicked(mouseEvent -> {
            Label tmp =  addListView.getSelectionModel().getSelectedItem();
            if(tmp.getText().equals(" from question bank")){
                switchToScene("GUI6.3.fxml");
            }
            else if(tmp.getText().equals(" a random question")){
                switchToScene("GUI6.5.fxml");
            }
        });
    }
    public void changeColorLabel(){
        labelAdd.setTextFill(Color.GREEN);
        labelAdd.setStyle("-fx-cursor: hand;");
    }

    public void changeColorLabelOff(){
        labelAdd.setTextFill(Color.BLUE);
    }

    private Label getItem(String content){
        Image image = new Image(getClass().getResource("/Img.img/small-add.png").toExternalForm());;
        ImageView imageView = new ImageView(image);
        Label label = new Label(content);
        label.setGraphic(imageView);
        return label;
    }

    @FXML
    public void displayListView() {
        addListView.setVisible(true);
    }
    @FXML
    public void displayListViewOff(){
        addListView.setVisible(false);
    }

    private void switchToScene(String fxmlFile) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) addListView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToGUIHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
