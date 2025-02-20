package uniandes.dpoo.aerolinea.modelo.cliente;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son personas naturales.
 */
public class ClienteNatural extends Cliente {
    public static final String NATURAL = "Natural";

    private String nombre;

    /**
     * Construye un nuevo cliente natural dado su nombre.
     * 
     * @param nombre El nombre del cliente.
     */
    public ClienteNatural(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el nombre del cliente como su identificador único.
     * 
     * @return Identificador del cliente.
     */
    @Override
    public String getIdentificador() {
        return nombre;
    }

    /**
     * Retorna el tipo del cliente.
     * 
     * @return Tipo de cliente como cadena de texto.
     */
    @Override
    public String getTipoCliente() {
        return NATURAL;
    }
}