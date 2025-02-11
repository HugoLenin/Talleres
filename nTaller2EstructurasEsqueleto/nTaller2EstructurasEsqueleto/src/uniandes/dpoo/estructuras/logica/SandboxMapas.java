package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
import java.util.*;

public class SandboxMapas {
    private Map<String, String> mapaCadenas;

    public SandboxMapas() {
        mapaCadenas = new HashMap<>();
    }

    public List<String> getValoresComoLista() {
        List<String> valores = new ArrayList<>(mapaCadenas.values());
        Collections.sort(valores);
        return valores;
    }

    public List<String> getLlavesComoListaInvertida() {
        List<String> llaves = new ArrayList<>(mapaCadenas.keySet());
        llaves.sort(Collections.reverseOrder());
        return llaves;
    }

    public String getPrimera() {
        return mapaCadenas.isEmpty() ? null : Collections.min(mapaCadenas.keySet());
    }

    public String getUltima() {
        return mapaCadenas.isEmpty() ? null : Collections.max(mapaCadenas.values());
    }


    public Collection<String> getLlaves() {
        return mapaCadenas.keySet().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }
    public int getCantidadCadenasDiferentes() {
        return new HashSet<>(mapaCadenas.values()).size();
    }

    public void agregarCadena(String cadena) {
        mapaCadenas.put(new StringBuilder(cadena).reverse().toString(), cadena);
    }

    public void eliminarCadenaConLLave(String llave) {
        mapaCadenas.remove(llave);
    }

    public void eliminarCadenaConValor(String valor) {
        mapaCadenas.values().removeIf(v -> v.equals(valor));
    }

    public void reiniciarMapaCadenas(List<Object> objetos) {
        mapaCadenas.clear();
        for (Object obj : objetos) {
            String valor = obj.toString();
            mapaCadenas.put(new StringBuilder(valor).reverse().toString(), valor);
        }
    }

    public void volverMayusculas() {
        Map<String, String> nuevoMapa = new HashMap<>();
        for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
            nuevoMapa.put(entry.getKey().toUpperCase(), entry.getValue().toUpperCase());
        }
        mapaCadenas = nuevoMapa;
    }
    public boolean compararValores(String[] otroArreglo) {
        Set<String> valoresSet = new HashSet<>(mapaCadenas.values());
        for (String valor : otroArreglo) {
            if (!valoresSet.contains(valor)) {
                return false;
            }
        }
        return true;
    }
}
