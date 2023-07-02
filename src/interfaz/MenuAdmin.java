package interfaz;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import plataforma.AdminApp;
import plataforma.EmpresaTransporte;
import plataforma.Omnibus;
import plataforma.Plataforma;
import plataforma.UsuarioApp;
import plataforma.Viaje;



public class MenuAdmin extends JPanel {
    private JButton btnAgregar;
    private JButton btnMod;
    private JButton btnEliminar;
    private JButton btnSalir;
    private Vista vista;
    private UsuarioApp usuarioApp;
    private Plataforma p;
    private ViajesTabla tabla;
    private AdminApp app;
    private JSpinner fechaSpinner;
    private JSpinner horaSalidaSpinner;
    private JSpinner horaLlegadaSpinner;

    
    public MenuAdmin(Vista vista, UsuarioApp app) {
        this.vista = vista;
        this.usuarioApp = app;
        this.p = usuarioApp.getPlataforma();
        this.app = new AdminApp(this.p);

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblPlataforma = new JLabel("Plataforma 9/14");
        lblPlataforma.setFont(new Font("Tahoma", Font.BOLD, 24));
        headerPanel.add(lblPlataforma);

        JPanel centerPanel = new JPanel(new BorderLayout());
        this.tabla = new ViajesTabla(getViajes());
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAgregar = new JButton("Agregar");
        btnMod = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnSalir = new JButton("Salir");

        footerPanel.add(btnAgregar);
        footerPanel.add(btnMod);
        footerPanel.add(btnEliminar);
        footerPanel.add(btnSalir);

        add(headerPanel, BorderLayout.NORTH);
        add(footerPanel, BorderLayout.SOUTH);

        // Agregar listeners a los botones
        btnAgregar.addActionListener(e -> agregarViaje());
        btnMod.addActionListener(e -> actualizarDatos());
        btnEliminar.addActionListener(e -> eliminarViaje());
        btnSalir.addActionListener(e -> salir());
    }

    private ArrayList<Viaje> getViajes() {
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        for (EmpresaTransporte empresa : p.getEmpresas())
            for (Omnibus bus : empresa.getOmnibus())
                for (Viaje viaje : bus.getItinerario())
                    viajes.add(viaje);
        return viajes;
    }

    private void agregarViaje() {
        JFrame frame = new JFrame("Agregar Viaje");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 2, 10, 10));

        JLabel origenLabel = new JLabel("Origen:");
        JTextField origenField = new JTextField();
        JLabel destinoLabel = new JLabel("Destino:");
        JTextField destinoField = new JTextField();
        JLabel nomEmpresaLabel = new JLabel("Nombre Empresa:");
        JTextField nomEmpresaField = new JTextField();
        JLabel codOmnibusLabel = new JLabel("Código Omnibus:");
        JTextField codOmnibusField = new JTextField();
        JLabel precioLabel = new JLabel("Precio:");
        JTextField precioField = new JTextField();
        JLabel fechaLabel = new JLabel("Fecha:");
        Calendar calendar = Calendar.getInstance();
        SpinnerDateModel dateModel = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH);
        fechaSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinner, "MM/dd/yyyy");
        fechaSpinner.setEditor(dateEditor);
        JLabel horaSalidaLabel = new JLabel("Hora de Salida:");
        horaSalidaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor horaSalidaEditor = new JSpinner.DateEditor(horaSalidaSpinner, "HH:mm");
        horaSalidaSpinner.setEditor(horaSalidaEditor);
        JLabel horaLlegadaLabel = new JLabel("Hora de Llegada:");
        horaLlegadaSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor horaLlegadaEditor = new JSpinner.DateEditor(horaLlegadaSpinner, "HH:mm");
        horaLlegadaSpinner.setEditor(horaLlegadaEditor);
        JLabel intermediosLabel = new JLabel("Intermedios (separados por ;):");
        JTextArea intermediosArea = new JTextArea();

        mainPanel.add(origenLabel);
        mainPanel.add(origenField);
        mainPanel.add(destinoLabel);
        mainPanel.add(destinoField);
        mainPanel.add(nomEmpresaLabel);
        mainPanel.add(nomEmpresaField);
        mainPanel.add(codOmnibusLabel);
        mainPanel.add(codOmnibusField);
        mainPanel.add(precioLabel);
        mainPanel.add(precioField);
        mainPanel.add(fechaLabel);
        mainPanel.add(fechaSpinner);
        mainPanel.add(horaSalidaLabel);
        mainPanel.add(horaSalidaSpinner);
        mainPanel.add(horaLlegadaLabel);
        mainPanel.add(horaLlegadaSpinner);
        mainPanel.add(intermediosLabel);
        mainPanel.add(intermediosArea);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores de los campos
                    String origen = origenField.getText();
                    String destino = destinoField.getText();
                    String nomEmpresa = nomEmpresaField.getText();
                    int codOmnibus = Integer.parseInt(codOmnibusField.getText());
                    double precio = Double.parseDouble(precioField.getText());

                    Date fechaSpin = (Date) fechaSpinner.getValue();
                    LocalDate fecha = fechaSpin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    Date horaSalida = (Date) horaSalidaSpinner.getValue();
                    LocalTime salida = horaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                    
                    Date horaLlegada = (Date) horaLlegadaSpinner.getValue();
                    LocalTime llegada = horaLlegada.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
                    
                    String[] intermedios = intermediosArea.getText().split(";");
               
                    // Crear el objeto Viaje y agregarlo
                    Viaje viaje = new Viaje(origen, destino, fecha, precio, llegada, salida);
                    for (String recorrido : intermedios)
                    	viaje.addRecorridoIntermedio(recorrido);
                    
                    app.alta(viaje, codOmnibus, nomEmpresa);

                    // Actualizar la tabla
                    tabla.actualizar(getViajes());

                    // Cerrar la ventana
                    frame.dispose();
                } catch (Exception e2) {
                    System.out.println(e2);
                }

            }
        });

        mainPanel.add(agregarButton);
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void actualizarDatos() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de modificar viaje no implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarViaje() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un viaje para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar el viaje?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
            	Viaje viaje = this.tabla.getViaje(filaSeleccionada);
            	//System.out.println(viaje);
            	app.baja(viaje);
            	tabla.actualizar(getViajes());
                JOptionPane.showMessageDialog(this, "Viaje eliminado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    private void salir() {
    	usuarioApp.deslogear();
    	vista.mostrar(Vista.INICIO_SESION);
    }
}
