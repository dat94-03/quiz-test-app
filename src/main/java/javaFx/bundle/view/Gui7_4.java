package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Gui7_4 implements Initializable {
    @FXML
    private VBox vBox;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchTo7_4(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Gui7_4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene1(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public HBox AQuestion (int i){
        // Create HBox
        HBox hbox = new HBox();
        hbox.setPrefWidth(730.0);
        hbox.setPrefHeight(204.0);
        hbox.setSpacing(10);

        // Create AnchorPane for question panel
        AnchorPane questionPane = new AnchorPane();
        questionPane.setStyle("-fx-border-color: #bfbcbc;");
        questionPane.setPrefWidth(120.0);
        questionPane.setPrefHeight(120.0);
        hbox.setMargin(questionPane,new Insets(0, 0,30,0));


        // Create labels for question panel
        Label questionTitle = new Label("Question "+ i);
        Font font = Font.font("Arial", FontWeight.BOLD, 18);
        questionTitle.setFont(font);
        questionTitle.setTextFill(Color.web("#cf1616"));


        Label statusLabel = new Label("Answered");
        statusLabel.setFont(Font.font(14.0));
        statusLabel.setId("answered"+i);

        Label markLabel = new Label("Mark out of 1.00");
        markLabel.setFont(Font.font(14.0));

        HBox flagHbox = new HBox();
        Image flagImg = new Image(getClass().getResource("/Img.img/flag2.png").toExternalForm());
        ImageView flagImgView = new ImageView(flagImg);
        flagImgView.setFitWidth(20);
        flagImgView.setFitHeight(20);
        Label flagLabel = new Label("Flag question");
        flagLabel.setFont(Font.font(14.0));
        flagHbox.setSpacing(2);
        flagHbox.setCursor(Cursor.HAND);
        flagHbox.setId("flag"+i);
        flagHbox.getChildren().addAll(flagImgView,flagLabel);

        // Add labels to question panel
        VBox questionBox = new VBox(8);
        questionBox.setPadding(new Insets(15));
        questionBox.getChildren().addAll(questionTitle, statusLabel, markLabel, flagHbox);
        questionPane.getChildren().add(questionBox);

        // Create AnchorPane for question content area
        AnchorPane contentPane = new AnchorPane();
        contentPane.setStyle("-fx-background-color: #E7F3F5;");
        contentPane.setPrefWidth(750.0);
        contentPane.setPrefHeight(204.0);

        // Create question label
        Label questionLabel = new Label("S7B23C6: Quang hợp diễn ra bình thường ở nhiệt độ trung bình là");
        questionLabel.setFont(Font.font(16.0));
        questionLabel.setWrapText(true);

        // Create answer radio buttons
        RadioButton optionA = new RadioButton("A. 15 đến 25 độ C");
        RadioButton optionB = new RadioButton("B. 10 đến 30 độ C");
        RadioButton optionC = new RadioButton("C. 20 đến 25 độ C");
        RadioButton optionD = new RadioButton("D. 15 đến 35 độ C");

        optionA.setFont(Font.font(14.0));
        optionB.setFont(Font.font(14.0));
        optionC.setFont(Font.font(14.0));
        optionD.setFont(Font.font(14.0));

        // Group radio buttons
        ToggleGroup answerGroup = new ToggleGroup();
        optionA.setToggleGroup(answerGroup);
        optionB.setToggleGroup(answerGroup);
        optionC.setToggleGroup(answerGroup);
        optionD.setToggleGroup(answerGroup);

        optionA.setOnAction(e -> handleAnswerSelection(i));
        optionB.setOnAction(e -> handleAnswerSelection(i));
        optionC.setOnAction(e -> handleAnswerSelection(i));
        optionD.setOnAction(e -> handleAnswerSelection(i));

        // Create a label answer
        Label aLabel = new Label("The correct answer is: ");
        Font font1 = Font.font("Arial", FontWeight.BOLD, 16);
        aLabel.setFont(font1);
        aLabel.setTextFill(Color.web("#cf1616"));


        // Add components to content pane
        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20.0));
        contentBox.getChildren().addAll(questionLabel, optionA, optionB, optionC, optionD,aLabel);
        contentPane.getChildren().add(contentBox);


        // Add panes to HBox
        hbox.getChildren().addAll(questionPane, contentPane);

        return hbox;

    }
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox vBoxNav;
    @FXML
    private ScrollPane scrollPane;
    private void scrollToQuestion(int questionIndex,int numQuestion) {
        double vValue = (double) questionIndex / (numQuestion-2.2);
        scrollPane.setVvalue(vValue);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int numQuestion=100;
        for (int i=1;i<=numQuestion;i++) {
            vBox.getChildren().add(AQuestion(i));
        }


        GridPane gridPane = new GridPane();
        gridPane.setHgap(4); // Khoảng cách giữa các cột
        gridPane.setVgap(4); // Khoảng cách giữa các hàng

        int numRows = 7;
        int numColumns = 4;

        for (int i = 0; i < numQuestion; i++) {
            Button button = new Button("" + (i + 1));
            button.setPrefHeight(40);
            button.setPrefWidth(30);
            button.setStyle("-fx-border-color: black; -fx-background-color: none;-fx-border-width:2px;-fx-border-radius:10%;");
            button.setCursor(Cursor.HAND);
            button.setFont(Font.font(16.0));
            button.setAlignment(Pos.TOP_CENTER);
            button.setPadding(new Insets(-5, 0,0,0));
            button.setId("button"+(i + 1));
            button.setStyle("-fx-background-color: linear-gradient(from 42.803% 50.3788% to 42.803% 50.0%, #c9c9c9 0.0%, #ffffff 100.0%); -fx-border-color: black;-fx-border-radius:10% ; -fx-border-width:2px;");



            int finalI = i;
            button.setOnAction(event -> scrollToQuestion(finalI +1,numQuestion));
            int row = i / numColumns; // Vị trí hàng
            int column = i % numColumns; // Vị trí cột
            gridPane.add(button, column, row); // Thêm nút vào vị trí hàng và cột tương ứng
        }


        vBoxNav.getChildren().add(gridPane);

        Label label1 = new Label("Finish review");
        label1.setFont(Font.font(18.0));
        label1.setCursor(Cursor.HAND);
        label1.setPadding(new Insets(20,0,0,0));
        label1.setOnMouseEntered(mouseEvent -> {
            label1.setUnderline(true);
        });
        label1.setOnMouseExited(mouseEvent -> {
            label1.setUnderline(false);
        });
        label1.setOnMouseClicked(mouseEvent -> {
            try {
                switchToScene1(mouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        vBoxNav.getChildren().add(label1);
    }

    // Xử lý sự kiện khi chọn một câu trả lời
    private void handleAnswerSelection(int questionIndex) {
        Button targetButton = (Button) vBoxNav.lookup("#button"+questionIndex);
        Label answeredLabel = (Label) vBox.lookup("#answered"+questionIndex);
        if (targetButton != null) {
            targetButton.setStyle("-fx-background-color: linear-gradient(from 42.803% 50.3788% to 42.803% 50.0%, #c9c9c9 0.0%, #ffffff 100.0%); -fx-border-color: black;-fx-border-radius:10% ; -fx-border-width:2px;");
            answeredLabel.setText("Answered          ");
        }
    }

}