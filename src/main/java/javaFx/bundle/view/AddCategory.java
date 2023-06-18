package javaFx.bundle.view;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCategory implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String selectCategory = new String();
    private String fullyPath = new String("");

    @FXML
    private Label categoryLabel;
    @FXML
    private javafx.scene.control.TreeView<String> categoryTreeView;
    @FXML
    private TextField newCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryLabel.setText("  " + TreeView.currentCategory);

        categoryTreeView.setStyle("-fx-font-size: 16px;");
        categoryTreeView.setShowRoot(true);

        TreeItem<String> rootItem = new TreeItem<>("root");
        rootItem.setExpanded(true);

        CategoriesManage build = null;
        try {
            build = new CategoriesManage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Category rootCategory = build.getRoot();
        LibraryForUs.accessChildrenTreeView(rootCategory, rootItem);

        categoryTreeView.setRoot(rootItem);
    }

    public void selectItem() {
        if(newCategory.getText().equals("") == false){
            categoryTreeView.setOnMouseClicked(mouseEvent -> {
                TreeItem<String> selectedItem = (TreeItem<String>) categoryTreeView.getSelectionModel().getSelectedItem();
                selectCategory = selectedItem.getValue();
                System.out.println(selectCategory);

//            get fully path
                String[] fullyCategory = new String[10];
                TreeItem<String> tmp = selectedItem;
                int i = 0;
                fullyCategory[i] = tmp.getValue();
                while (tmp.getParent() != null){
                    tmp = tmp.getParent();
                    fullyCategory[++i] = tmp.getValue();
                }
                for(int j = i; j >= 0; j--){
                    fullyPath += fullyCategory[j] + "/";
                }
                fullyPath += newCategory.getText();
                QuestionManage qm = null;
                try {
                    qm = new QuestionManage();
                    qm.addCategory(fullyPath);
                    System.out.println(fullyPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

//            disable TreeView when we done
                categoryTreeView.setVisible(false);
            });
        }
        else{
            categoryTreeView.setVisible(false);
        }

    }

    @FXML
    public void displayTreeView() {
        categoryTreeView.setVisible(true);
    }
    @FXML
    public void displayTreeViewOff() {
        categoryTreeView.setVisible(false);
    }

    private void switchToScene(String fxmlFile) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) categoryTreeView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void switchToEditQuestion(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EditQuestion.fxml"));
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
