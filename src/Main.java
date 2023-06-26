import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;

import interfaz.Vista;
import plataforma.EmpresaTransporte;
import plataforma.Omnibus;
import plataforma.Plataforma;
import plataforma.Usuario;
import plataforma.UsuarioApp;
import plataforma.Viaje;



// TODO: refactorizar lógica del menú en una clase

public class Main {

	public static void main (String [ ] args) {
		
		// Cargamos la plataforma
		
		EmpresaTransporte langueyu = new EmpresaTransporte("Langueyu");
		langueyu.addNewOmnibus();
		Omnibus omLangeyu = langueyu.buscarOmnibus(1);
		
		Viaje v1 = new Viaje("Tandil", "MDQ", LocalDateTime.of(2023, 6, 1, 21, 0), 2000, omLangeyu, LocalTime.of(16, 0), LocalTime.of(18, 0));
        v1.addRecorridoIntermedio("Necochea");
        v1.addRecorridoIntermedio("San Manuel");
        omLangeyu.agregarItinerario(v1);
        Viaje v2 = new Viaje("Buenos Aires", "Mar del Plata", LocalDateTime.of(2023, 6, 2, 10, 30), 1800, omLangeyu, null, null);
        v2.addRecorridoIntermedio("Tandil");
        v2.addRecorridoIntermedio("Rauch");
        omLangeyu.agregarItinerario(v2);
        Viaje v3 = new Viaje("La Plata", "Bahía Blanca", LocalDateTime.of(2023, 6, 3, 16, 15), 2500, omLangeyu, null, null);
        v3.addRecorridoIntermedio("Olavarria");
        v3.addRecorridoIntermedio("Azul");
        omLangeyu.agregarItinerario(v3);
        Viaje v4 = new Viaje("Mar del Plata", "Tandil", LocalDateTime.of(2023, 6, 4, 8, 45), 1900, omLangeyu, null, null);
        v4.addRecorridoIntermedio("Napaleofú");
        v4.addRecorridoIntermedio("Balcarce");
        omLangeyu.agregarItinerario(v4);
    	
		EmpresaTransporte balin = new EmpresaTransporte("Balin");
		balin.addNewOmnibus();
		Omnibus omBalin = balin.buscarOmnibus(1);
		
        Viaje v5 = new Viaje("Córdoba", "Mendoza", LocalDateTime.of(2023, 6, 1, 9, 0), 3500, omBalin, null, null);
        v5.addRecorridoIntermedio("Ciudad de Rio Tercero");
        v5.addRecorridoIntermedio("San Luis");
        omBalin.agregarItinerario(v5);
        Viaje v6 = new Viaje("Buenos +Aires", "Rosario", LocalDateTime.of(2023, 6, 2, 12, 30), 1200, omBalin, null, null);
        v6.addRecorridoIntermedio("Zarate");
        v6.addRecorridoIntermedio("Arroyo Seco");
        omBalin.agregarItinerario(v6);
        Viaje v7 = new Viaje("Santa Fe", "Córdoba", LocalDateTime.of(2023, 6, 3, 15, 45), 1800, omBalin, null, null);
        v7.addRecorridoIntermedio("San Francisco");
        v7.addRecorridoIntermedio("Arroyito");
        omBalin.agregarItinerario(v7);
        Viaje v8 = new Viaje("Mendoza", "Buenos Aires", LocalDateTime.of(2023, 6, 4, 18, 20), 3200, omBalin, null, null);
        v8.addRecorridoIntermedio("San Luis");
        omBalin.agregarItinerario(v8);

		EmpresaTransporte flecha = new EmpresaTransporte("Flecha");
		flecha.addNewOmnibus();
		Omnibus omFlecha = flecha.buscarOmnibus(1);

		
        Viaje v9 = new Viaje("Rosario", "Santa Fe", LocalDateTime.of(2023, 6, 1, 14, 0), 800, omFlecha, null, null);
        omFlecha.agregarItinerario(v9);
        Viaje v10 = new Viaje("Mar del Plata", "Buenos Aires", LocalDateTime.of(2023, 6, 2, 17, 45), 1500, omFlecha, null, null);
        omFlecha.agregarItinerario(v10);
        Viaje v11 = new Viaje("Córdoba", "Rosario", LocalDateTime.of(2023, 6, 3, 9, 30), 1000, omFlecha, null, null);
        v11.addRecorridoIntermedio("Villa Maria");
        omFlecha.agregarItinerario(v11);
        Viaje v12 = new Viaje("Santa Fe", "Mar del Plata", LocalDateTime.of(2023, 6, 4, 12, 15), 2000, omFlecha, null, null);
        v12.addRecorridoIntermedio("Rosario");
        v12.addRecorridoIntermedio("Buenos Aires");
        omFlecha.agregarItinerario(v12);
        Viaje v13 = new Viaje("Tandil", "MDQ", LocalDateTime.of(2023, 6, 1, 21, 0), 1800, omFlecha, LocalTime.of(16, 0), LocalTime.of(18, 0));
        v13.addRecorridoIntermedio("Necochea");
        omFlecha.agregarItinerario(v13);
    	//EmpresaTransporte langeyu = new EmpresaTransporte("Langeyú", omnibus);
    	
    	ArrayList<EmpresaTransporte> empresas = new ArrayList<EmpresaTransporte>();
    	
    	empresas.add(langueyu);
    	empresas.add(balin);
    	empresas.add(flecha);	
    	
    	Plataforma p = new Plataforma(empresas);
    	    	
    	
    	
		// Iniciamos la sesión del usuario que va a utilizar la aplicación
		UsuarioApp app = new UsuarioApp(p);

		
		// Cargamos un usuario para testear
		try {
			Usuario test = new Usuario("Juan","Pino",1,"jorge@gmail.com","Pasaporte55");
			app.registrarUsuario(test);	
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
    	JFrame frame = new JFrame("Plataforma 9-14");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JPanel mainPanel = new JPanel();
        
        Vista vista = new Vista(app);
        //cardLayout.add(loginPanel, "login");
        
        mainPanel.add(vista);
        
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
	}
}
