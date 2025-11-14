package org.example.restaurantejavafx;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// https://www.youtube.com/watch?v=MAw5Ku1OVFA
public class PeticionesHttp {

    private final String BASE_URL = "http://localhost:3000";
    private final HttpClient cliente;


    public PeticionesHttp() {
        cliente = HttpClient.newHttpClient();
    }


    public String getMesas() throws IOException, InterruptedException {
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/mesas"))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

        return respuesta.body();
    }

    public String getMenus() throws IOException, InterruptedException {
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/menus"))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

        return respuesta.body();
    }
}
