package interfaz;
import javax.swing.*;

import plataforma.UsuarioApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesion extends JPanel {

    private JTextField campoUsuario;
    private JPasswordField campoClave;
    private UsuarioApp app;
    private Vista vista;

    public InicioSesion(UsuarioApp app, Vista vista) {
        this.app = app;
        this.vista = vista;

        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Plataforma 9-14");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titulo, BorderLayout.NORTH);
        add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(etiquetaUsuario, gbc);

        campoUsuario = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(campoUsuario, gbc);

        JLabel etiquetaClave = new JLabel("Clave:");
        etiquetaClave.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(etiquetaClave, gbc);

        campoClave = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(campoClave, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botonRegistrar = new JButton("Registrarse");
        botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.mostrarRegistro();
            }
        });
        buttonPanel.add(botonRegistrar);

        JButton botonIniciarSesion = new JButton("Iniciar sesión");
        botonIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        buttonPanel.add(botonIniciarSesion);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Realiza la acción de iniciar sesión.
     */
    private void iniciarSesion() {
        int dni = 1;
        String clave = String.valueOf(campoClave.getPassword());
        app.iniciarSesion(dni, clave);
        if (app.estaLogeado()) {
            mostrarMensaje("Inicio de sesión exitoso");
            vista.mostrarMenu();
            //cardLayout.show(cardPanel, "menu");
        } else {
            mostrarMensaje("Inicio de sesión fallido");
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(InicioSesion.this, mensaje);
    }
}
