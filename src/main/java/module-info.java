module com.example.quiztestapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quiztestapp to javafx.fxml;
    exports com.example.quiztestapp;
}