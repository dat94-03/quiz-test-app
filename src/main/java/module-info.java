module com.example.ourproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ourproject to javafx.fxml;
    exports com.example.ourproject;
}