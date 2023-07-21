package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Gui7_4 implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Quiz currentQuiz = GUI1_1_Controller.currentQuiz;
    private QuizInExam quizInExam = Gui7_3.quizInExam;
    private Boolean isPlay = false;
    @FXML
    Label labelStartQUiz;  // 2 cái startQuiz với finishQUiz kia là set time thì Tuấn làm nhé
    @FXML
    Label labelFinishQuiz;
    @FXML
    Label labelMark;
    @FXML
    Label labelGrade;
    @FXML
    private VBox vBox;
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox vBoxNav;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label labelNameQuiz;
    @FXML
    private Label labelTimeTaken;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(quizInExam.timeTaken);
        labelStartQUiz.setText(quizInExam.startExam);
        labelFinishQuiz.setText(quizInExam.endExam);
        labelTimeTaken.setText(quizInExam.timeTaken);

        double userPoint = quizInExam.userPoint;
        double maxPoint = quizInExam.maxPoint;
        double ratio = userPoint/maxPoint*10.0;

        String s1 = String.format("%.2f",userPoint);
        String s2 = String.format("%.2f",maxPoint);
        String s3 = String.format("%.2f",ratio);

        labelMark.setText(s1 + "/" + s2);
        labelGrade.setText(s3 + " out of 10.00(0%)");

        labelNameQuiz.setText("/ " + currentQuiz.quizName + "  /  Preview");

        QuestionManage questionManage = null;
        try {
            questionManage = new QuestionManage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Integer> listQuestion = LibraryForUs.getQuestionIdFromQuiz(currentQuiz);
        int dem = 1;
        for (int i : listQuestion) {
            try {
                vBox.getChildren().add(AQuestion(i, dem));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dem++;
        }
        dem -- ; // real number question

//         set display in VBoxNav
        GridPane gridPane = new GridPane();
        gridPane.setHgap(4); // Khoảng cách giữa các cột
        gridPane.setVgap(4); // Khoảng cách giữa các hàng

        int numRows = 7;
        int numColumns = 4;


        for (int i = 0; i < dem; i++) {
            Button button = new Button("" + (i + 1));
            button.setPrefHeight(40);
            button.setPrefWidth(30);
            button.setStyle("-fx-border-color: black; -fx-background-color: none;-fx-border-width:2px;-fx-border-radius:10%;");
            button.setCursor(Cursor.HAND);
            button.setFont(Font.font(16.0));
            button.setAlignment(Pos.TOP_CENTER);
            button.setPadding(new Insets(-5, 0,3,0));
            button.setId("button"+(i + 1));
            int finalI = i;
            int finalDem = dem;
            button.setOnAction(event -> scrollToQuestion(finalI + 1, finalDem));
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

        boolean isQuestionTrue = false;
        int dem2 = 1;
        for(int i : listQuestion){
            isQuestionTrue =false;
            if(quizInExam.correctQuestions.contains(i)) isQuestionTrue = true;
            if(isQuestionTrue == true)  handleTrueQuestion(dem2);
            else handleFalseQuestion(dem2);
            dem2++;
        }
    }

    public HBox AQuestion (int i, int dem) throws IOException {
        Question question = QuestionManage.questionsList.get(i);

        boolean hasImage = false;
        if(question.getQuestionMedia() != null)     hasImage =  true;

        String userChoice = quizInExam.userChoice.get(dem - 1);

        // Create HBox
        HBox hbox = new HBox();
        hbox.setPrefWidth(730.0);
        hbox.setPrefHeight(204.0);
        hbox.setSpacing(10);

        // Create AnchorPane for question panel
        AnchorPane questionPane = new AnchorPane();
        questionPane.setId("QuestionPane");
        questionPane.setStyle("-fx-border-color: #bfbcbc;");
        questionPane.setPrefWidth(120.0);
        questionPane.setPrefHeight(120.0);
        hbox.setMargin(questionPane,new Insets(0, 0,30,0));


        // Create labels for question panel
        Label questionTitle = new Label("Question "+ dem);
        Font font = Font.font("Arial", FontWeight.BOLD, 18);
        questionTitle.setFont(font);
        questionTitle.setTextFill(Color.web("#cf1616"));

        Label statusLabel = new Label("Not yet answered");
        statusLabel.setFont(Font.font(14.0));
        statusLabel.setId("answered" + dem);

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
        flagHbox.getChildren().addAll(flagImgView,flagLabel);

        // Add labels to question panel
        VBox questionBox = new VBox(8);
        questionBox.setPadding(new Insets(15));
        questionBox.getChildren().addAll(questionTitle, statusLabel, markLabel, flagHbox);
        questionPane.getChildren().add(questionBox);

        // Create AnchorPane for question content area
        AnchorPane contentPane = new AnchorPane();
        contentPane.setId("" + i);
        contentPane.setStyle("-fx-background-color: #E7F3F5;");
        contentPane.setPrefWidth(750.0);
        contentPane.setPrefHeight(204.0);

        // Create question label
        Label questionLabel = new Label(question.title);
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(580);
        questionLabel.setFont(Font.font(16.0));
        questionLabel.setWrapText(true);


        // Add components to content pane
        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20.0));
        contentBox.getChildren().add(questionLabel);
        if(hasImage == true){
            if(question.getQuestionMedia().get(0) != null){
                if(question.getQuestionMedia().get(0).mediaType.equals("V")){
                    File file = question.getQuestionMedia().get(0).mediaFile;
                    javafx.scene.media.Media media = new Media(file.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    MediaView mediaView = new MediaView();
                    mediaView.setMediaPlayer(mediaPlayer);
                    mediaView.setFitWidth(500);
                    mediaView.setFitHeight(500);
                    mediaView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(isPlay == false){
                                mediaPlayer.play();
                                isPlay = true;
                            }
                            else {
                                mediaPlayer.pause();
                                isPlay = false;
                            }
                        }
                    });
                    contentBox.getChildren().add(mediaView);

                    Button seeAgain = new Button("Watch Video Again");
                    seeAgain.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
                                mediaPlayer.seek(Duration.seconds(0.0));
                            }
                        }
                    });
                    contentBox.getChildren().add(seeAgain);
                }
                else {
                    ImageView imageView = new ImageView(new Image(question.getQuestionMedia().get(0).mediaFile.toURI().toString()));
                    imageView.setFitWidth(300);
                    imageView.setFitHeight(300);
                    contentBox.getChildren().addAll(imageView);
                }

            }
        }

        // Group radio buttons
        ToggleGroup answerGroup = new ToggleGroup();
        // Create answer radio buttons
        if(question.isMultipleChoice() == false){
            int count = 1;
            for (String choice : question.choices){
                RadioButton option = new RadioButton(choice);
                option.setFont(Font.font(14.0));
                option.setWrapText(true);
                option.setMaxWidth(580);
                option.setDisable(true);
                if(userChoice != null){
                    if(userChoice.equals(choice.substring(0,1)))     option.setSelected(true);
                }
                option.setToggleGroup(answerGroup);
                contentBox.getChildren().add(option);
                if(hasImage == true){
                    if(question.getQuestionMedia().get(count) != null){
                        if(question.getQuestionMedia().get(count).mediaType.equals("V")){
                            File file = question.getQuestionMedia().get(count).mediaFile;
                            Media media = new Media(file.toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            MediaView mediaView = new MediaView();
                            mediaView.setMediaPlayer(mediaPlayer);
                            mediaView.setFitWidth(500);
                            mediaView.setFitHeight(500);
                            mediaView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    if(isPlay == false){
                                        mediaPlayer.play();
                                        isPlay = true;
                                    }
                                    else {
                                        mediaPlayer.pause();
                                        isPlay = false;
                                    }
                                }
                            });
                            contentBox.getChildren().add(mediaView);
                            Button seeAgain = new Button("Watch Video Again");
                            seeAgain.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
                                        mediaPlayer.seek(Duration.seconds(0.0));
                                    }
                                }
                            });
                            contentBox.getChildren().add(seeAgain);
                        }
                        else {
                            ImageView imageView = new ImageView(new Image(question.getQuestionMedia().get(count).mediaFile.toURI().toString()));
                            imageView.setFitWidth(100);
                            imageView.setFitHeight(100);
                            contentBox.getChildren().addAll(imageView);
                        }
                    }
//                      old
//                    ImageView imageChoice = new ImageView(question.getQuestionImage().get(count));
//                    contentBox.getChildren().add(imageChoice);
                }
                count++;
            }
        }
        else {
            int count = 1;
            for (String choice : question.choices) {
                CheckBox option = new CheckBox(choice);
                option.setFont(Font.font(14.0));
                option.setWrapText(true);
                option.setMaxWidth(580);
                option.setDisable(true);
                if(userChoice != null){
                    if (userChoice.contains(choice.substring(0,1))) option.setSelected(true);
                }
                contentBox.getChildren().add(option);
                if (hasImage == true) {
                    if (question.getQuestionMedia().get(count) != null) {
                        if (question.getQuestionMedia().get(count).mediaType.equals("V")) {
                            File file = question.getQuestionMedia().get(count).mediaFile;
                            Media media = new Media(file.toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            MediaView mediaView = new MediaView();
                            mediaView.setMediaPlayer(mediaPlayer);
                            mediaView.setFitWidth(500);
                            mediaView.setFitHeight(500);
                            mediaView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    if (isPlay == false) {
                                        mediaPlayer.play();
                                        isPlay = true;
                                    } else {
                                        mediaPlayer.pause();
                                        isPlay = false;
                                    }
                                }
                            });
                            contentBox.getChildren().add(mediaView);
                            Button seeAgain = new Button("Watch Video Again");
                            seeAgain.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
                                        mediaPlayer.seek(Duration.seconds(0.0));
                                    }
                                }
                            });
                            contentBox.getChildren().add(seeAgain);
                        } else {
                            ImageView imageView = new ImageView(new Image(question.getQuestionMedia().get(count).mediaFile.toURI().toString()));
                            imageView.setFitWidth(100);
                            imageView.setFitHeight(100);
                            contentBox.getChildren().addAll(imageView);
                        }
                    }
//                      old
//                    ImageView imageChoice = new ImageView(question.getQuestionImage().get(count));
//                    contentBox.getChildren().add(imageChoice);
                }
                count++;
            }
        }

        // Create a label answer
        Label aLabel = new Label("The correct answer is: " + question.correctAnswer);
        Font font1 = Font.font("Arial", FontWeight.BOLD, 16);
        aLabel.setFont(font1);
        aLabel.setTextFill(Color.web("#cf1616"));
        contentBox.getChildren().add(aLabel);

        contentPane.getChildren().add(contentBox);


        // Add panes to HBox
        hbox.getChildren().addAll(questionPane, contentPane);

        return hbox;

    }


    private void scrollToQuestion(int questionIndex,int numQuestion) {
        double vValue = (double) questionIndex / (numQuestion-2.5);
        scrollPane.setVvalue(vValue);
    }


    // Xử lý sự kiện khi chọn một câu trả lời
    private void handleTrueQuestion(int questionIndex) {
        Button targetButton = (Button) vBoxNav.lookup("#button"+questionIndex);
        if (targetButton != null) {
//            targetButton.setStyle("-fx-background-color: linear-gradient(from 42.803% 50.3788% to 42.803% 50.0%, #c9c9c9 0.0%, #ffffff 100.0%); -fx-border-color: black;-fx-border-radius:10% ; -fx-border-width:2px;");
            targetButton.setStyle("-fx-background-color: linear-gradient(from 100.0% 49.2114% to 100.0% 48.265%, #16be27 0.0%, #ffffff 100.0%);-fx-border-color: black;-fx-border-radius:10% ; -fx-border-width:2px;");
        }
    }

    private void handleFalseQuestion(int questionIndex) {
        Button targetButton = (Button) vBoxNav.lookup("#button"+questionIndex);
        if (targetButton != null) {
            targetButton.setStyle("-fx-background-color:linear-gradient(from 100.0% 49.2114% to 100.0% 48.265%, #bd1717 0.0%, #ffffff 100.0%); -fx-border-color: black;-fx-border-radius:10% ; -fx-border-width:2px;");
//            targetButton.setStyle("-fx-background-color: red;");
        }
    }

    public void switchToScene1(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

