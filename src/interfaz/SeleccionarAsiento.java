package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import plataforma.Asiento;
import plataforma.Compra;
import plataforma.Usuario;
import plataforma.UsuarioApp;
import plataforma.Viaje;

public class SeleccionarAsiento extends JPanel {
    private JButton comprarButton;
    private JComboBox<String> asientosComboBox;
    private Compra compra;
    private UsuarioApp app;
    private Viaje viajeSelec;
	private Vista vista;

    
    
    public SeleccionarAsiento(Viaje v, UsuarioApp app, Vista vista) {
        // Configuración del panel
        setLayout(new BorderLayout());
        
        this.app = app;
        this.vista = vista;
    
        
        compra = new Compra(v, app.getUsuarioLogeado());
        
        
        JPanel leftPanel = new JPanel(new GridLayout(0, 3));
        JPanel rightPanel = new JPanel(new GridLayout(0, 5));

        this.viajeSelec = v;

        for (Asiento a : v.asientosLibres()) {
            JButton asientoBtn = new JButton("" + a.getNumero());
            if (a.isReservado())
                asientoBtn.setBackground(Color.RED);
            else
            	asientoBtn.addActionListener(e -> {
            		Asiento asientoSelec = a;
                    int nroAsiento = asientoSelec.getNumero();
            		asientoBtn.setBackground(Color.GREEN);
            		if (compra.getCantAsientos() > 1)
            		{
            	    	RegistroPasajero reg = new RegistroPasajero(app, vista);
            	    	Usuario personaAsiento = reg.getPersonaAsiento();
            	    	if (personaAsiento != null)
            	    	{
            	    		v.reservarAsiento(nroAsiento, personaAsiento);
            	    		asientoBtn.setBackground(Color.RED);
            	    	}
            	     }
            		else
            		{
            			v.reservarAsiento(nroAsiento, app.getUsuarioLogeado());
            			asientoBtn.setBackground(Color.RED);
            		}
            		compra.addAsiento(asientoSelec);
                });
            leftPanel.add(asientoBtn);
        }

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // Crear y configurar el botón de compra
        comprarButton = new JButton("Comprar");
        comprarButton.addActionListener(e -> {
        	if (!app.getUsuarioLogeado().tieneTarjeta())
        	{
                AsociarTarjeta asociarTarjetaPanel = new AsociarTarjeta(app, this.app.getUsuarioLogeado());
                vista.actualizarPanel(asociarTarjetaPanel, Vista.ASOCIAR_TARJETA);
                vista.mostrar(Vista.ASOCIAR_TARJETA);
        	}
        	try {
            	this.app.confirmarCompra(v.getOmnibus().getNombreEmpresa(), compra);
            	JOptionPane.showMessageDialog(SeleccionarAsiento.this, "Se ha realizado exitosamente la compra! ");
            	// se le pide la tarjeta en caso de que no la tenga asociada
            	// se procede a confirmar la compra	
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(SeleccionarAsiento.this, "Ocurrió el siguiente error en la compra: " + e2);
			}
        });
        add(comprarButton, BorderLayout.SOUTH);
    }
}
