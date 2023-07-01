package interfaz;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import plataforma.UsuarioApp;

public class Menu extends JPanel {
    private JButton btnBuscarViaje;
    private JButton btnSalir;
    private Vista vista;
    private UsuarioApp app;
    
    
    
    public Menu(Vista vista, UsuarioApp app) {
    	this.vista = vista;
    	this.app = app;
    	
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblPlataforma = new JLabel("Plataforma 9/14");
        lblPlataforma.setFont(new Font("Tahoma", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblPlataforma, gbc);

        btnBuscarViaje = new JButton("Buscar viaje");
        btnBuscarViaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.mostrar(Vista.BUSCAR_VIAJE);;
            }
        });
        
        
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(btnBuscarViaje, gbc);


        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	app.deslogear();
                vista.mostrar(Vista.INICIO_SESION);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnSalir, gbc);
    }
}
