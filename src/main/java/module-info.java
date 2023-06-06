module com.example.ourproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.scratchpad;


    opens javaFx.bundle.view to javafx.fxml;
    exports javaFx.bundle.view;
}