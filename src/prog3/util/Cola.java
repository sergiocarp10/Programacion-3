package prog3.util;

import prog3.listagenerica.ListaGenericaEnlazada;

public class Cola<T> extends ListaGenericaEnlazada<T> {

    public Cola(){}

    public void encolar(T elem){
        super.agregarFinal(elem);
    }

    public T desencolar(){
        T result = this.tope();
        super.eliminarEn(0);
        return result;
    }

    public T tope(){
        return super.elemento(0);
    }

    public boolean esVacia(){
        return super.esVacia();
    }
}
