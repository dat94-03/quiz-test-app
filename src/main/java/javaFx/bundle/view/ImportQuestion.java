package javaFx.bundle.view;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.QuestionManage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportQuestion implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String pathToFile = new String("");
    @FXML
    private Button chooseAFileButton;
    @FXML
    private ImageView dropDown;
    @FXML
    private Label dragDropFile;
    @FXML
    private Button dragDropButton;
    @FXML
    private Label dropHere;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(1000), dropDown);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByY(-23);
        translate.setAutoReverse(true);
        translate.play();


//        Rotate pivot = new Rotate();  // create center rotate
//        pivot.setPivotX(dropDown.getX());
//        pivot.setPivotY(dropDown.getY() - 1500);
//        dropDown.getTransforms().add(pivot); // set center rotate for Image View drop down
//        RotateTransition rotate = new RotateTransition(Duration.millis(2000), dropDown);
//        rotate.setCycleCount(TranslateTransition.INDEFINITE);
//        rotate.setByAngle(360);
//        rotate.setAxis(Rotate.Y_AXIS);
//        rotate.setAutoReverse(true);
//        rotate.setInterpolator(Interpolator.LINEAR);
//        rotate.play();
//
//        ParallelTransition parallel = new ParallelTransition(dropDown, translate, rotate);
//        parallel.play();

    }

    public void openFile(ActionEvent event) throws IOException {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Word Documents", "*.docx");
            fileChooser.getExtensionFilters().add(filter);
            java.io.File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                pathToFile = selectedFile.getAbsolutePath();
                dragDropFile.setText("[" + pathToFile + "]");
                dragDropFile.setVisible(true);
                System.out.println(pathToFile);
            }
    }
    public void dragAndDropFile(){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Drag and drop File");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Word Documents", "*.docx");
            fileChooser.getExtensionFilters().add(filter);
            fileChooser.showOpenDialog(stage);

        dropHere.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dropHere
                        && event.getDragboard().hasFiles()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
        dropHere.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    dragDropFile.setVisible(true);
                    dragDropFile.setText(db.getFiles().toString());
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        if(dragDropFile.getText().equals("") == false){
            pathToFile = dragDropFile.getText();
            pathToFile = pathToFile.substring(1, pathToFile.length() - 1);
            System.out.println(pathToFile);
        }

    }

    public void importQuestion(ActionEvent event) throws IOException {
        QuestionManage questionManage = null;
        try {
            questionManage = new QuestionManage();
            if(!pathToFile.equals(""))
                questionManage.importQuestions(pathToFile, QuestionBankTree.fullyCategory);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void switchToScene3(ActionEvent event) throws IOException {
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

    public void switchToQuestionList(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("QuestionList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

