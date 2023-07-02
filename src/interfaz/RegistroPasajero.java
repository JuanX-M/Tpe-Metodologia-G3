package interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import plataforma.CondicionMismoDni;
import plataforma.Usuario;
import plataforma.UsuarioApp;

public class RegistroPasajero extends JFrame {
    private JPanel panel;
    private JTextField campoDNI;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private UsuarioApp app;
    private Vista vista;
    private CardLayout cardLayout;

    private Usuario personaAsiento;
    private static final String ENTRAR_DNI = "ENTRAR_DNI";
    private static final String REGISTRAR_NUEVO_PASAJERO = "REGISTRAR_NUEVO_PASAJERO";

    public RegistroPasajero(UsuarioApp app, Vista vista) {
        this.app = app;
        this.vista = vista;
        this.cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);
        initialize();
    }

    public Usuario getPersonaAsiento() {
        return personaAsiento;
    }

    private void initialize() {
        //setLayout(new GridLayout(7, 2));
        
        JPanel dniPanel = new JPanel();
        dniPanel.setLayout(new BorderLayout());
        JLabel labelDNI = new JLabel("DNI:");
        campoDNI = new JTextField();
        JButton btnDNI = new JButton("Registrar pasajero");

        btnDNI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(campoDNI.getText());
                    ArrayList<Usuario> encontrado = app.buscarUsuario(new CondicionMismoDni(dni));
                    if (encontrado.isEmpty())
                        cardLayout.show(panel, REGISTRAR_NUEVO_PASAJERO);
                    else {
                        dispose();
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(RegistroPasajero.this,
                            "Ocurrio el siguiente error: " + e2);
                }
            }
        });

        dniPanel.add(labelDNI);
        dniPanel.add(campoDNI);
        dniPanel.add(btnDNI);
        
        JPanel agregarPanel = new JPanel();
        agregarPanel.setLayout(new BorderLayout());
        JLabel labelNombre = new JLabel("Nombre:");
        campoNombre = new JTextField();
        JLabel labelApellido = new JLabel("Apellido:");
        campoApellido = new JTextField();
        JButton botonRegistrar = new JButton("Registrar pasajero");

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dni = Integer.parseInt(campoDNI.getText());
                String nombre = campoNombre.getText();
                String apellido = campoApellido.getText();

                personaAsiento = new Usuario(dni, nombre, apellido);
                try {
                    app.registrarUsuario(personaAsiento);
                    JOptionPane.showMessageDialog(RegistroPasajero.this, "Registro exitoso");
                    dispose();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(RegistroPasajero.this,
                            "Ocurrio el siguiente error en el registro: " + e1);
                }
            }
        });

        agregarPanel.add(labelNombre);
        agregarPanel.add(campoNombre);
        agregarPanel.add(labelApellido);
        agregarPanel.add(campoApellido);
        agregarPanel.add(botonRegistrar);
        
        
        panel.setLayout(new BorderLayout());
        panel.add(dniPanel, ENTRAR_DNI);
        panel.add(agregarPanel, REGISTRAR_NUEVO_PASAJERO);
        add(panel);
        
        setTitle("Registro de Pasajero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        //setSize(400, 300);
        //setLocationRelativeTo(null);
        setVisible(true);
    }

    private void clearFields() {
        campoDNI.setText("");
        campoNombre.setText("");
        campoApellido.setText("");
    }

}
