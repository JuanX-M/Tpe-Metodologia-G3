package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import plataforma.Asiento;
import plataforma.Compra;
import plataforma.Usuario;
import plataforma.UsuarioApp;
import plataforma.Viaje;

public class SeleccionarAsiento extends JPanel implements OnActionListener {
    private JButton comprarButton;
    private JComboBox<String> asientosComboBox;
    private Compra compra;
    private UsuarioApp app;
    private Viaje viajeSelec;
	private Vista vista;
	private JLabel texto;
    
    public SeleccionarAsiento(Viaje v, UsuarioApp app, Vista vista) {
        // Configuración del panel
        setLayout(new BorderLayout());
        
        this.app = app;
        this.viajeSelec = v;
        this.vista = vista;
    
        
        compra = new Compra(v, app.getUsuarioLogeado());
        
        texto = new JLabel("Precio: 0 | Cantidad de asientos: 0");
        texto.setFont(new Font("Arial", Font.PLAIN, 24));
        
        JPanel leftPanel = new JPanel(new GridLayout(0, 4));
        JPanel rightPanel = new JPanel(new GridLayout(0, 1));

        for (Asiento a : v.getAsientos()) {
            JButton asientoBtn = new JButton("" + a.getNumero());
            if (a.isReservado()) {
                asientoBtn.setBackground(Color.RED);
            } else {
                asientoBtn.addActionListener(e -> {
                    Asiento asientoSeleccionado = a;
                    if (asientoSeleccionado.isReservado() || asientoBtn.getBackground() == Color.GREEN) {
                        deshacerSeleccion(asientoBtn, asientoSeleccionado);
                    } else {
                        seleccionarAsiento(asientoBtn, asientoSeleccionado);
                    }
                });
            }
            leftPanel.add(asientoBtn);
        }

        rightPanel.add(texto);
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // Crear y configurar el botón de compra
        comprarButton = new JButton("Comprar");
        comprarButton.addActionListener(e -> {
        	if (this.compra.getCantAsientos() == 0) {
            	JOptionPane.showMessageDialog(SeleccionarAsiento.this, "Selecciona al menos un asiento para confirmar la compra ");
            	return;
        	}
        	if (!app.getUsuarioLogeado().tieneTarjeta())
        	{
                AsociarTarjeta asociarTarjetaPanel = new AsociarTarjeta(app, this.app.getUsuarioLogeado(), this);
                //vista.actualizarPanel(asociarTarjetaPanel, Vista.ASOCIAR_TARJETA);
                //vista.mostrar(Vista.ASOCIAR_TARJETA);
        	}
        	else
        	{
        		this.confirmarCompra();
        	}
        	
        });
        add(comprarButton, BorderLayout.SOUTH);
    }
    

		private void deshacerSeleccion(JButton boton, Asiento asiento) {
		    boton.setBackground(null);
		    viajeSelec.cancelarReserva(asiento.getNumero());
		    compra.removeAsiento(asiento);
		    actualizarTexto();
		}
		
		private void seleccionarAsiento(JButton boton, Asiento asiento) {
		    int numeroAsiento = asiento.getNumero();
		    boton.setBackground(Color.GREEN);
		    if (compra.getCantAsientos() >= 1) {
		        RegistroPasajero reg = new RegistroPasajero(app, vista);
		        Usuario personaAsiento = reg.getPersonaAsiento();
		        if (personaAsiento != null) {
		        	viajeSelec.reservarAsiento(numeroAsiento, personaAsiento);
		        }
		    } else {
		    	viajeSelec.reservarAsiento(numeroAsiento, app.getUsuarioLogeado());
		    }
		    compra.addAsiento(asiento);
		    actualizarTexto();
		}
    
    public void actualizarTexto()
    {
    	texto.setText("Precio: " + compra.getPrecio() + " | Cantidad de asientos: " + compra.getCantAsientos());
    }

    private void confirmarCompra()
    {
    	try {
        	this.app.confirmarCompra(this.viajeSelec.getOmnibus().getNombreEmpresa(), compra);
        	JOptionPane.showMessageDialog(SeleccionarAsiento.this, "Se ha realizado exitosamente la compra! ");
        	vista.mostrar(Vista.BUSCAR_VIAJE);
        	// se le pide la tarjeta en caso de que no la tenga asociada
        	// se procede a confirmar la compra	
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(SeleccionarAsiento.this, "Ocurrió el siguiente error en la compra: " + e2);
		}
    }
    


	@Override
	public void onAction() {
		confirmarCompra();
	}
}
