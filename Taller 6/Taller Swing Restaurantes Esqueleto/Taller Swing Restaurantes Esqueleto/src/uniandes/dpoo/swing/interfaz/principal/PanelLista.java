package uniandes.dpoo.swing.interfaz.principal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelLista extends JPanel implements ListSelectionListener
{
    private JList<Restaurante> listaDeRestaurantes;
    private DefaultListModel<Restaurante> dataModel;
    private VentanaPrincipal ventanaPrincipal;

    public PanelLista(VentanaPrincipal ventanaPrincipal)
    {
        this.ventanaPrincipal = ventanaPrincipal;
        setBorder(new TitledBorder("Restaurantes"));
        setLayout(new BorderLayout());

 
        dataModel = new DefaultListModel<>();
        listaDeRestaurantes = new JList<>(dataModel);
        listaDeRestaurantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaDeRestaurantes.addListSelectionListener(this);
        
      
        listaDeRestaurantes.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Restaurante) {
                    Restaurante restaurante = (Restaurante) value;
                    setText(restaurante.getNombre());
                }
                return this;
            }
        });


        JScrollPane scroll = new JScrollPane(listaDeRestaurantes);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll);
    }

    public void actualizarRestaurantes(List<Restaurante> restaurantes)
    {
        List<Restaurante> nuevosRestaurantes = new ArrayList<>();
        for(Restaurante q : restaurantes)
        {
            if(!dataModel.contains(q))
                nuevosRestaurantes.add(q);
        }
        dataModel.addAll(nuevosRestaurantes);
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        Restaurante seleccionado = listaDeRestaurantes.getSelectedValue();
        this.ventanaPrincipal.cambiarRestauranteSeleccionado(seleccionado);
    }

    public void seleccionarRestaurante(Restaurante restaurante)
    {
        listaDeRestaurantes.setSelectedValue(restaurante, true);
    }
}