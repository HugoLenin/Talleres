package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta {
    private String codigoRuta;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private String horaSalida;
    private String horaLlegada;

    /**
     * Crea una nueva ruta con los datos dados.
     * 
     * @param origen      El aeropuerto de origen.
     * @param destino     El aeropuerto de destino.
     * @param horaSalida  La hora esperada de salida en formato "HHMM".
     * @param horaLlegada La hora esperada de llegada en formato "HHMM".
     * @param codigoRuta  El código de la ruta.
     */
    public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) {
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.codigoRuta = codigoRuta;
    }

    /**
     * Retorna el código de la ruta.
     * 
     * @return Código de la ruta.
     */
    public String getCodigoRuta() {
        return codigoRuta;
    }

    /**
     * Retorna el aeropuerto de origen.
     * 
     * @return Aeropuerto de origen.
     */
    public Aeropuerto getOrigen() {
        return origen;
    }

    /**
     * Retorna el aeropuerto de destino.
     * 
     * @return Aeropuerto de destino.
     */
    public Aeropuerto getDestino() {
        return destino;
    }

    /**
     * Retorna la hora de salida en formato "HHMM".
     * 
     * @return Hora de salida.
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * Retorna la hora de llegada en formato "HHMM".
     * 
     * @return Hora de llegada.
     */
    public String getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * Calcula la duración esperada del vuelo en minutos.
     * 
     * @return Duración en minutos.
     */
    public int getDuracion() {
        int horasSalida = getHoras(horaSalida);
        int minutosSalida = getMinutos(horaSalida);
        int horasLlegada = getHoras(horaLlegada);
        int minutosLlegada = getMinutos(horaLlegada);

        int minutosSalidaTotales = horasSalida * 60 + minutosSalida;
        int minutosLlegadaTotales = horasLlegada * 60 + minutosLlegada;

        if (minutosLlegadaTotales < minutosSalidaTotales) {
            minutosLlegadaTotales += 24 * 60; // Ajuste para vuelos que cruzan medianoche
        }

        return minutosLlegadaTotales - minutosSalidaTotales;
    }

    public static int getMinutos(String horaCompleta) {
        return Integer.parseInt(horaCompleta) % 100;
    }


    public static int getHoras(String horaCompleta) {
        return Integer.parseInt(horaCompleta) / 100;
    }
}