package org.example.restaurantejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("V-Principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Carga de estilos css por código
        String css = this.getClass().getResource("/estilos/V-Principal.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Gestor Comandas");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.setAlwaysOnTop(true);

        stage.show();
    }
}
