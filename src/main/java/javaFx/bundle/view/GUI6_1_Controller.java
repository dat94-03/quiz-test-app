package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI6_1_Controller implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label quizName;
    @FXML
    private Button openButton ;
    @FXML
    private Label labelTimeLimit;
    @FXML
    private Label labelNameQuiz;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelNameQuiz.setText(GUI1_1_Controller.currentQuiz.quizName);
        String s1 = String.format("%.2f",GUI1_1_Controller.currentQuiz.timeLimit / 60.0);
        labelTimeLimit.setText("Time limit: " + s1 + " minutes");
        quizName.setText(GUI1_1_Controller.currentQuiz.quizName);
    }
    public void switchToGUI6_2a(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("gui6.2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchTo7(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("Gui7_3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openChildStage1() {
        // Lấy Stage cha từ Button
        Stage parentStage = (Stage) openButton.getScene().getWindow();

        // Tạo Stage con
        Stage childStage = new Stage();
        childStage.setTitle("");
        AnchorPane root = new AnchorPane() ;
        root.setPrefHeight(370);
        root.setPrefWidth(760);
        root.setMaxHeight(Double.POSITIVE_INFINITY);
        root.setMaxWidth(Double.POSITIVE_INFINITY);
        root.setMinHeight(Double.NEGATIVE_INFINITY);
        root.setMinWidth(Double.NEGATIVE_INFINITY);
        root.setStyle("-fx-background-color: #e7e7e7");

        Line line1 = new Line() ;
        line1.setEndX(524.3333740234375);
        line1.setLayoutX(206);
        line1.setLayoutY(30);
        line1.setStartX(-179.66668701171875);
        line1.setStrokeWidth(2.0);
        line1.setStroke(Color.web("#aeaeae"));
        root.getChildren().add(line1) ;

        Line line2 = new Line() ;
        line2.setEndX(525.3333740234375);
        line2.setLayoutX(206);
        line2.setLayoutY(103);
        line2.setStartX(-177.59999084472656);
        line2.setStroke(Color.web("#aeaeae"));
        line2.setStrokeWidth(2.0);
        root.getChildren().add(line2) ;

        Label label2 = new Label("Start attempt");
        label2.setLayoutX(39.0);
        label2.setLayoutY(50.0);
        label2.setTextFill(Color.web("#303030"));
        label2.setFont(new Font("Arial", 28.0));
        root.getChildren().add(label2) ;

        Image image = new Image(getClass().getResourceAsStream("/Img.img/close.png"));
        ImageView close = new ImageView(image) ;
        close.setFitHeight(26);
        close.setFitWidth(38);
        close.setLayoutX(699);
        close.setLayoutY(55);
        close.setPreserveRatio(true);
        close.setPickOnBounds(true);
        root.getChildren().add(close) ;

        Label label = new Label();
        label.setLayoutX(39.0);
        label.setLayoutY(113.0);
        label.setPrefHeight(46.0);
        label.setPrefWidth(149.0);
        label.setText("Time limit");
        label.setTextFill(Color.web("#c02424"));
        Font font = Font.font("Bell MT Bold", FontWeight.BOLD, 26.0);
        label.setFont(font);
        root.getChildren().add(label) ;

        String s1 = String.format("%.2f",GUI1_1_Controller.currentQuiz.timeLimit / 60.0);
        Text text2 = new Text("Your attempt will hava a time limit of " +
                s1 +
                " minute. When you start, the timer will begin to count down and cannot be paused. " +
                "You must finish your attempt before it expires. Are you sure you wish start now ?") ;
        text2.setLayoutX(30);
        text2.setLayoutY(198);
        text2.setFill(Color.web("#404040"));
        text2.setStrokeType(StrokeType.OUTSIDE);
        text2.setStrokeWidth(0);
        text2.setFont(Font.font(20));
        text2.setWrappingWidth(715.5999755859375);
        root.getChildren().add(text2) ;


        Line line3 = new Line();
        line3.setEndX(581.7332763671875);
        line3.setLayoutX(154);
        line3.setLayoutY(277);
        line3.setStartX(-133.8666870117188);
        line3.setStroke(Color.web("#bfbfbf"));
        line3.setStrokeWidth(2.0);
        root.getChildren().add(line3) ;

        Button startButton = new Button("START ATTEMPT");
        startButton.setLayoutX(61.0);
        startButton.setLayoutY(292.0);
        startButton.setMnemonicParsing(false);
        startButton.setPrefHeight(59.0);
        startButton.setPrefWidth(217.0);
        startButton.setStyle("-fx-background-color: #C02424;");
        startButton.setTextFill(Color.WHITE);
        Font font2 = Font.font("Arial", 20.0);
        startButton.setFont(font2);
        startButton.setCursor(Cursor.HAND);
        startButton.setOnAction(event -> {
            try {
                childStage.close();
                parentStage.setOpacity(1.0);
                Parent root1 = FXMLLoader.load(getClass().getResource("Gui7_3.fxml"));
                Stage stage = (Stage) openButton.getScene().getWindow();
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(startButton) ;

        Button cancelButton = new Button("CANCEL");
        cancelButton.setLayoutX(286.0);
        cancelButton.setLayoutY(292.0);
        cancelButton.setMnemonicParsing(false);
        cancelButton.setPrefHeight(59.0);
        cancelButton.setPrefWidth(134.0);
        cancelButton.setStyle("-fx-background-color: #009FE5;");
        cancelButton.setTextFill(Color.WHITE);
        cancelButton.setFont(font2);
        cancelButton.setCursor(Cursor.HAND);
        cancelButton.setOnAction(event -> {
            childStage.close();
            // Khôi phục trạng thái của Stage cha
            parentStage.setOpacity(1.0);
        });
        root.getChildren().add(cancelButton) ;

        Button exportButton = new Button("EXPORT") ;
        exportButton.setLayoutX(590);
        exportButton.setLayoutY(292.0);
        exportButton.setMnemonicParsing(false);
        exportButton.setPrefHeight(59.0);
        exportButton.setPrefWidth(134.0);
        exportButton.setFont(font2);
        exportButton.setStyle("-fx-background-color: #59da95");
        exportButton.setTextFill(Color.web("#ececec"));
        exportButton.setFont(Font.font(18));
        exportButton.setCursor(Cursor.HAND);
        exportButton.setOnAction(event -> {
            try {
                childStage.close();
                parentStage.setOpacity(1.0);
                Parent root1 = FXMLLoader.load(getClass().getResource("GUI8.fxml"));
                Stage stage = (Stage) openButton.getScene().getWindow();
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(exportButton) ;

        // Thiết lập Stage con làm cửa sổ modal và kết nối với Stage cha
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.initOwner(parentStage);

        // Làm mờ Stage cha
//        parentStage.setOpacity(0.5);

        Scene scene = new Scene(root,760,370);
        childStage.setScene(scene);
        childStage.show();
    }
    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}