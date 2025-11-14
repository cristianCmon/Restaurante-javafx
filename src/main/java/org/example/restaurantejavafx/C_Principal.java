package org.example.restaurantejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class C_Principal {

    @FXML
    private Label welcomeText;

    private PeticionesHttp llamarApi;


    @FXML
    private void initialize() throws IOException, InterruptedException {
        llamarApi = new PeticionesHttp();
        System.out.println(llamarApi.getMesas());
        System.out.println(llamarApi.getMenus());
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
