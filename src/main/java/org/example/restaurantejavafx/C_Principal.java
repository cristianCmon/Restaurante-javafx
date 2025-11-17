package org.example.restaurantejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class C_Principal {

    @FXML
    private Label mesa0, mesa1, mesa2, mesa3, mesa4, idMesaSeleccionada;

    @FXML
    private Button btnVerPedido, btnConfirmar, btnServirPedido;

    private PeticionesHttp llamarApi = new PeticionesHttp();
    private List<Mesa> mesas = new ArrayList<>();
    private List<Label> mesasVisuales = new ArrayList<>();
    int indiceMesa;
    private final List<Menu> menus = new ArrayList<>();
    private Comanda comanda;


    @FXML
    private void initialize() throws IOException, InterruptedException {
        setearBotonera(false, false, false);

        mesasVisuales.add(mesa0);
        mesasVisuales.add(mesa1);
        mesasVisuales.add(mesa2);
        mesasVisuales.add(mesa3);
        mesasVisuales.add(mesa4);

        mesas.addAll(llamarApi.recibirMesas());
        menus.addAll(llamarApi.recibirMenus());

//        comanda = llamarApi.recibirComanda("691afa8672e968af8f239d80");
//        for (Mesa mesa : mesas) {
//            System.out.println(mesa);
//        }
//        for (Menu menu : menus) {
//            System.out.println(menu);
//        }

        escuchadorMesas();

    }


    public void clicMesa1(MouseEvent mouseEvent) {
        idMesaSeleccionada.setText("Mesa 1");
        indiceMesa = 0;
        setearBotonera(mesas.get(0).isBloqueada(), mesas.get(0).isBloqueada(), false);
    }

    public void clicMesa2(MouseEvent mouseEvent) {
        idMesaSeleccionada.setText("Mesa 2");
        indiceMesa = 1;
        setearBotonera(mesas.get(1).isBloqueada(), mesas.get(1).isBloqueada(), false);
    }

    public void clicMesa3(MouseEvent mouseEvent) {
        idMesaSeleccionada.setText("Mesa 3");
        indiceMesa = 2;
        setearBotonera(mesas.get(2).isBloqueada(), mesas.get(2).isBloqueada(), false);
    }

    public void clicMesa4(MouseEvent mouseEvent) {
        idMesaSeleccionada.setText("Mesa 4");
        indiceMesa = 3;
        setearBotonera(mesas.get(3).isBloqueada(), mesas.get(3).isBloqueada(), false);
    }

    public void clicMesa5(MouseEvent mouseEvent) {
        idMesaSeleccionada.setText("Mesa 5");
        indiceMesa = 4;
        setearBotonera(mesas.get(4).isBloqueada(), mesas.get(4).isBloqueada(), false);
    }


    public void clicVerPedido(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Comanda mesa " + (indiceMesa + 1));
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea eliminar esta ficha?:\n\n");
        Optional<ButtonType> botonPulsado = alert.showAndWait();

        System.out.println(comanda);
//        if (botonPulsado.get().equals(ButtonType.OK)) {
//
//        }
    }

    public void clicConfirmar(ActionEvent actionEvent) {
        btnServirPedido.setDisable(false);
        btnConfirmar.setDisable(true);
    }

    public void clicServirPedido(ActionEvent actionEvent) throws IOException, InterruptedException {
        mesas.get(indiceMesa).setBloqueada(false);
        llamarApi.actualizarMesa(mesas.get(indiceMesa));
        btnServirPedido.setDisable(true);
    }


    public void setearBotonera(boolean verPedido, boolean confirmarPedido, boolean servirPedido) {
        btnVerPedido.setDisable(!verPedido);
        btnConfirmar.setDisable(!confirmarPedido);
        btnServirPedido.setDisable(!servirPedido);
    }

    public void escuchadorMesas() {
        ScheduledExecutorService temporizador = Executors.newSingleThreadScheduledExecutor();

        // Definimos la tarea que ejecutará
        Runnable actualizarMesas = new Runnable() {
            @Override
            public void run() {
                try {
                    mesas.clear();
                    mesas = llamarApi.recibirMesas();

                    for (int i = 0; i < mesas.size(); i++) {
                        if (mesas.get(i).isBloqueada()) {
                            mesasVisuales.get(i).setStyle("-fx-background-color:#43A047; -fx-text-fill: #FFFFFF;");
                            comanda = llamarApi.recibirComanda(mesas.get(i).getIdComanda());
                        } else if (mesas.get(i).isOcupada()) {
                            mesasVisuales.get(i).setStyle("-fx-background-color:#E4A81D; -fx-text-fill: #FFFFFF;");
                        } else {
                            mesasVisuales.get(i).setStyle("-fx-background-color:#303030; -fx-text-fill: #FFFFFF;");
                        }
                    }

                    System.out.println("actualizando mesas");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        int retardoInicial = 3;
        int retardo = 3;

        temporizador.scheduleAtFixedRate(actualizarMesas, retardoInicial, retardo, TimeUnit.SECONDS);
    }

}
