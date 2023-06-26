package interfaz;
import javax.swing.*;

import plataforma.Usuario;
import plataforma.UsuarioApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Registro extends JPanel {

    private JTextField campoDNI;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoMail;
    private JPasswordField campoClave;
    private UsuarioApp app;
    private Vista vista;
    
    
    
    /**
     * Crea el panel de registro.
     * @param app La instancia de UsuarioApp para registrar al usuario.
     * @param cardLayout El CardLayout para administrar los componentes.
     * @param cardPanel El panel contenedor de los componentes.
     */
    public Registro(UsuarioApp app, Vista vista) {
        this.app = app;
        this.vista = vista;
        
        
        initialize();
    }

    /**
     * Inicializa el contenido del panel.
     */
    private void initialize() {
        setLayout(new GridLayout(7, 2));

        JLabel labelDNI = new JLabel("DNI:");
        campoDNI = new JTextField();
        JLabel labelNombre = new JLabel("Nombre:");
        campoNombre = new JTextField();
        JLabel labelApellido = new JLabel("Apellido:");
        campoApellido = new JTextField();
        JLabel labelMail = new JLabel("Mail:");
        campoMail = new JTextField();
        JLabel labelClave = new JLabel("Contraseña:");
        campoClave = new JPasswordField();
        JButton botonRegistrar = new JButton("Registrar");

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	vista.mostrarInicio();
            }
        });
        
        
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(campoDNI.getText());
                    String nombre = campoNombre.getText();
                    String apellido = campoApellido.getText();
                    String mail = campoMail.getText();
                    String clave = String.valueOf(campoClave.getPassword());

                    Usuario u = new Usuario(nombre, apellido, dni, mail, clave);
                    app.registrarUsuario(u);

                    int op = JOptionPane.showConfirmDialog(Registro.this, "¿Desea asociar una tarjeta?", "Asociar Tarjeta", JOptionPane.YES_NO_OPTION);
                    if (op == JOptionPane.YES_OPTION) {
                        AsociarTarjeta asociarTarjetaPanel = new AsociarTarjeta(app, u);
                        //cardPanel.add(asociarTarjetaPanel, "asociarTarjeta");
                        //cardLayout.show(cardPanel, "asociarTarjeta");
                    }
                    JOptionPane.showMessageDialog(Registro.this, "Registro exitoso");
                    botonVolver.doClick();
                    clearFields();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Registro.this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        
        add(labelDNI);
        add(campoDNI);
        add(labelNombre);
        add(campoNombre);
        add(labelApellido);
        add(campoApellido);
        add(labelMail);
        add(campoMail);
        add(labelClave);
        add(campoClave);
        add(new JLabel()); // Espacio vacío para alinear el botón
        add(botonRegistrar);
        add(botonVolver);
    }

    private void clearFields() {
        campoDNI.setText("");
        campoNombre.setText("");
        campoApellido.setText("");
        campoMail.setText("");
        campoClave.setText("");
    }
    


}
