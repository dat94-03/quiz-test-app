package javaFx.bundle.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI6_1_Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGUI6_2a(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("gui6.2a.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
