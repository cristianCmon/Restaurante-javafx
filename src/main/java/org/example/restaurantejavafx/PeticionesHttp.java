package org.example.restaurantejavafx;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.youtube.com/watch?v=MAw5Ku1OVFA
public class PeticionesHttp {

    private final String BASE_URL = "http://localhost:3000";
    private final HttpClient cliente;
    private final ObjectMapper objectMapper;
    private HttpRequest peticion;
    private List<Object> resultadoApi;


    public PeticionesHttp() {
        this.cliente = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }


    public Mesa recibirMesa(String idMesa) {
        resultadoApi = consumirApi("GET", "/mesas/" + idMesa);
        return (Mesa) resultadoApi.getFirst();
    }

    public List<Mesa> recibirMesas() {
        resultadoApi = consumirApi("GET", "/mesas/");
        //https://stackoverflow.com/questions/933447/how-do-you-cast-a-list-of-supertypes-to-a-list-of-subtypes
        return (List<Mesa>)(List<?>) resultadoApi;
    }

    public void actualizarMesa(String idMesa) {
        resultadoApi = consumirApi("PUT", "/mesas/" + idMesa);
    }

    public List<Menu> recibirMenus() {
        resultadoApi = consumirApi("GET", "/menus/");
        //https://stackoverflow.com/questions/933447/how-do-you-cast-a-list-of-supertypes-to-a-list-of-subtypes
        return (List<Menu>)(List<?>) resultadoApi;
    }

    public String crearComanda() {
        resultadoApi = consumirApi("POST", "/comandas");
        return resultadoApi.getFirst().toString();
    }

    private List<Object> consumirApi(String tipo, String endPoint) {
        List<Object> objetoRespuesta = new ArrayList<>();
        HttpResponse<String> respuesta = null;
        String prueba = "";

        switch (tipo) {
//            case "POST":
//                peticion = HttpRequest.newBuilder()
//                    .uri(URI.create(BASE_URL + endPoint))
//                    .POST()
//                    .build();
//                break;

            case "GET":
                peticion = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endPoint))
                    .GET()
                    .build();
                break;

//            case "PUT":
//                peticion = HttpRequest.newBuilder()
//                    .uri(URI.create(BASE_URL + endPoint))
//                    .PUT()
//                    .build();
//                break;
        }

        try {
            respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
            prueba = respuesta.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        } finally {
//            cliente.close();
        }

        System.out.println("hasta aqui");

//        objetoRespuesta.addAll(respuesta.body());
        return objetoRespuesta;


//        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
//
//        return respuesta.body();
    }

//    public String recibirMesas() throws IOException, InterruptedException {
//        HttpRequest peticion = HttpRequest.newBuilder()
//                .uri(URI.create(BASE_URL + "/mesas"))
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
//
//        return respuesta.body();
//    }
//
//    public String recibirMenus() throws IOException, InterruptedException {
//        HttpRequest peticion = HttpRequest.newBuilder()
//                .uri(URI.create(BASE_URL + "/menus"))
//                .GET()
//                .build();
//
//        HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
//
//        return respuesta.body();
//    }


    /*
        @GET("mesas")
    Call<List<Mesa>> leerMesasLocal();

    @GET("mesas/{id}")
    Call<Mesa> leerMesaLocal(
        @Path("id") String id
    );

    @GET("menus")
    Call<List<Pedido>> leerMenusLocal();

    @FormUrlEncoded
    @PUT("mesas/{id}")
    Call<Mesa> actualizarMesa(
        @Path("id") String id,
        @Field("ocupada") boolean ocupada,
        @Field("bloqueada") boolean bloqueada
    );

    @FormUrlEncoded
    @POST("comandas")
    Call<Comanda> crearComanda(
            @Field("idMesa") String idMesa,
            @Field("fecha") String fecha,
            @Field("idMenus") List<String> idMenus,
            @Field("cantidadMenus") List<Integer> cantidadMenus
    );
    * */
}
