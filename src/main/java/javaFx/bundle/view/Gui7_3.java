package javaFx.bundle.view;

import javafx.application.Platform;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Gui7_3 implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Quiz currentQuiz = GUI1_1_Controller.currentQuiz;
    static QuizInExam quizInExam = new QuizInExam();
    //    private Media media;
    private ArrayList<AnchorPane> questionPanes = new ArrayList<>();
    private ArrayList<Double> questionHeight = new ArrayList<>();
    private double sumQuestionHeight = 0;
    private boolean isCalculateHeight = false;


    private Boolean isPlay = false;
    @FXML
    private Text hour ;
    @FXML
    private Text minute ;
    @FXML
    private Text second ;
    @FXML
    private Label labelNameQuiz;
    @FXML
    private VBox vBox;
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox vBoxNav;
    @FXML
    private ScrollPane scrollPane;

    Integer finish  = 0;
    Integer check = 1 ;
    Thread thrd ;
    Integer k = 0 ;
    Integer currSecond = currentQuiz.timeLimit ;
    LocalDateTime currentDateTime = LocalDateTime.now();
    LocalDateTime time = currentDateTime ;
    Integer currSec = currSecond;


    public void startCountdown() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a");
        String formattedDateTime = currentDateTime.format(formatter);
        quizInExam.startExam = formattedDateTime;

        thrd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // Countdown here
                        setOutput();
                        if (currSecond == -1) {

                            thrd.interrupt();
                            if(finish != 1)
                            {  time = time.plusSeconds(currSecond) ;
                                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a");
                                String formattedDateTime2 = time.format(formatter2);
                                quizInExam.endExam = formattedDateTime2;
                                exportTime(currSec - currSecond);
                            }
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        calculateAttempt();
                                        Parent root = FXMLLoader.load(getClass().getResource("Gui7_4.fxml"));
                                        Stage stage = (Stage) scrollPane.getScene().getWindow();
                                        Scene scene = new Scene(root);
                                        stage.setScene(scene);
                                        stage.show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }
                        currSecond -= 1 ;
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {

                }
            }
        }) ;
        thrd.start();
    }
    public void stop() {
        if(check == 1)
            thrd.interrupt();
    }
    String changeint(int a) {
        if(0<=a && a <= 9)
            return   "0" + String.valueOf(a) ;
        else  return String.valueOf(a) ;
    }
    void setOutput() {
        LinkedList<Integer> currHms = secondsToHms(currSecond) ;
        hour.setText(changeint(currHms.get(0)));
        minute.setText(changeint(currHms.get(1)));
        second.setText(changeint(currHms.get(2)));
    }
    LinkedList<Integer> secondsToHms(Integer currSecond) { //
        Integer hours = currSecond /3600 ;
        currSecond  = currSecond % 3600 ;
        Integer minutes = currSecond / 60 ;
        currSecond = currSecond % 60 ;
        Integer seconds = currSecond ;
        LinkedList<Integer> answer = new LinkedList<>() ;
        answer.add(hours) ;
        answer.add(minutes) ;
        answer.add(seconds) ;
        return answer ;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizInExam.deleteData();
        String str = currentQuiz.quizName.trim();
        labelNameQuiz.setText("/ " + str + "  /  Preview" );

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
            quizInExam.maxPoint = quizInExam.maxPoint + 1;  // sau sẽ thay bằng cộng trọng số điểm từng câu
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
            button.setOnAction(event -> scrollToQuestion(finalI +1,finalDem));
            int row = i / numColumns; // Vị trí hàng
            int column = i % numColumns; // Vị trí cột
            gridPane.add(button, column, row); // Thêm nút vào vị trí hàng và cột tương ứng
        }


        vBoxNav.getChildren().add(gridPane);

//        display "Finish Attempt"
        Label labelFinishAttempt = new Label("Finish attempt...");
        labelFinishAttempt.setFont(Font.font(18.0));
        labelFinishAttempt.setCursor(Cursor.HAND);
        labelFinishAttempt.setPadding(new Insets(20,0,0,0));
        labelFinishAttempt.setOnMouseEntered(mouseEvent -> {
            labelFinishAttempt.setUnderline(true);
        });
        labelFinishAttempt.setOnMouseExited(mouseEvent -> {
            labelFinishAttempt.setUnderline(false);
        });
        labelFinishAttempt.setOnMouseClicked(mouseEvent -> {
            try { finish = 1 ;
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a");
                String formattedDateTime3 = currentDateTime.format(formatter3);
                quizInExam.endExam = formattedDateTime3;
                stop();
                switchTo7_4(mouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        vBoxNav.getChildren().add(labelFinishAttempt);
        if (check == 1)
            startCountdown();
    }

    public HBox AQuestion (int i, int dem) throws IOException {
        Question question = QuestionManage.questionsList.get(i);

        boolean hasImage = false;
        if(question.getQuestionMedia() != null)     hasImage =  true;

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
        flagHbox.setId("flag" + dem);
        flagHbox.getChildren().addAll(flagImgView,flagLabel);
        flagHbox.setOnMouseClicked(event -> flagQuestion(dem));

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
        int count = 1;
        if(question.isMultipleChoice() == false){
            for (String choice : question.choices){
                RadioButton option = new RadioButton(choice);
                option.setFont(Font.font(14.0));
                option.setWrapText(true);
                option.setMaxWidth(580);
                option.setToggleGroup(answerGroup);
                option.setOnAction(e -> handleAnswerSelection(dem));
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
                            Button seeAgain = new Button("See Video Again");
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
                }
                count++;
            }
        }
        else {
            for (String choice : question.choices){
                CheckBox option = new CheckBox(choice);
                option.setFont(Font.font(14.0));
                option.setOnAction(e -> handleAnswerSelection(dem));
                option.setWrapText(true);
                option.setMaxWidth(580);
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
                }
                count++;
            }
        }

        contentPane.getChildren().add(contentBox);


        // Add panes to HBox
        hbox.getChildren().addAll(questionPane, contentPane);
        return hbox;
    }

    private void scrollToQuestion(int questionIndex,int numQuestion) {
//        System.out.println("question Index la : " + questionIndex + ", numQues la : " + numQuestion);
        if(!isCalculateHeight){
            questionHeight.add((double) 0);
            calculateHeight();
//            System.out.println(questionHeight);
        }
        isCalculateHeight = true;
        double vValue = (double) questionIndex / (numQuestion-2.5);
//        System.out.println("vValue old : " + vValue);
        vValue = (double) (questionHeight.get(questionIndex - 1) + 230) / (sumQuestionHeight + 230 )  ;
//        System.out.println("vValue new : " + vValue + ", questionHeight : " + questionHeight.get(questionIndex - 1));
//        System.out.println("sum : " + sumQuestionHeight);
        scrollPane.setVvalue(vValue);
    }

    // Xử lý sự kiện khi chọn một câu trả lời
    private void handleAnswerSelection(int questionIndex) {
        Button targetButton = (Button) vBoxNav.lookup("#button" + questionIndex);
        Label answeredLabel = (Label) vBox.lookup("#answered" + questionIndex);
        if (targetButton != null ) {
            targetButton.setStyle("-fx-background-color: linear-gradient(from 42.803% 50.3788% to 42.803% 50.0%, #c9c9c9 0.0%, #ffffff 100.0%); -fx-border-color: black;-fx-border-radius:10% ; -fx-border-width:2px;");
            answeredLabel.setText("Answered          ");
        }
    }

    // Flag Question
    public void flagQuestion(int questionIndex) {
        Button targetButton = (Button) vBoxNav.lookup("#button"+questionIndex);
        Image flagImg = new Image(getClass().getResource("/Img.img/flag2.png").toExternalForm());
        ImageView flagImgView = new ImageView(flagImg);
        flagImgView.setFitWidth(15);
        flagImgView.setFitHeight(15);
        if (targetButton != null) {
            targetButton.setGraphic(flagImgView);
            targetButton.setContentDisplay(ContentDisplay.BOTTOM);
        }
        if (targetButton != null) {
            targetButton.setStyle("-fx-background-color:  linear-gradient(from 42.803% 48.8636% to 42.803% 48.1061%, #ff8989 0.0%, #ffffff 100.0%); -fx-border-color: black;-fx-border-radius:10% ; -fx-border-width:2px;");
        }
    }

    public void switchTo7_4(MouseEvent event) throws IOException, InterruptedException {
        calculateAttempt();
        exportTime(currSec - currSecond);
//      switch to GUI 7.4
        root = FXMLLoader.load(getClass().getResource("Gui7_4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void calculateAttempt(){
        // pause video

        //        finish attempt
        for (javafx.scene.Node node : vBox.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                for (javafx.scene.Node childNode : hBox.getChildren()) {
                    if (childNode instanceof AnchorPane) {
                        AnchorPane anchorPane = (AnchorPane) childNode;
                        if(anchorPane == null)  continue;
                        if(anchorPane.getId().equals("QuestionPane"))   continue;
                        int idQues = Integer.parseInt(anchorPane.getId());
                        Question question = QuestionManage.questionsList.get(idQues);
                        for (javafx.scene.Node grandChildNode : anchorPane.getChildren()) {
                            if (grandChildNode instanceof VBox) {
                                VBox contentBox = (VBox) grandChildNode;
                                int userChoice = 1;  boolean flag = false;
                                if(question.isMultipleChoice() == false){
                                    for (javafx.scene.Node radioNode : contentBox.getChildren()) {
                                        if(radioNode instanceof RadioButton){
                                            if(((RadioButton) radioNode).isSelected() ){
                                                flag =true; // da tra loi cau hoi
                                                String correctAns = question.correctAnswer;
                                                String selectAns = ((RadioButton) radioNode).getText().substring(0,1);
                                                quizInExam.userChoice.add(selectAns);
                                                if(correctAns.equals(selectAns) ){
//                                                System.out.println("Cau " + idQues + "lam dung");
                                                    quizInExam.userPoint = quizInExam.userPoint + 1;
                                                    quizInExam.correctQuestions.add(idQues);
                                                }
//                                            System.out.println(((RadioButton) radioNode).getText());
                                            }
                                            userChoice++;
                                        }
                                    }
                                    if(flag == false)   quizInExam.userChoice.add(null);
                                }
                                else {
                                    int tmp = 0;
                                    int numberCorrectAns = question.multipleAnswer.size();
                                    boolean isUserRight = true;
                                    String userSelect = new String("");
                                    String correctAns = question.correctAnswer;
                                    for (javafx.scene.Node checkBoxNode : contentBox.getChildren()) {
                                        if(checkBoxNode instanceof CheckBox){
                                            if(((CheckBox) checkBoxNode).isSelected() ){
                                                flag = true; // da tra loi cau hoi
                                                String selectAns = ((CheckBox) checkBoxNode).getText().substring(0,1);
                                                userSelect += selectAns + ", ";
                                                if(correctAns.contains(selectAns) == false){
                                                    isUserRight = false;
                                                    break;
                                                }
                                                tmp++;
                                            }
                                            userChoice++;
                                        }
                                    }
                                    if(flag == false)   quizInExam.userChoice.add(null);
                                    else    quizInExam.userChoice.add(userSelect);
                                    if(isUserRight && (tmp == numberCorrectAns)){
                                        quizInExam.userPoint = quizInExam.userPoint + 1;
                                        quizInExam.correctQuestions.add(idQues);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void calculateHeight(){
        for (javafx.scene.Node node : vBox.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                for (javafx.scene.Node childNode : hBox.getChildren()) {
                    if (childNode instanceof AnchorPane) {
                        AnchorPane anchorPane = (AnchorPane) childNode;
                        if (anchorPane == null) continue;
                        if (anchorPane.getId().equals("QuestionPane")) continue;
//                        System.out.println(anchorPane.getHeight());
                        sumQuestionHeight += anchorPane.getHeight() ;
                        questionHeight.add(sumQuestionHeight);
                    }
                }
            }
        }
    }

    public void exportTime(int a){
        int min = a/60;
        int sec = a % 60;
        quizInExam.timeTaken = "" + min + " min " + sec + " seconds";
    }
}

