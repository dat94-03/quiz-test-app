package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Question;
import model.QuestionManage;

import javax.xml.transform.Source;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionList implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Question qStatic ;

    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<Label> editLabel = new ArrayList<>();

    @FXML
    private Label categoryLabel;
    @FXML
    private VBox questionBox;
    @FXML
    private ImageView dropDown;
    @FXML
    private CheckBox checkBoxShowSubCategory;
    @FXML
    private AnchorPane myAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
//        remove when editquestion done
        QuestionManage qm = null;
        try {
            qm = new QuestionManage();
            for(Question ques : QuestionManage.questionsList){
                if (ques.category.equals(QuestionBankTree.fullyCategory) && (ques.title.equals("") == false)){
                    qStatic = ques;
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        questionBox.getChildren().clear();
        questions.clear();
        editLabel.clear();

//        set label
        categoryLabel.setText("  " + QuestionBankTree.currentCategory);

//        Display Question
        int dem = 0;
        for (Question question : QuestionManage.questionsList) {
            if (question.category.equals(QuestionBankTree.fullyCategory) && (question.title.equals("") == false)) {
                questions.add(question); // add question to arraylist
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
                    BackgroundFill backgroundFill = new BackgroundFill(Color.web("#DDDDDD"), null, null);
                    Background background = new Background(backgroundFill);
                    hboxQuestion.setBackground(background);
                }
                hboxQuestion.setPrefWidth(800);
                HBox.setHgrow(hboxQuestion, Priority.NEVER);

//                Draw function edit beside
                HBox hBoxEdit = new HBox();
                Label label = new Label("Edit ");
                Image image = new Image(getClass().getResource("/Img.img/dropdown arrow.png").toExternalForm());
                ImageView imageView1 = new ImageView(image);

                label.setFont(Font.font(20));
                Color color = Color.web("#00a2e9");
                label.setTextFill(color);


                editLabel.add(label);

//                set action for Edit Click
                int Dem = dem; // hơi cấn cấn chỗ này vì phải dùng hằng, dùng vì hàm lambda bắt buộc dùng
                editLabel.get(dem).setOnMouseClicked(e -> {
                    try {
                        qStatic = questions.get(Dem);
                        switchToEditQuestion(e);
                    } catch (IOException even) {
                        even.printStackTrace();
                    }
                });

                editLabel.get(dem).setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        editLabel.get(Dem).setStyle("-fx-cursor: hand;");
                        editLabel.get(Dem).setUnderline(true);
                    }
                });

                editLabel.get(dem).setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        editLabel.get(Dem).setUnderline(false);
                    }
                });


//                add component
                hBoxEdit.getChildren().addAll(label, imageView1);
                // fill color
                if(dem % 2 ==0){
                    BackgroundFill backgroundFill2 = new BackgroundFill(Color.web("#DDDDDD"), null, null);
                    Background background2 = new Background(backgroundFill2);
                    hBoxEdit.setBackground(background2);
                }
                else {
                    hBoxEdit.setBackground(Background.fill(null));
                }
                // continue add component
                hBoxrow.getChildren().addAll(hboxQuestion, hBoxEdit);
                questionBox.getChildren().add(hBoxrow);

                dem ++;
            }
            myAnchorPane.setPrefHeight(784 + dem*30);
        }
    }

    public void showQuestionFromSubCate() throws IOException {
        QuestionManage qm = new QuestionManage();
        questionBox.getChildren().clear();
        questions.clear();
        editLabel.clear();

//          if click "Sub question from category" off
        if(checkBoxShowSubCategory.isSelected() == false){
            int dem = 0;
            for (Question question : QuestionManage.questionsList) {
                if (question.category.equals(QuestionBankTree.fullyCategory) && (question.title.equals("") == false)) {
                    questions.add(question); // add question to arraylist
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
                        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#DDDDDD"), null, null);
                        Background background = new Background(backgroundFill);
                        hboxQuestion.setBackground(background);
                    }
                    hboxQuestion.setPrefWidth(800);
                    HBox.setHgrow(hboxQuestion, Priority.NEVER);

//                Draw button edit beside
                    HBox hBoxEdit = new HBox();
                    Label label = new Label("Edit ");
                    Image image = new Image(getClass().getResource("/Img.img/dropdown arrow.png").toExternalForm());
                    ImageView imageView1 = new ImageView(image);

                    label.setFont(Font.font(20));
                    Color color = Color.web("#00a2e9");
                    label.setTextFill(color);


                    editLabel.add(label);

//                set action for Edit Click
                    int Dem = dem; // hơi cấn cấn chỗ này vì phải dùng hằng, dùng vì hàm lambda bắt buộc dùng
                    editLabel.get(dem).setOnMouseClicked(e -> {
                        try {
                            qStatic = questions.get(Dem);
                            switchToEditQuestion(e);
                        } catch (IOException even) {
                            even.printStackTrace();
                        }
                    });

                    editLabel.get(dem).setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            editLabel.get(Dem).setStyle("-fx-cursor: hand;");
                            editLabel.get(Dem).setUnderline(true);
                        }
                    });

                    editLabel.get(dem).setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            editLabel.get(Dem).setUnderline(false);
                        }
                    });


//                add component
                    hBoxEdit.getChildren().addAll(label, imageView1);
                    // fill color
                    if(dem % 2 ==0){
                        BackgroundFill backgroundFill2 = new BackgroundFill(Color.web("#DDDDDD"), null, null);
                        Background background2 = new Background(backgroundFill2);
                        hBoxEdit.setBackground(background2);
                    }
                    else {
                        hBoxEdit.setBackground(Background.fill(null));
                    }
                    // continue add component
                    hBoxrow.getChildren().addAll(hboxQuestion, hBoxEdit);
                    questionBox.getChildren().add(hBoxrow);

                    dem ++;
                }
                myAnchorPane.setPrefHeight(784 + dem*30);
            }
        }

//        if click "Sub question from category"
        else {
            ArrayList<Question> subCate = new ArrayList<>();
            subCate = qm.getQuestionsOfCategoryAndSubcategory(QuestionBankTree.fullyCategory);

            int dem = 0;
            for (Question question : subCate) {
                if ((question.title.equals("") == false)){
                    questions.add(question); // add question to arraylist
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
                        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#DDDDDD"), null, null);
                        Background background = new Background(backgroundFill);
                        hboxQuestion.setBackground(background);
                    }
                    hboxQuestion.setPrefWidth(800);
                    HBox.setHgrow(hboxQuestion, Priority.NEVER);

//                Draw button edit beside
                    HBox hBoxEdit = new HBox();
                    Label label = new Label("Edit ");
                    Image image = new Image(getClass().getResource("/Img.img/dropdown arrow.png").toExternalForm());
                    ImageView imageView1 = new ImageView(image);

                    label.setFont(Font.font(20));
                    Color color = Color.web("#00a2e9");
                    label.setTextFill(color);


                    editLabel.add(label);

//                set action for Edit Click
                    int Dem = dem; // hơi cấn cấn chỗ này vì phải dùng hằng, dùng vì hàm lambda bắt buộc dùng
                    editLabel.get(dem).setOnMouseClicked(e -> {
                        try {
                            qStatic = questions.get(Dem);
                            switchToEditQuestion(e);
                        } catch (IOException even) {
                            even.printStackTrace();
                        }
                    });

                    editLabel.get(dem).setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            editLabel.get(Dem).setStyle("-fx-cursor: hand;");
                            editLabel.get(Dem).setUnderline(true);
                        }
                    });

                    editLabel.get(dem).setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            editLabel.get(Dem).setUnderline(false);
                        }
                    });


//                add component
                    hBoxEdit.getChildren().addAll(label, imageView1);
                    // fill color
                    if(dem % 2 ==0){
                        BackgroundFill backgroundFill2 = new BackgroundFill(Color.web("#DDDDDD"), null, null);
                        Background background2 = new Background(backgroundFill2);
                        hBoxEdit.setBackground(background2);
                    }
                    else {
                        hBoxEdit.setBackground(Background.fill(null));
                    }
                    // continue add component
                    hBoxrow.getChildren().addAll(hboxQuestion, hBoxEdit);
                    questionBox.getChildren().add(hBoxrow);

                    dem ++;
                }
                myAnchorPane.setPrefHeight(784 + dem*30);
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