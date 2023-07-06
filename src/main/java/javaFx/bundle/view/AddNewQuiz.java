package javaFx.bundle.view;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.QuestionManage;
import model.Quiz;
import model.QuizzesManage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class AddNewQuiz implements Initializable {
    @FXML
    private ImageView chamthan ;
    private Stage stage;
    private Scene scene;
    private Parent root;
    Map<Integer,String> numberMap ;
    Integer currSecond ;
    Integer text ;
    Thread thrd ;

    @FXML
    private TextField textFieldNameQuiz;
    @FXML
    private CheckBox checkBox ;
    @FXML
    private ChoiceBox<String> choiceBox ;
    private String[] choiceTime = {" hours"," minutes"," seconds"} ;
    @FXML
    private Text secondsTimer;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonStart;

    @FXML
    private ComboBox<Integer> dayComboBox1;

    @FXML
    private ComboBox<Integer> dayComboBox2;

    @FXML
    private ComboBox<Integer> hourComboBox1;

    @FXML
    private ComboBox<Integer> hourComboBox2;

    @FXML
    private Text hoursTimer;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private ComboBox<Integer> minuteComboBox1;

    @FXML
    private ComboBox<Integer> minuteComboBox2;

    @FXML
    private Text minutesTimer;

    @FXML
    private ComboBox<String> monthComboBox1;

    @FXML
    private ComboBox<String> monthComboBox2;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private TextField textTime;

    @FXML
    private AnchorPane timerPane;

    @FXML
    private ComboBox<Integer> yearComboBox1;
    private volatile boolean stopFlag = false;
    @FXML
    private ComboBox<Integer> yearComboBox2;
    public void CreateQuiz(ActionEvent event) throws IOException {
        boolean flagNameQuiz = false, flagTimeSet = false;

        if(textFieldNameQuiz != null && (textFieldNameQuiz.getText().equals("") == false)){
            String nameQuiz = new String(textFieldNameQuiz.getText());
            flagNameQuiz = true;
        }
        String nameQuiz = new String(textFieldNameQuiz.getText());
        String strOpenQuiz = dayComboBox1.getValue() + "/" + monthComboBox1.getValue() + "/" + yearComboBox1.getValue();
        String strCloseQuiz = dayComboBox2.getValue() + "/" + monthComboBox2.getValue() + "/" + yearComboBox2.getValue();
        String questionsOnQuiz = new String();  // it is empty
        int intTimeLimit = 0;
        if((textTime != null) && IntegerCheck.isInteger(textTime.getText())){
            if(myChoiceBox.getValue().equals(" seconds")){
                intTimeLimit = Integer.valueOf(textTime.getText());
                System.out.println("seconds");
            }
            else if(myChoiceBox.getValue().equals(" minutes")){
                intTimeLimit = Integer.valueOf(textTime.getText()) * 60;
                System.out.println("minutes");
            }
            else if(myChoiceBox.getValue().equals(" hours")){
                intTimeLimit = Integer.valueOf(textTime.getText()) * 3600;
                System.out.println("hours");
            }
            else {
                System.out.println("Error at create quiz");
            }
            flagTimeSet = true;
        }

        if((flagNameQuiz == true) && (flagTimeSet == true)){
            System.out.println("Quiz infor : " + nameQuiz + " " +  strOpenQuiz + " " +  strCloseQuiz + " " + intTimeLimit);
            QuizzesManage quizzesManage = new QuizzesManage();
            int numQuiz = 0;
            for (Quiz quiz : QuizzesManage.quizzesList){
                numQuiz++;
            }
            Quiz quiz = new Quiz(numQuiz, nameQuiz, strOpenQuiz, strCloseQuiz, intTimeLimit, questionsOnQuiz, "wil fix");
            quizzesManage.addQuiz(quiz);

//       switch to GUI 1.1
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You don't import quiz name or set time (time must be integer)");
            alert.showAndWait();
        }
    }

    public class IntegerCheck {
        public static boolean isInteger(String str) {
            try {
                Integer.parseInt(str); // Chuyển đổi chuỗi thành số nguyên
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }


    public void switchToHome(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void startCountdown() {
        System.out.println("Start Countdown");
        thrd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   stopFlag = false ;
                    while (!stopFlag) {
                        // Countdown here
                        setOutput();
                        if(currSecond==0) {
                            System.out.println("Finished");
                            scrollDown();
                        }
                        currSecond -= 1 ;
                        Thread.sleep(1000);


                    }
                    System.out.println("Finished");
                } catch (Exception e) {

                }
            }
        }) ;
        thrd.start();
    }
    public void stopCountdown() {
        stopFlag = true;
    }
    String changeint(int a) {
        if(0<=a && a <= 9)
            return   "0" + String.valueOf(a) ;
        else  return String.valueOf(a) ;
    }
    void setOutput() {
        LinkedList<Integer> currHms = secondsToHms(currSecond) ;
        hoursTimer.setText(changeint(currHms.get(0)));
        minutesTimer.setText(changeint(currHms.get(1)));
        secondsTimer.setText(changeint(currHms.get(2)));
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
      Integer hmsToSeconds() {
         if(myChoiceBox.getValue() == " minutes")
        text = Integer.valueOf(textTime.getText())*60 ;
         else if(myChoiceBox.getValue() == " hours")
             text = Integer.valueOf(textTime.getText())*3600 ;
         else if(myChoiceBox.getValue() == " seconds")
             text = Integer.valueOf(textTime.getText()) ;
          return text ;
    }

    public void start(ActionEvent event) {
        if (checkBox.isSelected()) {
            currSecond = hmsToSeconds();
            if (check() == 1)
                scrollUp();
        }
        else scrollUp();
    }
    public void unStart(ActionEvent event){
        if (checkBox.isSelected()) {
        stopCountdown();
        scrollDown(); }
        else scrollDown();
    }
    void scrollUp() {
        TranslateTransition tr1 = new TranslateTransition() ;
        tr1.setDuration(Duration.millis(100));
        tr1.setToX(0);
        tr1.setToY(-760);
        tr1.setNode(menuPane);
        TranslateTransition tr2 = new TranslateTransition() ;
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(760);
        tr2.setToX(0) ;
        tr2.setToY(0);
        tr2.setNode(timerPane);
        ParallelTransition pt = new ParallelTransition(tr1,tr2) ;
        if(checkBox.isSelected())
        startCountdown();
        pt.play();
    }
    void scrollDown() {
        TranslateTransition tr1 = new TranslateTransition() ;
        tr1.setDuration(Duration.millis(100));
        tr1.setToX(0);
        tr1.setToY(-760);
        tr1.setNode(timerPane);
        TranslateTransition tr2 = new TranslateTransition() ;
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(760);
        tr2.setToX(0) ;
        tr2.setToY(0);
        tr2.setNode(menuPane);
        ParallelTransition pt = new ParallelTransition(tr1,tr2) ;
        pt.play();

    }
    public Integer check() {       // Kiểm tra xem có thể vào vào được quiz hay không
        Integer check = 0 ;
        Integer year1 = yearComboBox1.getValue() ;
        String month1 = monthComboBox1.getValue() ;
        Integer day1 = dayComboBox1.getValue() ;
        Integer hour1 = hourComboBox1.getValue() ;
        Integer minute1 = minuteComboBox1.getValue() ;

        Integer year2 = yearComboBox2.getValue() ;
        String month2 = monthComboBox2.getValue() ;
        Integer day2 = dayComboBox2.getValue() ;
        Integer hour2 = hourComboBox2.getValue() ;
        Integer minute2 = minuteComboBox2.getValue() ;
        Integer m1 = 0  ;
        if(month1 == "January")  m1 = 1 ;
        else if(month1 == "February")   m1 = 2 ;
        else if(month1 == "March")  m1 = 3 ;
        else if(month1 == "April") m1 = 4 ;
        else if(month1 == "May")  m1 = 5 ;
        else if(month1 == "June") m1 = 6 ;
        else if(month1 == "July") m1 = 7 ;
        else if(month1 == "August") m1 = 8 ;
        else if(month1 == "September") m1 = 9 ;
        else if(month1 == "October") m1 = 10 ;
        else if(month1 == "November") m1 = 11 ;
        else if(month1 == "December") m1 = 12 ;
        Integer m2 = 0 ;
        if(month2 == "January")  m2 = 1 ;
        else if(month2 == "February")   m2 = 2 ;
        else if(month2 == "March")  m2 = 3 ;
        else if(month2 == "April") m2 = 4 ;
        else if(month2 == "May")  m2 = 5 ;
        else if(month2 == "June") m2 = 6 ;
        else if(month2 == "July") m2 = 7 ;
        else if(month2 == "August") m2 = 8 ;
        else if(month2 == "September") m2 = 9 ;
        else if(month2 == "October") m2 = 10 ;
        else if(month2 == "November") m2 = 11 ;
        else if(month2 == "December") m2 = 12 ;

        String time1 = String.valueOf(year1) + "-"+String.valueOf(m1) + "-" +String.valueOf(day1) + " " +String.valueOf(hour1) + ":" +String.valueOf(minute1) +":00" ;
        String time2 = String.valueOf(year2) + "-"+String.valueOf(m2) + "-" +String.valueOf(day2) + " " +String.valueOf(hour2) + ":" +String.valueOf(minute2) +":00" ;

        Timestamp ts1 = Timestamp.valueOf(time1);
        Timestamp ts2 = Timestamp.valueOf(time2);

        Date date = new Date();

        int b1 = date.compareTo(ts1) ;
        int b2 = ts2.compareTo(date) ;
        if( 0<=b1 && b2 >=0 )
            check = 1 ;
        else check =0 ;

    return check ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         myChoiceBox.getItems().addAll(choiceTime) ;
         myChoiceBox.setValue(" minutes");
         choiceBox.setValue("Open attempts are submitted automatic");
        ObservableList<Integer> dayList = FXCollections.observableArrayList();
        ObservableList<String> monthList = FXCollections.observableArrayList("January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December") ;
        ObservableList<Integer> yearList = FXCollections.observableArrayList() ;
        ObservableList<Integer> hourList = FXCollections.observableArrayList() ;
        ObservableList<Integer> minuteList = FXCollections.observableArrayList() ;
          for(int i =1 ;i <= 31 ;i++)
              dayList.add(new Integer(i)) ;
//              dayList.add(i);
          for(int i=2020;i<=2040;i++)
              yearList.add(new Integer(i)) ;
//              dayList.add(i);
          for(int i = 0;i<=23;i++)
              hourList.add(new Integer(i)) ;
//              dayList.add(i);
          for(int i=0;i<=59;i++)
              minuteList.add(new Integer(i)) ;
//              dayList.add(i);
          dayComboBox1.setItems(dayList);
          dayComboBox1.setValue(21);
          dayComboBox2.setItems(dayList);
        dayComboBox2.setValue(21);
        monthComboBox1.setItems(monthList);
        monthComboBox1.setValue("March");
        monthComboBox2.setItems(monthList);
        monthComboBox2.setValue("March");
          yearComboBox1.setItems(yearList);
          yearComboBox1.setValue(2023);
          yearComboBox2.setItems(yearList);
        yearComboBox2.setValue(2023);
          hourComboBox1.setItems(hourList);
          hourComboBox1.setValue(15);
          hourComboBox2.setItems(hourList);
        hourComboBox2.setValue(15);
          minuteComboBox1.setItems(minuteList);
          minuteComboBox1.setValue(57);
          minuteComboBox2.setItems(minuteList);
        minuteComboBox2.setValue(57);

        textTime.setEditable(false);
        textFieldNameQuiz.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                chamthan.setVisible(true);
            } else {
                chamthan.setVisible(false);
            }
        });
    }
    public void change(ActionEvent event) {
        if (!checkBox.isSelected()) {
            textTime.setEditable(false);
        } else {
            textTime.setEditable(true);
        }
    }
}