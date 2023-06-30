package interfaz;
import javax.swing.*;

import plataforma.UsuarioApp;
import plataforma.Viaje;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.time.ZoneId;

public class BuscarViaje extends JPanel {
    private UsuarioApp app;
    private JTextField origenTextField;
    private JTextField destinoTextField;
    private JSpinner fechaSpinner;
    private ViajesTabla viajesTabla;
    private Vista vista;
    private Filtrado filtrado;
    private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
    
    
    public BuscarViaje(UsuarioApp app, Vista vista) {
        this.app= app;
        this.vista = vista;
        this.inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Ciudad de origen:"));
        origenTextField = new JTextField();
        inputPanel.add(origenTextField);
        inputPanel.add(new JLabel("Ciudad de destino:"));
        destinoTextField = new JTextField();
        inputPanel.add(destinoTextField);
        inputPanel.add(new JLabel("Fecha:"));

        Calendar calendar = Calendar.getInstance();
        SpinnerDateModel dateModel = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH);
        fechaSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinner, "MM/dd/yyyy");
        fechaSpinner.setEditor(dateEditor);
        inputPanel.add(fechaSpinner);
        add(inputPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarViaje();
            }
        });
        
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
        
        
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.mostrar(Vista.MENU);
            }
        });
        
        
        
        filtrado = new Filtrado(app);
        add(filtrado, BorderLayout.EAST);
        
        
        buttonPanel.add(buscarButton);
        buttonPanel.add(btnFiltrar);
        buttonPanel.add(btnAtras);
        add(buttonPanel, BorderLayout.SOUTH);

        
        
        // Viaje table
        viajesTabla = new ViajesTabla(new ArrayList<>());
        JScrollPane scrollPane = new JScrollPane(viajesTabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    
    private void buscarViaje() {
    	try {
    		String origen = origenTextField.getText();
            String destino = destinoTextField.getText();
            Date selectedDate = (Date) fechaSpinner.getValue();
            LocalDate fecha = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            ArrayList<Viaje> viajesBuscados = app.buscarViaje(origen, destino, fecha);
            this.viajes = viajesBuscados;
            if (!viajesBuscados.isEmpty())
            {
            	viajesTabla.actualizar(viajesBuscados);
            	ArrayList<String> nombres = new ArrayList<String>();
            	for (Viaje viaje : viajesBuscados)
            	{
            		String nombre = viaje.getOmnibus().getNombreEmpresa();
            		if (!nombres.contains(nombre))
            			nombres.add(nombre);	
            	};
            	filtrado.agregarEmpresas(nombres);
            }
            else
    			JOptionPane.showMessageDialog(BuscarViaje.this, "No se encontró ningún viaje. ");
    	} catch (Exception e) {
			JOptionPane.showMessageDialog(BuscarViaje.this, e);
		}
    }

    private void filtrar() {
    	viajesTabla.actualizar(filtrado.filtrar(viajes));
    }
}
