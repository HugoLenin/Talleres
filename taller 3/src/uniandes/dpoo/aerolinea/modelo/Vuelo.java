package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase tiene la información de un vuelo particular que cubre una ruta y se lleva a cabo en una cierta fecha.
 * Los vuelos son las unidades a las cuales están asociadas las ventas de tiquetes.
 */
public class Vuelo {
    private Ruta ruta;
    private String fecha; // Formato YYYY-MM-DD
    private Avion avion;
    private Map<String, Tiquete> tiquetes;

    /**
     * Crea un nuevo vuelo con los parámetros dados.
     * Inicializa el mapa de tiquetes vendidos, pero lo deja vacío.
     * 
     * @param ruta   La ruta que cubrirá el vuelo.
     * @param fecha  La fecha del vuelo en formato YYYY-MM-DD.
     * @param avion  El avión que realizará el vuelo.
     */
    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.tiquetes = new HashMap<>();
    }

    /**
     * Obtiene la ruta del vuelo.
     * 
     * @return La ruta del vuelo.
     */
    public Ruta getRuta() {
        return ruta;
    }

    /**
     * Obtiene la fecha del vuelo.
     * 
     * @return La fecha en formato YYYY-MM-DD.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Obtiene el avión asignado al vuelo.
     * 
     * @return El avión que realizará el vuelo.
     */
    public Avion getAvion() {
        return avion;
    }

    /**
     * Obtiene los tiquetes vendidos para este vuelo.
     * 
     * @return Colección de tiquetes vendidos.
     */
    public Collection<Tiquete> getTiquetes() {
        return tiquetes.values();
    }

    /**
     * Vende una determinada cantidad de tiquetes para el vuelo y los deja registrados en el mapa de tiquetes.
     * 
     * @param cliente     El cliente que compra los tiquetes.
     * @param calculadora La calculadora de tarifas usada para determinar el precio.
     * @param cantidad    La cantidad de tiquetes a vender.
     * @return El valor total de los tiquetes vendidos.
     * @throws VueloSobrevendidoException Si no hay suficiente espacio en el vuelo.
     */
    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)
            throws VueloSobrevendidoException {
        if (tiquetes.size() + cantidad > avion.getCapacidad()) {
        	throw new VueloSobrevendidoException(this);
        }

        int total = 0;
        for (int i = 0; i < cantidad; i++) {
            String codigoTiquete = "T" + (tiquetes.size() + 1);
            Tiquete nuevoTiquete = new Tiquete(codigoTiquete, this, cliente, calculadora.calcularTarifa(this, cliente));
            tiquetes.put(codigoTiquete, nuevoTiquete);
            total += nuevoTiquete.getTarifa();
        }
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vuelo vuelo = (Vuelo) obj;
        return fecha.equals(vuelo.fecha) && ruta.equals(vuelo.ruta) && avion.equals(vuelo.avion);
    }
}