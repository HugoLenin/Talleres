package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas.
 */
public class ClienteCorporativo extends Cliente {
    public static final String CORPORATIVO = "Corporativo";
    public static final int PEQUENA = 1;
    public static final int MEDIANA = 2;
    public static final int GRANDE = 3;

    private String nombreEmpresa;
    private int tamanoEmpresa;

    /**
     * Construye un nuevo ClienteCorporativo e inicializa sus atributos usando los parámetros.
     * 
     * @param nombreEmpresa Nombre de la empresa.
     * @param tamano        Tamaño de la empresa (PEQUENA, MEDIANA o GRANDE).
     */
    public ClienteCorporativo(String nombreEmpresa, int tamano) {
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamano;
    }

    /**
     * Retorna el nombre de la empresa.
     * 
     * @return Nombre de la empresa.
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Retorna el tamaño de la empresa: PEQUENA, MEDIANA o GRANDE.
     * 
     * @return Tamaño de la empresa.
     */
    public int getTamanoEmpresa() {
        return tamanoEmpresa;
    }

    /**
     * Retorna el identificador del cliente (nombre de la empresa).
     * 
     * @return Identificador único del cliente.
     */

    public String getIdentificador() {
        return nombreEmpresa;
    }

    /**
     * Retorna el tipo del cliente.
     * 
     * @return Tipo de cliente como cadena de texto.
     */

    public String getTipoCliente() {
        return CORPORATIVO;
    }

    /**
     * Crea un nuevo objeto de tipo ClienteCorporativo a partir de un objeto JSON.
     * 
     * El objeto JSON debe tener dos atributos: nombreEmpresa (una cadena) y tamanoEmpresa (un número).
     * 
     * @param cliente El objeto JSON que contiene la información.
     * @return El nuevo objeto inicializado con la información.
     */
    public static ClienteCorporativo cargarAerolinea(JSONObject cliente) {
        String nombreEmpresa = cliente.getString("nombreEmpresa");
        int tamano = cliente.getInt("tamanoEmpresa");
        return new ClienteCorporativo(nombreEmpresa, tamano);
    }

    /**
     * Salva este objeto de tipo ClienteCorporativo dentro de un objeto JSONObject
     * para que ese objeto se almacene en un archivo.
     * 
     * @return El objeto JSON con toda la información del cliente corporativo.
     */

    public JSONObject salvarAerolinea() {
        JSONObject jobject = new JSONObject();
        jobject.put("nombreEmpresa", this.nombreEmpresa);
        jobject.put("tamanoEmpresa", this.tamanoEmpresa);
        jobject.put("tipo", CORPORATIVO);
        return jobject;
    }
}