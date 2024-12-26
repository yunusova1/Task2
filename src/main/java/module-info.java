module com.example.builders {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.builders to javafx.fxml;
    exports com.example.builders;
}