package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase abstracta define e implementa los aspectos que son comunes para todos los tipos de clientes de la Aerolínea.
 * Cada cliente, sin importar su tipo, tiene una lista de tiquetes usados y sin usar.
 */
public abstract class Cliente {
    private List<Tiquete> tiquetesSinUsar;
    private List<Tiquete> tiquetesUsados;

    /**
     * Constructor que inicializa las listas de tiquetes del cliente.
     */
    public Cliente() {
        this.tiquetesSinUsar = new ArrayList<>();
        this.tiquetesUsados = new ArrayList<>();
    }

    /**
     * Retorna el identificador único del cliente.
     * 
     * @return Identificador del cliente.
     */
    public abstract String getIdentificador();

    /**
     * Retorna el tipo del cliente.
     * 
     * @return Tipo de cliente como cadena de texto.
     */
    public abstract String getTipoCliente();

    /**
     * Agrega un nuevo tiquete a la lista de tiquetes (sin usar) que ha comprado el cliente.
     * 
     * @param tiquete El nuevo tiquete que se va a agregar.
     */
    public void agregarTiquete(Tiquete tiquete) {
        tiquetesSinUsar.add(tiquete);
    }

    /**
     * Calcula el valor total de los tiquetes que ha comprado un cliente.
     * 
     * @return Valor total de los tiquetes comprados.
     */
    public int calcularValorTotalTiquetes() {
        int total = 0;
        for (Tiquete tiquete : tiquetesSinUsar) {
            total += tiquete.getTarifa();
        }
        for (Tiquete tiquete : tiquetesUsados) {
            total += tiquete.getTarifa();
        }
        return total;
    }

    /**
     * Marca como usados todos los tiquetes del cliente que correspondan a un vuelo determinado.
     * Mueve los tiquetes de la lista de tiquetes sin usar a la lista de tiquetes usados.
     * 
     * @param vuelo El vuelo cuyos tiquetes se marcarán como usados.
     */
    public void usarTiquetes(Vuelo vuelo) {
        List<Tiquete> usados = new ArrayList<>();
        for (Tiquete tiquete : tiquetesSinUsar) {
            if (tiquete.getVuelo().equals(vuelo)) {
                usados.add(tiquete);
            }
        }
        tiquetesSinUsar.removeAll(usados);
        tiquetesUsados.addAll(usados);
    }
}