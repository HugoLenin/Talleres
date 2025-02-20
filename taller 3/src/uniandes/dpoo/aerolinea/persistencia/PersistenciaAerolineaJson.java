package uniandes.dpoo.aerolinea.persistencia;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {


    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException {
        try (FileReader reader = new FileReader(archivo)) {
            JSONObject json = new JSONObject(new JSONTokener(reader));
            aerolinea.cargarDesdeJSON(json);
        } catch (Exception e) {
            throw new InformacionInconsistenteException("Error al cargar la aerolínea desde JSON: " + e.getMessage());
        }
    }


    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        try (FileWriter writer = new FileWriter(archivo)) {
            JSONObject json = aerolinea.salvarEnJSON();
            writer.write(json.toString(4));
        }
    }
}