import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void solicitarDatosYRegistrar(UsuarioApp app) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre = scan.nextLine();
        System.out.println("Ingrese su apellido: ");
        String apellido = scan.nextLine();
        System.out.println("Ingrese su DNI: ");
        int dni = scan.nextInt();
        if (!app.verificarExisteDNI(dni)) {
            System.out.println("Ingrese su mail: ");
            String mail = scan.next();
            System.out.println("Ingrese la contraseña, debe contener al menos: 8 caracteres, una minuscula, una mayuscula y un numero:  ");
            String clave = scan.next();
            while (!app.verificarClave(clave)) {
                System.out.println("Clave mal ingresada, ingrese otra: ");
                clave = scan.next();
            }
            Usuario u = new Usuario(nombre, apellido, dni, mail, clave);
            app.registrarUsuario(u);
        } else {
            System.out.println("El DNI ingresado ya existe en el sistema: ");
        }
    }
    
    
    // TEST CASE de la funcionalidad de buscarViaje
    public static void buscarViaje(UsuarioApp app)
    {
    	Scanner scan = new Scanner(System.in);
        System.out.println("ingrese la ciudad de origen: ");
        String origen = scan.nextLine();
        System.out.println("ingrese la ciudad de destino: ");
        String destino = scan.nextLine();
        System.out.println("ingrese la fecha: ");
        
        System.out.println("ingrese el mes: ");
        int mes = Integer.parseInt(scan.nextLine());
        System.out.println("ingrese el dia: ");
        int dia = Integer.parseInt(scan.nextLine());
    	
    	
        // TODO: Checkeamos que tenga coherencia la fecha dada
        // dia, mes, LocalDate.now().getYear()
        LocalDate fecha = LocalDate.of(LocalDate.now().getYear(), mes, dia); // "Tandil", "MDQ", LocalDate.of(2023, 6, 1)
        ArrayList<Viaje> viajesBuscados = app.buscarViaje(origen, destino, fecha);
        
        if (viajesBuscados.isEmpty())
        	System.out.println("No se encontraron viajes para la entrada dada. ");
        
        for (Viaje v : viajesBuscados)
        {
        	System.out.println("Viajes disponibles para la entrada dada: ");
        	System.out.println(v);
        }   
    }
    
    
	public static void main (String [ ] args) {
		ArrayList<Omnibus> omnibus = new ArrayList<Omnibus>();
    	
    	Omnibus omLangeyu = new Omnibus("Plusmar");
        omnibus.add(omLangeyu);
        Viaje v1 = new Viaje("Tandil", "MDQ", LocalDateTime.of(2023, 6, 1, 21, 0), 2000, omLangeyu);
        omLangeyu.agregarItinerario(v1);
        Viaje v2 = new Viaje("Buenos Aires", "Mar del Plata", LocalDateTime.of(2023, 6, 2, 10, 30), 1800, omLangeyu);
        omLangeyu.agregarItinerario(v2);
        Viaje v3 = new Viaje("La Plata", "Bahía Blanca", LocalDateTime.of(2023, 6, 3, 16, 15), 2500, omLangeyu);
        omLangeyu.agregarItinerario(v3);
        Viaje v4 = new Viaje("Mar del Plata", "Tandil", LocalDateTime.of(2023, 6, 4, 8, 45), 1900, omLangeyu);
        omLangeyu.agregarItinerario(v4);
    	
        Omnibus omEmpresa = new Omnibus("Empresa");
        omnibus.add(omEmpresa);
        Viaje v5 = new Viaje("Córdoba", "Mendoza", LocalDateTime.of(2023, 6, 1, 9, 0), 3500, omEmpresa);
        omEmpresa.agregarItinerario(v5);
        Viaje v6 = new Viaje("Buenos Aires", "Rosario", LocalDateTime.of(2023, 6, 2, 12, 30), 1200, omEmpresa);
        omEmpresa.agregarItinerario(v6);
        Viaje v7 = new Viaje("Santa Fe", "Córdoba", LocalDateTime.of(2023, 6, 3, 15, 45), 1800, omEmpresa);
        omEmpresa.agregarItinerario(v7);
        Viaje v8 = new Viaje("Mendoza", "Buenos Aires", LocalDateTime.of(2023, 6, 4, 18, 20), 3200, omEmpresa);
        omEmpresa.agregarItinerario(v8);

        Omnibus omFlecha = new Omnibus("Flecha");
        omnibus.add(omFlecha);
        Viaje v9 = new Viaje("Rosario", "Santa Fe", LocalDateTime.of(2023, 6, 1, 14, 0), 800, omFlecha);
        omFlecha.agregarItinerario(v9);
        Viaje v10 = new Viaje("Mar del Plata", "Buenos Aires", LocalDateTime.of(2023, 6, 2, 17, 45), 1500, omFlecha);
        omFlecha.agregarItinerario(v10);
        Viaje v11 = new Viaje("Córdoba", "Rosario", LocalDateTime.of(2023, 6, 3, 9, 30), 1000, omFlecha);
        omFlecha.agregarItinerario(v11);
        Viaje v12 = new Viaje("Santa Fe", "Mar del Plata", LocalDateTime.of(2023, 6, 4, 12, 15), 2000, omFlecha);
        omFlecha.agregarItinerario(v12);
    	
    	EmpresaTransporte langeyu = new EmpresaTransporte("Langeyú", omnibus);
    	
    	ArrayList<EmpresaTransporte> empresas = new ArrayList<EmpresaTransporte>();
    	
    	empresas.add(langeyu);
    	
    	
    	Plataforma p = new Plataforma(empresas);
    	
		
		
		
		UsuarioApp app = new UsuarioApp(p);
	
		
		// TODO: REGEX EN LA CLASE DE USUARIO NO EN LA ENTRADA DE DATOS!!
		
        Usuario u = new Usuario("Juan", "Cruz", 44693208, "hola@gmail.com", "Pasaporte55");
        app.registrarUsuario(u);
		//solicitarDatosYRegistrar(app);
		buscarViaje(app);
	}
}
