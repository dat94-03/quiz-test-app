package javaFx.bundle.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI63 implements Initializable {
    @FXML
    private AnchorPane anchorPane ;
    @FXML
    private VBox questionBox ;
    @FXML
    private VBox zoomBox ;
    @FXML
    public javafx.scene.control.TreeView treeView;
    @FXML
    public Button button;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void displayTreeView() {
        treeView.setVisible(true);
    }
    @FXML
    public void displayTreeViewOff() {
        treeView.setVisible(false);
    }
    Integer count = 0 ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeView.setStyle("-fx-font-size: 16px;");

        TreeItem<String> rootItem = new TreeItem<>("Course: IT");

        TreeItem<String> rootItem2 = new TreeItem<>("Top for IT ");

        TreeItem<String> branchItem1 = new TreeItem<>("C câu hỏi dễ (256)");
        TreeItem<String> branchItem2 = new TreeItem<>("C from linh tinh 1(5)");
        TreeItem<String> branchItem3 = new TreeItem<>("C khó (56)");
        TreeItem<String> branchItem4 = new TreeItem<>("Công nghệ GK2 lớp 7(53)");
        TreeItem<String> branchItem5 = new TreeItem<>("Default for IT");
        TreeItem<String> branchItem6 = new TreeItem<>("From me - de thi GK2 L7 Sinh(21)");
        TreeItem<String> branchItem7 = new TreeItem<>("Sinh học kỳ 2 L7(67)");
        TreeItem<String> branchItem8 = new TreeItem<>("Sử Địa kỳ 2 L7 (130)");
        TreeItem<String> branchItem9 = new TreeItem<>("Tin học GK2 L7 (94)");
        TreeItem<String> branchItem10 = new TreeItem<>("Vật lý GK2 L7 (121)");
        TreeItem<String> leafItem1 = new TreeItem<>("Dễ");
        TreeItem<String> leafItem2 = new TreeItem<>("Khó");
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
    public void selectItem() {

            treeView.setOnMouseClicked(mouseEvent -> {
                if(count == 0) {
                    TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
                    if (selectedItem.getValue() == "Sinh học kỳ 2 L7(67)") {
                        try {
                            addquestionbank();
                            displayTreeViewOff();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });


    }
    public void addquestionbank() throws IOException {
         for(int i=1;i<=51;i++) {
             VBox vBox = new VBox();
             vBox.setPrefWidth(924);
             vBox.setPrefHeight(45);
             vBox.setMaxHeight(Double.POSITIVE_INFINITY);
             vBox.setMaxWidth(Double.POSITIVE_INFINITY);
             vBox.setMinHeight(Double.NEGATIVE_INFINITY);
             vBox.setMinWidth(Double.NEGATIVE_INFINITY);

             HBox hBox = new HBox();
             hBox.setPrefHeight(45);
             hBox.setPrefWidth(924);

             Image image1 = new Image(getClass().getResourceAsStream("/Img.img/add.png"));
             ImageView add = new ImageView(image1);
             add.setPreserveRatio(true);
             add.setPickOnBounds(true);
             add.setFitHeight(19);
             add.setFitWidth(22);
             HBox.setMargin(add, new Insets(10, 0, 0, 0));
             hBox.getChildren().add(add);

             CheckBox checkBox = new CheckBox();
             checkBox.setMnemonicParsing(false);
             checkBox.setFont(Font.font(14));
             HBox.setMargin(checkBox, new Insets(8, 0, 0, 8));
             hBox.getChildren().add(checkBox);

             Image image2 = new Image(getClass().getResourceAsStream("/Img.img/3.png"));
             ImageView threedot = new ImageView(image2);
             threedot.setPickOnBounds(true);
             threedot.setPreserveRatio(true);
             threedot.setFitHeight(18);
             threedot.setFitWidth(26);
             HBox.setMargin(threedot, new Insets(14, 0, 0, 2));
             hBox.getChildren().add(threedot);

             Label questionLabel = new Label(String.valueOf(i));
             questionLabel.setFont(Font.font(18));
             questionLabel.setPrefWidth(812);
             questionLabel.setPrefHeight(46);
             HBox.setMargin(questionLabel,new Insets(0,0,0,10));
             hBox.getChildren().add(questionLabel) ;

             Image image3 = new Image(getClass().getResourceAsStream("/Img.img/magnifying glass.png")) ;
             ImageView zoom = new ImageView(image3) ;
             zoom.setPreserveRatio(true);
             zoom.setPickOnBounds(true);
             zoom.setFitHeight(17);
             zoom.setFitWidth(18);
             Label zoomLabel = new Label() ;
             zoomLabel.setFont(Font.font(34));
             zoomLabel.setPrefWidth(29);
             zoomLabel.setGraphic(zoom);
             hBox.getChildren().add(zoomLabel) ;

             if(i%2 == 0) {
                 questionLabel.setStyle("-fx-background-color: #E6E6E6");
                 zoomLabel.setStyle("-fx-background-color: #E6E6E6");  }
             else if (i%2 == 1) {
                 questionLabel.setStyle("-fx-background-color: #FFFFFF");
                 zoomLabel.setStyle("-fx-background-color: #FFFFFF"); }
             vBox.getChildren().add(hBox) ;
             VBox.setMargin(vBox,new Insets(0,0,0,5));
             questionBox.getChildren().add(vBox) ;
             if(i>=10)
             anchorPane.setPrefHeight(760+110+(i-10)*45) ;
         }
        VBox vBox2 = new VBox() ;
         vBox2.setPrefHeight(100);
         vBox2.setPrefWidth(924);
        vBox2.setMaxHeight(Double.POSITIVE_INFINITY);
        vBox2.setMaxWidth(Double.POSITIVE_INFINITY);
        vBox2.setMinHeight(Double.NEGATIVE_INFINITY);
        vBox2.setMinWidth(Double.NEGATIVE_INFINITY);

        Label buttonLabel = new Label() ;
        buttonLabel.setPrefWidth(924);
        buttonLabel.setPrefHeight(27);
        buttonLabel.setStyle("-fx-background-color: #DDDDDD");
        VBox.setMargin(buttonLabel,new Insets(4,0,0,0));
        vBox2.getChildren().add(buttonLabel) ;

        Button buttonaddquiz = new Button() ;
        buttonaddquiz.setMnemonicParsing(false);
        buttonaddquiz.setPrefHeight(41);
        buttonaddquiz.setPrefWidth(342);
        buttonaddquiz.setStyle("-fx-background-color: #0073A5");
        buttonaddquiz.setText("ADD SELECTED QUESTIONS TO THE QUIZ ");
        buttonaddquiz.setTextFill(Color.WHITE);
        buttonaddquiz.setFont(Font.font(17)) ;
        buttonaddquiz.setOnAction(event -> {
            try {
                switchToGUI64(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        VBox.setMargin(buttonaddquiz,new Insets(10,0,0,0));
        vBox2.getChildren().add(buttonaddquiz) ;
        questionBox.getChildren().add(vBox2) ;

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Addseletedquestionstothequiz.fxml"));
//        VBox fxmlVBox = loader.load();
//        questionBox.getChildren().add(fxmlVBox);
        count ++ ;

    }
    public void switchToGUI64(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GUI6.4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
