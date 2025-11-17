package org.example.restaurantejavafx;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

// https://www.youtube.com/watch?v=MAw5Ku1OVFA
public class PeticionesHttp {

    private final String BASE_URL = "http://localhost:3000";
    private final HttpClient cliente;
    private final ObjectMapper objectMapper;


    public PeticionesHttp() {
        this.cliente = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }


    public Mesa recibirMesa(String idMesa) throws IOException, InterruptedException {
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/mesas/" + idMesa))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(respuesta.body(), new TypeReference<>() {});
    }

    public List<Mesa> recibirMesas() throws IOException, InterruptedException {
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/mesas"))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(respuesta.body(), new TypeReference<>() {});
    }

    public List<Menu> recibirMenus() throws IOException, InterruptedException {
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/menus"))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(respuesta.body(), new TypeReference<>() {});
    }

    public void actualizarMesa(Mesa mesa) throws IOException, InterruptedException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonRequestBody = objectMapper.writeValueAsString(mesa);

        String peticionJson = "{ \"numero\": \"" + mesa.getNumero() + "\", \"ocupada\": \"" + mesa.isOcupada() + "\", \"bloqueada\": \"" + mesa.isBloqueada() + "\", \"idComanda\": \"" + mesa.getIdComanda() + "\" }";
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/mesas/" + mesa.getId()))
                .header("Content-Type", "application/json") // Essential header
                .PUT(HttpRequest.BodyPublishers.ofString(peticionJson))
                .build();

        cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
    }

    public Comanda recibirComanda(String idComanda) throws IOException, InterruptedException {
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/comandas/" + idComanda))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(respuesta.body(), new TypeReference<>() {});
    }

}
