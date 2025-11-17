package org.example.restaurantejavafx;

import java.util.List;

public class Comanda {

    private String _id;
    private String fecha;
    private List<String> idMenus;
    private List<Integer> cantidadMenus;


    public Comanda() {};

    public Comanda(String _id, String fecha, List<String> idMenus, List<Integer> cantidadMenus) {
        this._id = _id;
        this.fecha = fecha;
        this.idMenus = idMenus;
        this.cantidadMenus = cantidadMenus;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<String> getIdMenus() {
        return idMenus;
    }

    public void setIdMenus(List<String> idMenus) {
        this.idMenus = idMenus;
    }

    public List<Integer> getCantidadMenus() {
        return cantidadMenus;
    }

    public void setCantidadMenus(List<Integer> cantidadMenus) {
        this.cantidadMenus = cantidadMenus;
    }

    @Override
    public String toString() {
        return this.fecha;
    }
}
