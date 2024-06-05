module sbu.cs.youtube {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens sbu.cs.youtube to javafx.fxml;
    exports sbu.cs.youtube;
}