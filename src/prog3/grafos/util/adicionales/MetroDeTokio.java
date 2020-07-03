package prog3.grafos.util.adicionales;

import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class MetroDeTokio {
    private final Grafo<Estacion> mapa;

    public MetroDeTokio(Grafo<Estacion> mapa) {
        this.mapa = mapa;
    }

    // PRECONDICIÓN: ORIGEN SEGURO EXISTE
    public Respuesta distanciaMaxima(String origen){
        Respuesta result = new Respuesta();

        ListaGenerica<Vertice<Estacion>> vertices = mapa.listaDeVertices();
        Vertice<Estacion> vOrigen = null, aux;

        // buscar vertice origen
        vertices.comenzar();
        while (vOrigen == null) {
            aux = vertices.proximo();
            if (aux.dato().getNombre().equals(origen))
                vOrigen = aux;
        }

        // preparar dfs
        ListaGenerica<Estacion> caminoTemp = new ListaGenericaEnlazada<>();
        boolean[] visitados = new boolean[vertices.tamanio()];      // todo en false
        int[] maximo = new int[]{-1};   // por referencia

        // buscar en profundidad y actualizar resultados
        this.dfs(vOrigen, result.getListaEstaciones(), caminoTemp, visitados, maximo, 0);
        result.setDistanciaMax(maximo[0]);

        return result;
    }

    private void dfs(Vertice<Estacion> v, ListaGenerica<Estacion> listaMax, ListaGenerica<Estacion> camino,
                     boolean[] visitados, int[] distMax, int distAct){
        // acciones iniciales comunes
        visitados[v.posicion()] = true;
        camino.agregarInicio(v.dato());

        // caso base: es terminal
        if (v.dato().isTerminal()){
            if (distAct > distMax[0]){
                this.copiarInvertido(camino, listaMax);
                distMax[0] = distAct;
            }
        } else {
            // caso recursivo
            ListaGenerica<Arista<Estacion>> adyacencias = this.mapa.listaDeAdyacentes(v);
            Arista<Estacion> arista;

            adyacencias.comenzar();
            while (!adyacencias.fin()){
                arista = adyacencias.proximo();
                if (!visitados[arista.verticeDestino().posicion()]){
                    this.dfs(arista.verticeDestino(), listaMax, camino, visitados,
                            distMax, distAct + arista.peso());
                }
            }
        }

        // acciones finales comunes
        camino.eliminarEn(0);
        visitados[v.posicion()] = false;
    }

    private void copiarInvertido(ListaGenerica<Estacion> fuente, ListaGenerica<Estacion> dest){
        while (!dest.esVacia())
            dest.eliminarEn(0);

        fuente.comenzar();
        while (!fuente.fin())
            dest.agregarInicio(fuente.proximo());
    }
}
