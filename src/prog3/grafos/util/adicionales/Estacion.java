package prog3.grafos.util.adicionales;

public class Estacion {
    private String nombre;
    private boolean terminal;

    public Estacion(){}

    public Estacion(String nombre, boolean terminal) {
        this.nombre = nombre;
        this.terminal = terminal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }
}
