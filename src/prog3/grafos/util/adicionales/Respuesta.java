package prog3.grafos.util.adicionales;

import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class Respuesta {
    private int distanciaMax;
    private ListaGenerica<Estacion> listaEstaciones;

    public Respuesta() {
        this.distanciaMax = -1;
        this.listaEstaciones = new ListaGenericaEnlazada<>();
    }

    public Respuesta(int distanciaMax, ListaGenerica<Estacion> listaEstaciones) {
        this.distanciaMax = distanciaMax;
        this.listaEstaciones = listaEstaciones;
    }

    public int getDistanciaMax() {
        return distanciaMax;
    }

    public void setDistanciaMax(int distanciaMax) {
        this.distanciaMax = distanciaMax;
    }

    public ListaGenerica<Estacion> getListaEstaciones() {
        return listaEstaciones;
    }

    public void setListaEstaciones(ListaGenerica<Estacion> listaEstaciones) {
        this.listaEstaciones = listaEstaciones;
    }
}
