package interfaz;
import javax.swing.*;

import plataforma.UsuarioApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista extends JPanel {

	public static final String INICIO_SESION = "INICIO_SESION";
	public static final String REGISTRO = "REGISTRO";
	public static final String MENU = "MENU";
	public static final String BUSCAR_VIAJE = "BUSCAR_VIAJE";
	public static final String ASOCIAR_TARJETA = "ASOCIAR_TARJETA";
	
    private JPanel inicioPanel;
    private CardLayout cardLayout;
    private UsuarioApp app;

    /**
     * Crea el panel de inicio de sesión.
     */
    public Vista(UsuarioApp app) {
        this.app = app;
        this.cardLayout = new CardLayout();
        setLayout(new BorderLayout());
        inicioPanel = new JPanel(this.cardLayout);
        
       
        Registro registroPanel = new Registro(app, this);
        InicioSesion inicioSesion = new InicioSesion(app, this);
        Menu menuPanel = new Menu(this);
        BuscarViaje buscarViaje = new BuscarViaje(app, this);
        
        
        inicioPanel.add(inicioSesion, INICIO_SESION);
        inicioPanel.add(registroPanel, REGISTRO);
        inicioPanel.add(menuPanel, MENU);
        inicioPanel.add(buscarViaje, BUSCAR_VIAJE);
        
        add(inicioPanel, BorderLayout.CENTER);
    }


    public void mostrarMenu() {
        cardLayout.show(inicioPanel, MENU);
    }
    

    public void mostrarBuscarViaje() {
        cardLayout.show(inicioPanel, BUSCAR_VIAJE);
    }
    
    public void mostrarInicio() {
        cardLayout.show(inicioPanel, INICIO_SESION);
    }

    /**
     * Muestra el formulario de registro.
     */
    public void mostrarRegistro() {
        cardLayout.show(inicioPanel, REGISTRO);
    }

}

