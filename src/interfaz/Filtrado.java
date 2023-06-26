package interfaz;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import plataforma.FiltroAND;
import plataforma.FiltroCosto;
import plataforma.FiltroEmpresa;
import plataforma.FiltroMargenHorario;
import plataforma.UsuarioApp;
import plataforma.Viaje;

public class Filtrado extends JPanel {
    private JComboBox<String> empresasComboBox;
    private SliderRangoHorario tiempoSlider;
    private SliderRangoPrecio preciosSlider;
    private UsuarioApp app;

    public Filtrado(UsuarioApp app) {
    	this.app = app;
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel empresasLabel = new JLabel("Empresas:");
        empresasComboBox = new JComboBox<String>();
        empresasComboBox.addItem("Elegir una opción");
        empresasComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> source = (JComboBox<String>) e.getSource();
                String selectedOption = (String) source.getSelectedItem();
                
                // TODO: cambiar este patch horrible
                if (selectedOption != null && selectedOption.equals("Elegir una opción")) {
                    source.setSelectedIndex(-1); // Unselect the option
                }
            }
        });
        
        
        JLabel tiempoLabel = new JLabel("Rango de Tiempo:");
        tiempoSlider = new SliderRangoHorario();

        JLabel preciosLabel = new JLabel("Rango de Precios:");
        preciosSlider = new SliderRangoPrecio(0, 10000, 100);

        add(empresasLabel);
        add(empresasComboBox);
        add(tiempoLabel);
        add(tiempoSlider);
        add(preciosLabel);
        add(preciosSlider);
    }
    
    public void agregarEmpresas(ArrayList<String> empresas)
    {
    	empresasComboBox.removeAllItems();
    	empresasComboBox.addItem("Elegir una opción");
    	for (String nombre : empresas)
    		empresasComboBox.addItem(nombre);
    }
    
    public ArrayList<Viaje> filtrar(ArrayList<Viaje> viajes)
    {
    	FiltroCosto filtroCosto = new FiltroCosto(Double.parseDouble(preciosSlider.getValorInicio()), Double.parseDouble(preciosSlider.getValorFin()));
    	LocalTime horaMin = LocalTime.parse(tiempoSlider.getValorInicio());
    	LocalTime horaMax = LocalTime.parse(tiempoSlider.getValorFin());
    	FiltroMargenHorario filtroHorario = new FiltroMargenHorario(horaMin, horaMax);
    	
    	FiltroAND conjuncion = new FiltroAND(filtroCosto, filtroHorario);
    	
    	if (empresasComboBox.getSelectedIndex() != -1)
    	{
    		String nombreEmpresa = (String) empresasComboBox.getSelectedItem();
    		conjuncion = new FiltroAND(conjuncion, new FiltroEmpresa(nombreEmpresa));
    	}
    	
    	return this.app.filtrar(viajes, conjuncion);
    }
    
}
