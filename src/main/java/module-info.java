module com.example.ourproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.scratchpad;
    requires java.sql;
    requires java.desktop;


    opens javaFx.bundle.view to javafx.fxml;
    exports javaFx.bundle.view;
}