module view {
    requires javafx.controls;
    requires javafx.fxml;


    opens view to javafx.fxml;
    exports view;
    exports model;
    opens model to javafx.fxml;
}