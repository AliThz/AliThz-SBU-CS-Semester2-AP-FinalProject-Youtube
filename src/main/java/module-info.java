module sbu.cs.youtube {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires javafx.media;
    requires animatefx;


    opens sbu.cs.youtube to javafx.fxml;
    exports sbu.cs.youtube;
    exports sbu.cs.youtube.Client.Controller;
    opens sbu.cs.youtube.Client.Controller to javafx.fxml;
}