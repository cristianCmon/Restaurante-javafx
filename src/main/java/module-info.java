module org.example.restaurantejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;


    opens org.example.restaurantejavafx to javafx.fxml;
    exports org.example.restaurantejavafx;
}