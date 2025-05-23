package uniandes.dpoo.aerolinea.persistencia;

/**
 * Esta clase cumple el rol de una fábrica de componentes que se encargan de manejar la persistencia de una aerolínea y de sus tiquetes.
 */
public class CentralPersistencia {
    /**
     * La cadena utilizada para identificar a los archivos en formato JSON.
     */
    public static final String JSON = "JSON";

    /**
     * La cadena utilizada para identificar a los archivos en texto plano.
     */
    public static final String PLAIN = "PlainText";

    /**
     * Retorna una nueva instancia de una clase capaz de cargar y salvar los datos de una aerolínea.
     * 
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de la aerolínea.
     * @return El objeto que debería usarse para cargar y salvar la información.
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido.
     */
    public static IPersistenciaAerolinea getPersistenciaAerolinea(String tipoArchivo) throws TipoInvalidoException {
        if (JSON.equals(tipoArchivo)) {
            return new PersistenciaAerolineaJson();
        } else if (PLAIN.equals(tipoArchivo)) {
            return new PersistenciaAerolineaPlaintext();
        } else {
            throw new TipoInvalidoException("Tipo de archivo inválido: " + tipoArchivo);
        }
    }

    /**
     * Retorna una nueva instancia de una clase capaz de cargar y salvar los datos de los tiquetes de una aerolínea.
     * 
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de los tiquetes.
     * @return El objeto que debería usarse para cargar y salvar la información.
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido.
     */
    public static IPersistenciaTiquetes getPersistenciaTiquetes(String tipoArchivo) throws TipoInvalidoException {
        if (JSON.equals(tipoArchivo)) {
            return new PersistenciaTiquetesJson();
        } else {
            throw new TipoInvalidoException("Tipo de archivo inválido: " + tipoArchivo);
        }
    }
}
