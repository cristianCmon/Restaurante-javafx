package org.example.restaurantejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class C_Principal {

    @FXML
    private Label welcomeText;

    private final PeticionesHttp llamarApi = new PeticionesHttp();
    private final List<Mesa> mesas = new ArrayList<>();
    private final List<Menu> menus = new ArrayList<>();


    @FXML
    private void initialize() throws IOException, InterruptedException {
        mesas.addAll(llamarApi.recibirMesas());
        menus.addAll(llamarApi.recibirMenus());

        for (Mesa mesa : mesas) {
            System.out.println(mesa);
        }

        for (Menu menu : menus) {
            System.out.println(menu);
        }
//        System.out.println(llamarApi.getMesas());
//        System.out.println(llamarApi.getMenus());
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
