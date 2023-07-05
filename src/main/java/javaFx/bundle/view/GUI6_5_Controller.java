package javaFx.bundle.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class GUI6_5_Controller implements Initializable {
    @FXML
    public TreeView treeView;
    @FXML
    private AnchorPane menuPane ;
    @FXML
    private ComboBox comboBox ;
    @FXML
    private HBox numberHBox ;
    @FXML
    private VBox questionVBox ;
    Integer h = 0 ;
    Integer k = 0 ;
    Integer i =0 ;
    Button[] button = new Button[10] ;
    public void action(int i) {
        for (int u = 0; u < 10; u++) {
            button[u].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #d1d1d1");
            button[u].setTextFill(Paint.valueOf("#009FE5"));

        }
        button[i].setStyle("-fx-background-color: #009FE5; -fx-border-color: #d1d1d1");
        button[i].setTextFill(Paint.valueOf("#FFFFFF"));
        h = i ;
        questionVBox.getChildren().clear();

       int dem = 0 ;
        for (int h = 0; h != i; h++)
                dem = dem + 10;
            for (k = dem; k < dem + 10; k++) {
                VBox vBox = new VBox();
                vBox.setPrefWidth(905);
                vBox.setPrefHeight(46);
                vBox.setMaxHeight(Double.POSITIVE_INFINITY);
                vBox.setMaxWidth(Double.POSITIVE_INFINITY);
                vBox.setMinHeight(Double.NEGATIVE_INFINITY);
                vBox.setMinWidth(Double.NEGATIVE_INFINITY);

                HBox hBox = new HBox();
                hBox.setPrefWidth(46);
                hBox.setPrefWidth(905);
                hBox.setStyle("-fx-background-color: #FFFFFF");

                Image image = new Image(getClass().getResourceAsStream("/Img.img/3.png"));
                ImageView threedots = new ImageView(image);
                threedots.setFitWidth(20);
                threedots.setFitHeight(20);
                threedots.setPreserveRatio(true);
                threedots.setPickOnBounds(true);
                HBox.setMargin(threedots, new Insets(8, 0, 0, 20));
                hBox.getChildren().add(threedots);

                Label questionLabel = new Label(String.valueOf(k+1));
                questionLabel.setPrefHeight(40);
                questionLabel.setPrefWidth(720);
                questionLabel.setFont(Font.font(17));
                HBox.setMargin(questionLabel, new Insets(0, 0, 0, 18));
                hBox.getChildren().add(questionLabel);
                Color color = Color.web("#d1d1d1");
                BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5));
                Border border = new Border(borderStroke);

                vBox.getChildren().add(hBox);
                vBox.setBorder(border);
                questionVBox.getChildren().add(vBox);

                menuPane.setPrefHeight(552 + 10*40);
            }
        }


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
                    appear();
                    displayTreeViewOff();
                }
            }
        });


    }
  public void buttonLeft() {
        if(h>0)
        action(h-1);
  }
  public void buttonRight() {
        if(h<9)
        action(h+1);
  }
    public void appear() {
        for( i=0;i<10;i++) {
            button[i] = new Button(String.valueOf(i+1)) ;
            button[i].setMnemonicParsing(false);
            button[i].setPrefWidth(38);
            button[i].setPrefHeight(41);
            button[i].setFont(Font.font(15));
            button[i].setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #d1d1d1");
            button[i].setTextFill(Paint.valueOf("#009FE5"));
            int k = i ;
            button[i].setOnAction(event -> {
                action(k);
            });
            numberHBox.getChildren().add(button[i]) ;
        }
        numberHBox.setSpacing(1);
        for(i=0;i<10;i++) {
            VBox vBox = new VBox() ;
            vBox.setPrefWidth(905);
            vBox.setPrefHeight(46);
            vBox.setMaxHeight(Double.POSITIVE_INFINITY);
            vBox.setMaxWidth(Double.POSITIVE_INFINITY);
            vBox.setMinHeight(Double.NEGATIVE_INFINITY);
            vBox.setMinWidth(Double.NEGATIVE_INFINITY);

            HBox hBox = new HBox() ;
            hBox.setPrefWidth(46);
            hBox.setPrefWidth(905);
            hBox.setStyle("-fx-background-color: #FFFFFF");

            Image image = new Image(getClass().getResourceAsStream("/Img.img/3.png")) ;
            ImageView threedots = new ImageView(image) ;
            threedots.setFitWidth(20);
            threedots.setFitHeight(20);
            threedots.setPreserveRatio(true);
            threedots.setPickOnBounds(true);
            HBox.setMargin(threedots,new Insets(8,0,0,20));
            hBox.getChildren().add(threedots) ;

            Label questionLabel = new Label(String.valueOf(i)) ;
            questionLabel.setPrefHeight(40);
            questionLabel.setPrefWidth(720);
            questionLabel.setFont(Font.font(17));
            HBox.setMargin(questionLabel,new Insets(0,0,0,18));
            hBox.getChildren().add(questionLabel) ;
            Color color = Color.web("#d1d1d1") ;
            BorderStroke borderStroke = new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5)); // set kích thước và màu cho border
            Border border = new Border(borderStroke);

            vBox.getChildren().add(hBox) ;
            vBox.setBorder(border);
            questionVBox.getChildren().add(vBox) ;

            menuPane.setPrefHeight(552+i*42);
        }

        ObservableList<Integer> number = FXCollections.observableArrayList() ;
        for(int i =1 ;i <= 50 ;i++)
            number.add(new Integer(i)) ;
        comboBox.setItems(number);

        count++ ;
    }


}
