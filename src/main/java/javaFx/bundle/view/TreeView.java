package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.QuestionManage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TreeView implements Initializable {
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
        treeView.setStyle("-fx-font-size: 16px;");
        treeView.setShowRoot(true);

        TreeItem<String> rootItem = new TreeItem<>("Course: IT");
        rootItem.setExpanded(true);

        TreeItem<String> rootItem2 = new TreeItem<>("Top for IT ");
        rootItem2.setExpanded(true);

        TreeItem<String> branchItem1 = new TreeItem<>("C câu hỏi dễ (256)");
        TreeItem<String> branchItem2 = new TreeItem<>("C from linh tinh 1(5)");
        TreeItem<String> branchItem3 = new TreeItem<>("C khó (56)");
        TreeItem<String> branchItem4 = new TreeItem<>("Công nghệ GK2 lớp 7(53)");
        TreeItem<String> branchItem5 = new TreeItem<>("Default for IT");
        branchItem5.setExpanded(true);
        TreeItem<String> branchItem6 = new TreeItem<>("From me - de thi GK2 L7 Sinh(21)");
        TreeItem<String> branchItem7 = new TreeItem<>("Sinh học kỳ 2 L7(67)");
        TreeItem<String> branchItem8 = new TreeItem<>("Sử Địa kỳ 2 L7 (130)");
        TreeItem<String> branchItem9 = new TreeItem<>("Tin học GK2 L7 (94)");
        TreeItem<String> branchItem10 = new TreeItem<>("Vật lý GK2 L7 (121)");
        TreeItem<String> leafItem1 = new TreeItem<>("Dễ");
        leafItem1.setExpanded(true);
        TreeItem<String> leafItem2 = new TreeItem<>("Khó");
        leafItem2.setExpanded(true);
        TreeItem<String> leafItem3 = new TreeItem<>("Tự luận 20221");
        TreeItem<String> leafItem4 = new TreeItem<>("Tự luận KTLT");

        TreeItem<String> leafItem5 = new TreeItem<>("20221");
        TreeItem<String> leafItem6 = new TreeItem<>("Trước đến 20211");
        TreeItem<String> leafItem7 = new TreeItem<>("Khó 20221");
        TreeItem<String> leafItem8 = new TreeItem<>("Khó cho đến 20211");

        treeView.setRoot(rootItem);
        rootItem.getChildren().addAll(rootItem2);

        rootItem2.getChildren().addAll(branchItem1,branchItem2,branchItem3,branchItem4,branchItem5,branchItem6,branchItem7,branchItem8,branchItem9,branchItem10);
        branchItem5.getChildren().addAll(leafItem1,leafItem2,leafItem3,leafItem4);
        leafItem1.getChildren().addAll(leafItem5, leafItem6);
        leafItem2.getChildren().addAll(leafItem7, leafItem8);
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
            if (selectedItem.getValue() == "Sinh học kỳ 2 L7(67)" ) {
                switchToScene("QuestionList.fxml");
            }
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