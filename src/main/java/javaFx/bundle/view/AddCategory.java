package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
        categoryLabel.setText("  " + QuestionBankTree.currentCategory);

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
        categoryTreeView.setOnMouseClicked(mouseEvent -> {
            TreeItem<String> selectedItem = (TreeItem<String>) categoryTreeView.getSelectionModel().getSelectedItem();
            selectCategory = selectedItem.getValue();

//            get fully path of category

            fullyPath = LibraryForUs.getFullyCategory(selectedItem);

//            disable TreeView when we done
            categoryTreeView.setVisible(false);
//            set label after change parent category
            categoryLabel.setText("  " + selectedItem.getValue());
        });
    }

    public void addCategory(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(newCategory.getText().equals("")){
            alert.setContentText("You did'nt set new category name");
        }
        else {
            fullyPath += ( "/" + newCategory.getText());
            QuestionManage qm = null;
            try {
                qm = new QuestionManage();
                qm.addCategory(fullyPath);
                alert.setContentText("You save new category successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        alert.showAndWait();
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
