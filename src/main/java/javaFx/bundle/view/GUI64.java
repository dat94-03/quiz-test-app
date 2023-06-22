package javaFx.bundle.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI64 implements Initializable {
    @FXML
    private VBox questionBox;
   @FXML
   private AnchorPane menuPane ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=1;i<=55;i++) {
            VBox vBox = new VBox() ;
            vBox.setPrefHeight(56);
            vBox.setPrefWidth(915);
            vBox.setMaxHeight(Double.POSITIVE_INFINITY);
            vBox.setMaxWidth(Double.POSITIVE_INFINITY);
            vBox.setMinHeight(Double.NEGATIVE_INFINITY);
            vBox.setMinWidth(Double.NEGATIVE_INFINITY);


            HBox hBox = new HBox();
            hBox.setPrefWidth(915);
            hBox.setPrefHeight(48);
            hBox.setStyle("-fx-background-color: #e7e7e7");

            Image image1 = new Image(getClass().getResourceAsStream("/Img.img/add.png"));
            ImageView add = new ImageView();
            add.setPickOnBounds(true);
            add.setPreserveRatio(true);
            add.setImage(image1);
            add.setFitHeight(19);
            add.setFitWidth(25);
            HBox.setMargin(add, new Insets(5, 0, 0, 5));
            hBox.getChildren().add(add);

            Label numberLabel = new Label(String.valueOf(i));
            numberLabel.setAlignment(Pos.CENTER);
            numberLabel.setPrefWidth(40);
            numberLabel.setPrefHeight(31);
            numberLabel.setStyle("-fx-background-color: #b1b1b1");
            numberLabel.setFont(Font.font(18));
            HBox.setMargin(numberLabel, new Insets(8, 0, 0, 5));
            hBox.getChildren().add(numberLabel);

            Image image2 = new Image(getClass().getResourceAsStream("/Img.img/3.png"));
            ImageView threedot = new ImageView();
            threedot.setPreserveRatio(true);
            threedot.setPickOnBounds(true);
            threedot.setImage(image2);
            threedot.setFitHeight(23);
            threedot.setFitWidth(30);
            HBox.setMargin(threedot, new Insets(12, 0, 0, 10));
            hBox.getChildren().add(threedot);

            Image image3 = new Image(getClass().getResourceAsStream("/Img.img/setting.png"));
            ImageView setting = new ImageView(image3);
            setting.setPickOnBounds(true);
            setting.setPreserveRatio(true);
            setting.setFitHeight(20);
            setting.setFitWidth(31);
            HBox.setMargin(setting, new Insets(17, 0, 0, 6));
            hBox.getChildren().add(setting);

            Label questionLabel = new Label(String.valueOf(i));
            questionLabel.setPrefHeight(46);
            questionLabel.setPrefWidth(531);
            questionLabel.setFont(Font.font(18));
            HBox.setMargin(questionLabel, new Insets(1, 0, 0, 10));
            hBox.getChildren().add(questionLabel);

            Pane pane = new Pane();
            pane.setPrefHeight(48);
            pane.setPrefWidth(81);
            hBox.getChildren().add(pane);

            Image image4 = new Image(getClass().getResourceAsStream("/Img.img/magnifying glass.png"));
            ImageView zoom = new ImageView(image4);
            zoom.setPickOnBounds(true);
            zoom.setPreserveRatio(true);
            zoom.setFitWidth(23);
            zoom.setFitHeight(21);
            HBox.setMargin(zoom, new Insets(12, 8, 0, 0));
            hBox.getChildren().add(zoom);

            Image image5 = new Image(getClass().getResourceAsStream("/Img.img/bin.png"));
            ImageView bin = new ImageView(image5) ;
            bin.setPickOnBounds(true);
            bin.setPreserveRatio(true);
            bin.setFitHeight(22);
            bin.setFitWidth(23);
            HBox.setMargin(bin,new Insets(12,10,0,5));
            hBox.getChildren().add(bin) ;

            TextField textField = new TextField() ;
            textField.setPrefHeight(30);
            textField.setPrefWidth(42);
            textField.setStyle("-fx-background-color: #FFFFFF");
            textField.setText("1.00");
            HBox.setMargin(textField,new Insets(8,0,0,0));
            hBox.getChildren().add(textField) ;

            Label label = new Label() ;
            label.setPrefWidth(24);
            label.setPrefHeight(30);
            label.setStyle("-fx-background-color: #FFFFFF");
            Image image6 = new Image(getClass().getResourceAsStream("/Img.img/edit.png")) ;
            ImageView edit = new ImageView(image6) ;
            edit.setPickOnBounds(true);
            edit.setPreserveRatio(true);
            edit.setFitHeight(20);
            edit.setFitWidth(18);
            label.setGraphic(edit);
            HBox.setMargin(label,new Insets(8,0,0,0));
            hBox.getChildren().add(label) ;

            Label spacing = new Label() ;
            spacing.setFont(Font.font(5));
            spacing.setStyle("-fx-background-color: #FFFFFF");
            spacing.setPrefWidth(915);

            vBox.getChildren().add(hBox) ;
            vBox.getChildren().add(spacing) ;

            questionBox.getChildren().add(vBox) ;
        }
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("questionsinGUI6.4.fxml"));
//            VBox fxmlVBox = new VBox();
//            try {
//                fxmlVBox = loader.load();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            QuestioninEditingquiz questioninEditingquiz = loader.getController() ;
//            questioninEditingquiz.setNumber(String.valueOf(i));
//            questioninEditingquiz.setQuestion(String.valueOf(i));
//            questionBox.getChildren().add(fxmlVBox) ;
//
//        }
//        questionBox.setSpacing(5) ;
       }
    }


