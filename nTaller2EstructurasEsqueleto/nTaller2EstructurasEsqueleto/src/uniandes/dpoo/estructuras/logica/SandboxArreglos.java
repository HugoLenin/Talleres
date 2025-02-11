package uniandes.dpoo.estructuras.logica;
import java.util.stream.IntStream;
import java.util.HashMap;
import java.util.Random;
/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas como construir listas para evitar la manipulación de arreglos).
 */
import java.util.Arrays;

public class SandboxArreglos {
    private int[] arregloEnteros;
    private String[] arregloCadenas;

    public SandboxArreglos() {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    public int[] getCopiaEnteros() {
        return Arrays.copyOf(arregloEnteros, arregloEnteros.length);
    }

    public String[] getCopiaCadenas() {
        return Arrays.copyOf(arregloCadenas, arregloCadenas.length);
    }

    public int getCantidadEnteros() {
        return arregloEnteros.length;
    }

    public int getCantidadCadenas() {
        return arregloCadenas.length;
    }

    public void agregarEntero(int entero) {
        arregloEnteros = Arrays.copyOf(arregloEnteros, arregloEnteros.length + 1);
        arregloEnteros[arregloEnteros.length - 1] = entero;
    }

    public void agregarCadena(String cadena) {
        arregloCadenas = Arrays.copyOf(arregloCadenas, arregloCadenas.length + 1);
        arregloCadenas[arregloCadenas.length - 1] = cadena;
    }

    public void eliminarEntero(int valor) {
        arregloEnteros = Arrays.stream(arregloEnteros).filter(i -> i != valor).toArray();
    }

    public void eliminarCadena(String cadena) {
        arregloCadenas = Arrays.stream(arregloCadenas).filter(s -> !s.equals(cadena)).toArray(String[]::new);
    }

    public void insertarEntero(int entero, int posicion) {
        int newSize = arregloEnteros.length + 1;
        int[] nuevoArreglo = new int[newSize];
        posicion = Math.max(0, Math.min(posicion, arregloEnteros.length));
        System.arraycopy(arregloEnteros, 0, nuevoArreglo, 0, posicion);
        nuevoArreglo[posicion] = entero;
        System.arraycopy(arregloEnteros, posicion, nuevoArreglo, posicion + 1, arregloEnteros.length - posicion);
        arregloEnteros = nuevoArreglo;
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if (posicion >= 0 && posicion < arregloEnteros.length) {
            int[] nuevoArreglo = new int[arregloEnteros.length - 1];
            System.arraycopy(arregloEnteros, 0, nuevoArreglo, 0, posicion);
            System.arraycopy(arregloEnteros, posicion + 1, nuevoArreglo, posicion, arregloEnteros.length - posicion - 1);
            arregloEnteros = nuevoArreglo;
        }
    }

    public void reiniciarArregloEnteros(double[] valores) {
        arregloEnteros = Arrays.stream(valores).mapToInt(v -> (int) v).toArray();
    }

    public void reiniciarArregloCadenas(Object[] objetos) {
        arregloCadenas = Arrays.stream(objetos).map(Object::toString).toArray(String[]::new);
    }

    public void volverPositivos() {
        for (int i = 0; i < arregloEnteros.length; i++) {
            arregloEnteros[i] = Math.abs(arregloEnteros[i]);
        }
    }

    public void organizarEnteros() {
        Arrays.sort(arregloEnteros);
    }

    public void organizarCadenas() {
        Arrays.sort(arregloCadenas);
    }

    public int contarApariciones(int valor) {
        return (int) Arrays.stream(arregloEnteros).filter(i -> i == valor).count();
    }

    public int contarApariciones(String cadena) {
        return (int) Arrays.stream(arregloCadenas).filter(s -> s.equalsIgnoreCase(cadena)).count();
    }

    public int[] buscarEntero(int valor) {
        return IntStream.range(0, arregloEnteros.length).filter(i -> arregloEnteros[i] == valor).toArray();
    }

    public int[] calcularRangoEnteros() {
        if (arregloEnteros.length == 0) return new int[]{};
        int min = Arrays.stream(arregloEnteros).min().getAsInt();
        int max = Arrays.stream(arregloEnteros).max().getAsInt();
        return new int[]{min, max};
    }

    public HashMap<Integer, Integer> calcularHistograma() {
        HashMap<Integer, Integer> histograma = new HashMap<>();
        for (int num : arregloEnteros) {
            histograma.put(num, histograma.getOrDefault(num, 0) + 1);
        }
        return histograma;
    }

    public int contarEnterosRepetidos() {
        return (int) calcularHistograma().values().stream().filter(v -> v > 1).count();
    }

    public boolean compararArregloEnteros(int[] otroArreglo) {
        return Arrays.equals(arregloEnteros, otroArreglo);
    }

    public boolean mismosEnteros(int[] otroArreglo) {
        int[] copia1 = Arrays.copyOf(arregloEnteros, arregloEnteros.length);
        int[] copia2 = Arrays.copyOf(otroArreglo, otroArreglo.length);
        Arrays.sort(copia1);
        Arrays.sort(copia2);
        return Arrays.equals(copia1, copia2);
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        Random rand = new Random();
        arregloEnteros = rand.ints(cantidad, minimo, maximo + 1).toArray();
    }
}

