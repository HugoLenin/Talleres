package uniandes.dpoo.aerolinea.modelo;

public class Avion {

    // Atributos privados
    private String nombre;
    private int capacidad;

    /**
     * Construye un avión con un determinado nombre y capacidad.
     *
     * @param nombre    El nombre con el que se identifica al avión.
     * @param capacidad La capacidad del avión.
     */
    public Avion(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    /**
     * Retorna el nombre del avión.
     *
     * @return Nombre del avión.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna la capacidad del avión.
     *
     * @return Capacidad del avión.
     */
    public int getCapacidad() {
        return capacidad;
    }
}
