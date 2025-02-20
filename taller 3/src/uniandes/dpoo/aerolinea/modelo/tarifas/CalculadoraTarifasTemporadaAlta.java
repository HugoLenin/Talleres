package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;


public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {

    protected final int COSTO_POR_KM = 500;


    public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        return COSTO_POR_KM * calcularDistanciaVuelo(vuelo.getRuta());
    }


    protected double calcularPorcentajeDescuento(Cliente cliente) {
        return 0.0; // No hay descuentos en temporada alta
    }
}