package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;

@SuppressWarnings("serial")
public class VentanaAgregarRestaurante extends JFrame
{
    private PanelEditarRestaurante panelDetalles;
    private PanelBotonesAgregar panelBotones;
    private PanelMapaAgregar panelMapa;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaAgregarRestaurante(VentanaPrincipal principal)
    {
        this.ventanaPrincipal = principal;
        setLayout(new BorderLayout());
        setTitle("Agregar Nuevo Restaurante");

        // Panel del mapa
        panelMapa = new PanelMapaAgregar();
        add(panelMapa, BorderLayout.CENTER);

        // Panel sur con detalles y botones
        JPanel panelSur = new JPanel(new BorderLayout());
        panelDetalles = new PanelEditarRestaurante();
        panelBotones = new PanelBotonesAgregar(this);
        
        panelSur.add(panelDetalles, BorderLayout.CENTER);
        panelSur.add(panelBotones, BorderLayout.SOUTH);
        add(panelSur, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    public void agregarRestaurante()
    {
        String nombre = panelDetalles.getNombre();
        int calificacion = panelDetalles.getCalificacion();
        boolean visitado = panelDetalles.getVisitado();
        int[] coordenadas = panelMapa.getCoordenadas();
        
        ventanaPrincipal.agregarRestaurante(
            nombre, 
            calificacion, 
            coordenadas[0], 
            coordenadas[1], 
            visitado
        );
        dispose();
    }

    public void cerrarVentana()
    {
        dispose();
    }
}