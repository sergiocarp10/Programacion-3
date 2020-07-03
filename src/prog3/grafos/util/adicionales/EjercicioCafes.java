package prog3.grafos.util.adicionales;

import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class EjercicioCafes<T> {

    // x: cantidad máxima de cuadras que una persona está dispuesta a caminar
    // y: cantidad a superar de cafés para que la esquina sea considerada valiosa
    // la ciudad es un grafo pesado, donde cada arista es la cantidad de cafés
    public ListaGenerica<T> esquinasValiosas(Grafo<T> ciudad, int x, int y){
        ListaGenerica<T> result = new ListaGenericaEnlazada<>();

        if (ciudad != null && !ciudad.esVacio()){
            ListaGenerica<Vertice<T>> esquinas = ciudad.listaDeVertices();
            boolean[] visitados = new boolean[esquinas.tamanio()];

            esquinas.comenzar();
            while (!esquinas.fin()){
                Vertice<T> aux = esquinas.proximo();
                if (this.esEsqValiosa(ciudad, aux, visitados, x, y))
                    result.agregarFinal(aux.dato());
            }
        }

        return result;
    }

    private boolean esEsqValiosa(Grafo<T> g, Vertice<T> v, boolean[] visited, int dx, int dy) {
        // caso base "bueno": alcancé cafés pedidos
        if (dy <= 0) return true;

        // caso base "malo": no debo caminar más
        if (dx == 0 || visited[v.posicion()])
            return false;

        // sino sigo...
        boolean cumple = false;
        visited[v.posicion()] = true;
        ListaGenerica<Arista<T>> cuadras = g.listaDeAdyacentes(v);
        Arista<T> arista;

        cuadras.comenzar();
        while (!cuadras.fin() && !cumple){
            arista = cuadras.proximo();
            cumple = this.esEsqValiosa(g, arista.verticeDestino(), visited, dx-1, dy-arista.peso());
        }

        visited[v.posicion()] = false;
        return cumple;
    }
}
