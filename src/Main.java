import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;

import interfaz.Vista;
import plataforma.Administrador;
import plataforma.EmpresaTransporte;
import plataforma.Omnibus;
import plataforma.Plataforma;
import plataforma.Usuario;
import plataforma.UsuarioApp;
import plataforma.Viaje;



// TODO: refactorizar lógica del menú en una clase

public class Main {

	public static void main (String [ ] args) {
		Plataforma p = new Plataforma();
		UsuarioApp app = new UsuarioApp(p);

		
		// Cargamos un admin para testear
		try {
			Usuario admin = new Administrador("Juan","Pino", 1,"jorge@gmail.com","Pasaporte55");
			Usuario test = new Usuario("John","Doe", 99,"lol","VIHPositivo9");
			app.registrarUsuario(admin);
			app.registrarUsuario(test);
		} catch (Exception e) {
			System.out.println(e);
		}
		
    	JFrame frame = new JFrame("Plataforma 9-14");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JPanel mainPanel = new JPanel();
        
        Vista vista = new Vista(app);
        
        mainPanel.add(vista);
        
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);		
	}
}
