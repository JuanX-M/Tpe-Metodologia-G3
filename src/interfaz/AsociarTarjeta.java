package interfaz;

import javax.swing.*;

import plataforma.TarjetaDeCredito;
import plataforma.Usuario;
import plataforma.UsuarioApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AsociarTarjeta extends JFrame {

    private JTextField numeroTarjetaField;
    private JTextField titularField;
    private JTextField bancoField;
    private JTextField marcaField;
    private JTextField fechaField;
    private JTextField codigoField;
    private UsuarioApp app;
    private Usuario usuario;
    private OnActionListener listener;
    
    
    public AsociarTarjeta(UsuarioApp app, Usuario usuario, OnActionListener listener) {
        this.app = app;
        this.listener = listener;
        this.usuario = usuario;
        inicializar();
    }

    /**
     * Inicializa el contenido del panel.
     */
    private void inicializar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Asociar Tarjeta");
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(7, 2));

        JLabel etiquetaNumeroTarjeta = new JLabel("Número de tarjeta:");
        numeroTarjetaField = new JTextField();
        JLabel etiquetaTitular = new JLabel("Titular de la tarjeta:");
        titularField = new JTextField();
        JLabel etiquetaBanco = new JLabel("Banco:");
        bancoField = new JTextField();
        JLabel etiquetaMarca = new JLabel("Marca:");
        marcaField = new JTextField();
        JLabel etiquetaFecha = new JLabel("Fecha de vencimiento (dd/MM/yyyy):");
        fechaField = new JTextField();
        JLabel etiquetaCodigo = new JLabel("Código de seguridad:");
        codigoField = new JTextField();

        formulario.add(etiquetaNumeroTarjeta);
        formulario.add(numeroTarjetaField);
        formulario.add(etiquetaTitular);
        formulario.add(titularField);
        formulario.add(etiquetaBanco);
        formulario.add(bancoField);
        formulario.add(etiquetaMarca);
        formulario.add(marcaField);
        formulario.add(etiquetaFecha);
        formulario.add(fechaField);
        formulario.add(etiquetaCodigo);
        formulario.add(codigoField);

        JButton botonAsociar = new JButton("Asociar tarjeta");
        botonAsociar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    long numero = Long.parseLong(numeroTarjetaField.getText());
                    String titular = titularField.getText();
                    String banco = bancoField.getText();
                    String marca = marcaField.getText();
                    String fechaTexto = fechaField.getText();
                    int codigo = Integer.parseInt(codigoField.getText());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fecha = LocalDate.parse(fechaTexto, formatter);

                    TarjetaDeCredito tarjeta = new TarjetaDeCredito(titular, numero, fecha, codigo, banco, marca);
                    app.asociarTarjeta(usuario, tarjeta);

                    JOptionPane.showMessageDialog(AsociarTarjeta.this, "La tarjeta fue asociada correctamente");
                    limpiarCampos();
                    listener.onAction();
                    
                    // TODO: Ocultarla.
                    dispose();

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(AsociarTarjeta.this, "Ocurrió el siguiente error al asociar su tarjeta" + e2);
                }
            }
        });

        formulario.add(botonAsociar);

        add(formulario, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Limpia los campos de entrada.
     */
    private void limpiarCampos() {
        numeroTarjetaField.setText("");
        titularField.setText("");
        bancoField.setText("");
        marcaField.setText("");
        fechaField.setText("");
        codigoField.setText("");
    }

}
