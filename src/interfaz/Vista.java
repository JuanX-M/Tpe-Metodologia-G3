package interfaz;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import plataforma.UsuarioApp;

public class Vista extends JPanel {

	public static final String INICIO_SESION = "INICIO_SESION";
	public static final String REGISTRO = "REGISTRO";
	public static final String MENU = "MENU";
	public static final String MENU_ADMIN = "MENU_ADMIN";
	public static final String BUSCAR_VIAJE = "BUSCAR_VIAJE";
	public static final String ASOCIAR_TARJETA = "ASOCIAR_TARJETA";
	public static final String SELECCIONAR_ASIENTO = "SELECCIONAR_ASIENTO";
		
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
        Menu menuPanel = new Menu(this, app);
        MenuAdmin menuAdmin = new MenuAdmin(this, app);
        BuscarViaje buscarViaje = new BuscarViaje(app, this);
        //SeleccionarAsiento seleccionar = new SeleccionarAsiento();
        
        
        
        inicioPanel.add(inicioSesion, INICIO_SESION);
        inicioPanel.add(registroPanel, REGISTRO);
        inicioPanel.add(menuPanel, MENU);
        inicioPanel.add(menuAdmin, MENU_ADMIN);
        inicioPanel.add(buscarViaje, BUSCAR_VIAJE);
        //inicioPanel.add(seleccionar, SELECCIONAR_ASIENTO);
        
        add(inicioPanel, BorderLayout.CENTER);
    }

    public void mostrar(String PANEL_NAME) {
        cardLayout.show(inicioPanel, PANEL_NAME);
    }

    
    
    public void actualizarPanel(JPanel panel, String PANEL_NAME) {
        inicioPanel.add(panel, PANEL_NAME);
    }
    
}

