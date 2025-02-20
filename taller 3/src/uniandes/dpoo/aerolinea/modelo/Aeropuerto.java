package uniandes.dpoo.aerolinea.modelo;

import java.util.HashSet;
import java.util.Set;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;

/**
 * Esta clase encapsula la información sobre los aeropuertos e implementa algunas operaciones relacionadas con la ubicación geográfica de los aeropuertos.
 * No puede haber dos aeropuertos con el mismo código.
 */
public class Aeropuerto {
    private static final int RADIO_TERRESTRE = 6371; // Radio de la Tierra en km
    private static Set<String> codigosUtilizados = new HashSet<>();

    private String nombre;
    private String codigo;
    private String nombreCiudad;
    private double latitud;
    private double longitud;

    /**
     * Construye un nuevo aeropuerto e inicializa sus atributos con los valores dados.
     * 
     * @param nombre       Nombre del aeropuerto.
     * @param codigo       Código único del aeropuerto.
     * @param nombreCiudad Nombre de la ciudad donde se ubica.
     * @param latitud      Latitud del aeropuerto (-90 a 90).
     * @param longitud     Longitud del aeropuerto (-180 a 180).
     * @throws AeropuertoDuplicadoException Si ya existe un aeropuerto con el mismo código.
     */
    public Aeropuerto(String nombre, String codigo, String nombreCiudad, double latitud, double longitud)
            throws AeropuertoDuplicadoException {
        if (codigosUtilizados.contains(codigo)) {
            throw new AeropuertoDuplicadoException("El código " + codigo + " ya está en uso.");
        }
        this.nombre = nombre;
        this.codigo = codigo;
        this.nombreCiudad = nombreCiudad;
        this.latitud = latitud;
        this.longitud = longitud;
        codigosUtilizados.add(codigo);
    }


    public String getNombre() {
        return nombre;
    }


    public String getCodigo() {
        return codigo;
    }


    public String getNombreCiudad() {
        return nombreCiudad;
    }


    public double getLatitud() {
        return latitud;
    }


    public double getLongitud() {
        return longitud;
    }

    public static int calcularDistancia(Aeropuerto aeropuerto1, Aeropuerto aeropuerto2) {
        double lat1 = Math.toRadians(aeropuerto1.getLatitud());
        double lon1 = Math.toRadians(aeropuerto1.getLongitud());
        double lat2 = Math.toRadians(aeropuerto2.getLatitud());
        double lon2 = Math.toRadians(aeropuerto2.getLongitud());

        double deltaX = (lon2 - lon1) * Math.cos((lat1 + lat2) / 2);
        double deltaY = lat2 - lat1;

        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY) * RADIO_TERRESTRE;
        return (int) Math.round(distancia);
    }
}