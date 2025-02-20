package uniandes.dpoo.aerolinea.modelo;

import java.io.IOException;
import java.util.*;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaAerolinea;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaTiquetes;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Aerolinea {
    private List<Avion> aviones;
    private Map<String, Ruta> rutas;
    private List<Vuelo> vuelos;
    private Map<String, Cliente> clientes;

    public Aerolinea() {
        aviones = new LinkedList<>();
        rutas = new HashMap<>();
        vuelos = new LinkedList<>();
        clientes = new HashMap<>();
    }

    public void agregarRuta(Ruta ruta) {
        this.rutas.put(ruta.getCodigoRuta(), ruta);
    }

    public void agregarAvion(Avion avion) {
        this.aviones.add(avion);
    }

    public void agregarCliente(Cliente cliente) {
        this.clientes.put(cliente.getIdentificador(), cliente);
    }

    public boolean existeCliente(String identificadorCliente) {
        return this.clientes.containsKey(identificadorCliente);
    }

    public Cliente getCliente(String identificadorCliente) {
        return this.clientes.get(identificadorCliente);
    }

    public Collection<Avion> getAviones() {
        return aviones;
    }

    public Collection<Ruta> getRutas() {
        return rutas.values();
    }

    public Ruta getRuta(String codigoRuta) {
        return rutas.get(codigoRuta);
    }

    public Collection<Vuelo> getVuelos() {
        return vuelos;
    }

    public Vuelo getVuelo(String codigoRuta, String fechaVuelo) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getRuta().getCodigoRuta().equals(codigoRuta) && vuelo.getFecha().equals(fechaVuelo)) {
                return vuelo;
            }
        }
        return null;
    }

    public Collection<Cliente> getClientes() {
        return clientes.values();
    }

    public Collection<Tiquete> getTiquetes() {
        List<Tiquete> tiquetes = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            tiquetes.addAll(vuelo.getTiquetes());
        }
        return tiquetes;
    }

    public void cargarAerolinea(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException, InformacionInconsistenteException {
        IPersistenciaAerolinea persistencia = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
        persistencia.cargarAerolinea(archivo, this);
    }

    public void salvarAerolinea(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException {
        IPersistenciaAerolinea persistencia = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
        persistencia.salvarAerolinea(archivo, this);
    }

    public void programarVuelo(String fecha, String codigoRuta, String nombreAvion) throws Exception {
        Ruta ruta = getRuta(codigoRuta);
        if (ruta == null) {
            throw new Exception("La ruta no existe.");
        }

        Avion avion = null;
        for (Avion a : aviones) {
            if (a.getNombre().equals(nombreAvion)) {
                avion = a;
                break;
            }
        }

        if (avion == null) {
            throw new Exception("El avión no existe.");
        }

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getFecha().equals(fecha) && vuelo.getAvion().equals(avion)) {
                throw new Exception("El avión ya está programado para otro vuelo en esa fecha.");
            }
        }

        vuelos.add(new Vuelo(ruta, fecha, avion));
    }

    public int venderTiquetes(String identificadorCliente, String fecha, String codigoRuta, int cantidad)
            throws VueloSobrevendidoException, Exception {
        Cliente cliente = getCliente(identificadorCliente);
        if (cliente == null) {
            throw new Exception("El cliente no existe.");
        }

        Vuelo vuelo = getVuelo(codigoRuta, fecha);
        if (vuelo == null) {
            throw new Exception("El vuelo no existe.");
        }

        return vuelo.venderTiquetes(cliente, nuevaCalculadoraTarifas(), cantidad);
    }

    public void registrarVueloRealizado(String fecha, String codigoRuta) {
        Vuelo vuelo = getVuelo(codigoRuta, fecha);
        if (vuelo != null) {
            for (Tiquete tiquete : vuelo.getTiquetes()) {
                tiquete.marcarComoUsado();
            }
        }
    }

    public String consultarSaldoPendienteCliente(String identificadorCliente) {
        Cliente cliente = getCliente(identificadorCliente);
        if (cliente == null) {
            return "Cliente no encontrado.";
        }
        int saldoPendiente = 0;
        for (Tiquete tiquete : cliente.getIdentificador()) {
            if (!tiquete.esUsado()) {
                saldoPendiente += tiquete.getTarifa();
            }
        }
        return "Saldo pendiente: " + saldoPendiente;
    }

    public void cargarTiquetes(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException, InformacionInconsistenteException {
        IPersistenciaTiquetes persistencia = CentralPersistencia.getPersistenciaTiquetes(tipoArchivo);
        persistencia.cargarTiquetes(archivo, this);
    }

    public void salvarTiquetes(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException {
        IPersistenciaTiquetes persistencia = CentralPersistencia.getPersistenciaTiquetes(tipoArchivo);
        persistencia.salvarTiquetes(archivo, this);
    }
}