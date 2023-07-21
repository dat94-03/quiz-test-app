package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.CategoriesManage;
import model.Category;
import model.LibraryForUs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionBankTree implements Initializable {
    public static String currentCategory = new String();
    public static String fullyCategory = new String();
    @FXML
    public javafx.scene.control.TreeView treeView;
    @FXML
    public Label defaultBox;
    @FXML
    public void displayTreeView() {
        treeView.setVisible(true);
    }
    @FXML
    public void displayTreeViewOff() {
        treeView.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentCategory = "Default"; // if user not selected category, file and question save to Default category

        treeView.setStyle("-fx-font-size: 16px;");
        treeView.setShowRoot(false);

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

        treeView.setRoot(rootItem);


    }

    private void switchToScene(String fxmlFile) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) treeView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void selectItem() {
        treeView.setOnMouseClicked(mouseEvent -> {
            TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
            if(selectedItem != null){
                GUI1_1_Controller.isSelectedCategory = true;
                currentCategory = selectedItem.getValue();
                fullyCategory = LibraryForUs.getFullyCategory(selectedItem);
            }
            switchToScene("QuestionList.fxml");
        });
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCategory(MouseEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("AddingCategory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToImport(MouseEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("ImportQuestion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}