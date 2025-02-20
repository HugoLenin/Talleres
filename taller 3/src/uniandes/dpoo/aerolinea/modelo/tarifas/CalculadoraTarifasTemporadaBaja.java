package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;

/**
 * Esta clase se utiliza para calcular las tarifas en temporada baja.
 * 
 * En temporada baja, los clientes que son personas naturales tienen una tarifa base diferente a la de los clientes corporativos.
 * 
 * Adicionalmente, los clientes corporativos tienen un descuento diferente según el tamaño (los clientes naturales no tienen descuento).
 */
public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

    /**
     * El costo por kilómetro en temporada baja para clientes corporativos.
     */
    protected final int COSTO_POR_KM_CORPORATIVO = 450;

    /**
     * El costo por kilómetro en temporada baja para personas naturales.
     */
    protected final int COSTO_POR_KM_NATURAL = 400;

    /**
     * El descuento que se le puede aplicar a empresas grandes.
     */
    protected final double DESCUENTO_GRANDES = 0.15;

    /**
     * El descuento que se le puede aplicar a empresas medianas.
     */
    protected final double DESCUENTO_MEDIANAS = 0.10;

    /**
     * El descuento que se le puede aplicar a empresas pequeñas.
     */
    protected final double DESCUENTO_PEQ = 0.05;

    /**
     * Calcula el costo base como COSTO_POR_KM x distancia.
     * 
     * @param vuelo   El vuelo para el que se quiere calcular la tarifa.
     * @param cliente El cliente para el que se quiere calcular la tarifa.
     * @return El valor base de la tarifa.
     */
    @Override
    public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        if (cliente instanceof ClienteNatural) {
            return COSTO_POR_KM_NATURAL * distancia;
        } else if (cliente instanceof ClienteCorporativo) {
            return COSTO_POR_KM_CORPORATIVO * distancia;
        }
        return COSTO_POR_KM_NATURAL * distancia;
    }

    /**
     * Calcula el porcentaje de descuento que se le debería dar a un cliente corporativo.
     * 
     * @param cliente El cliente para el que se quiere conocer el descuento.
     * @return Un porcentaje de descuento, entre 0 y 1.
     */
    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente instanceof ClienteCorporativo) {
            ClienteCorporativo corporativo = (ClienteCorporativo) cliente;
            switch (corporativo.getTamanoEmpresa()) {
                case ClienteCorporativo.GRANDE:
                    return DESCUENTO_GRANDES;
                case ClienteCorporativo.MEDIANA:
                    return DESCUENTO_MEDIANAS;
                case ClienteCorporativo.PEQUENA:
                    return DESCUENTO_PEQ;
            }
        }
        return 0.0;
    }
}