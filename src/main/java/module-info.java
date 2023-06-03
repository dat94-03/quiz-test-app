module view {
    requires javafx.controls;
    requires javafx.fxml;

    opens javaFx.bundle.view to javafx.fxml;
    exports javaFx.bundle.view;

}