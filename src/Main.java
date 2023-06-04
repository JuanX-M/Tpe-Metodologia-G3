import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	
    public static void solicitarDatosYRegistrar(UsuarioApp app) { //se piden los datos y se le manda a la app que registre al usuario
    	System.out.println("===== REGISTRARSE =====");
    	Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese su DNI: ");
        int dni = scan.nextInt();
        System.out.println("Ingrese su nombre: ");
        String nombre = scan.next();
        System.out.println("Ingrese su apellido: ");
        String apellido = scan.next();
        System.out.println("Ingrese su mail: ");
        String mail = scan.next();
        System.out.println("Ingrese la contraseña, debe contener al menos: 8 caracteres, una minuscula, una mayuscula y un numero:  ");
        String clave = scan.next();
        
        
        try {
            Usuario u = new Usuario(nombre, apellido, dni, mail, clave);
            app.registrarUsuario(u);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			
			// Esta línea hace que recursivamente hasta que no se registre
			// no se va avanzar el menú
			solicitarDatosYRegistrar(app);
		}
    }
    
    public static void iniciarSesion(UsuarioApp app)
    {
    	while (!app.estaLogeado())
    	{
    		System.out.println("===== INICIAR SESIÓN =====");
        	System.out.println("ingrese el Dni");
            Scanner scan = new Scanner(System.in);
            int dni= scan.nextInt();
            
            System.out.println("ingrese la clave");
            String clave = scan.next();
            
            app.iniciarSesion(dni, clave);
            if (!app.estaLogeado())
            {
            	System.out.println("La información ingresada es incorrecta");
            	System.out.println("Desea registrase? y/n");
            	String resp = scan.next();
            	if (!resp.equals("y"))
            		break;
            	Main.solicitarDatosYRegistrar(app);
            }
            else
            {
            	System.out.println("Se ha iniciado sesión correctamente");
            }
    	}
   
    }
    
    public static void asociarTarjeta(UsuarioApp app, Usuario u)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("ingrese el numero de la tarjeta");
        long numero = scan.nextInt();
        System.out.println("ingrese el nombre del titular de la tarjeta");
        String titular = scan.next();
        System.out.println("ingrese el banco de la tarjeta");
        String banco = scan.next();
        System.out.println("ingrese la marca de la tarjeta");
        String marca = scan.next();
        
        
        
        
        System.out.println("ingrese la fecha de vencimiento de la tarjeta");
        LocalDate fecha = LocalDate.parse(scan.next());
        if (!fecha.isBefore(LocalDate.now())) {
            System.out.println("ingrese el codigo de seguridad ");
            int codigo = scan.nextInt();
            
            TarjetaDeCredito tarjeta = new TarjetaDeCredito(titular, numero, fecha, codigo, banco, marca);
            app.asociarTarjeta(u, tarjeta);
            
            System.out.println("la tarjeta fue asociada correctamente");
        } else {
            System.out.println("la tarjeta a ingresada esta vencida");
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
    
    
    
    public static void mostrarMenuPrincipal(UsuarioApp app) {
        Scanner scanner = new Scanner(System.in);

        while (app.estaLogeado()) {
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Buscar viaje");
            System.out.println("2. Salir");

            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Main.buscarViaje(app);
                    break;
                case 2:
                    System.out.println("Saliendo del programa...");
                    app.deslogear();
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }
    }

    
    
    
    
    
	public static void main (String [ ] args) {
		
		// Cargamos la plataforma
		
		EmpresaTransporte plusmar = new EmpresaTransporte("Plusmar");
		plusmar.addNewOmnibus();
		Omnibus omLangeyu = plusmar.buscarOmnibus(1);
		
        Viaje v1 = new Viaje("Tandil", "MDQ", LocalDateTime.of(2023, 6, 1, 21, 0), 2000, omLangeyu, null, null);
        omLangeyu.agregarItinerario(v1);
        Viaje v2 = new Viaje("Buenos Aires", "Mar del Plata", LocalDateTime.of(2023, 6, 2, 10, 30), 1800, omLangeyu, null, null);
        omLangeyu.agregarItinerario(v2);
        Viaje v3 = new Viaje("La Plata", "Bahía Blanca", LocalDateTime.of(2023, 6, 3, 16, 15), 2500, omLangeyu, null, null);
        omLangeyu.agregarItinerario(v3);
        Viaje v4 = new Viaje("Mar del Plata", "Tandil", LocalDateTime.of(2023, 6, 4, 8, 45), 1900, omLangeyu, null, null);
        omLangeyu.agregarItinerario(v4);
    	
		EmpresaTransporte balin = new EmpresaTransporte("Balin");
		balin.addNewOmnibus();
		Omnibus omEmpresa = plusmar.buscarOmnibus(1);
		
        Viaje v5 = new Viaje("Córdoba", "Mendoza", LocalDateTime.of(2023, 6, 1, 9, 0), 3500, omEmpresa, null, null);
        omEmpresa.agregarItinerario(v5);
        Viaje v6 = new Viaje("Buenos Aires", "Rosario", LocalDateTime.of(2023, 6, 2, 12, 30), 1200, omEmpresa, null, null);
        omEmpresa.agregarItinerario(v6);
        Viaje v7 = new Viaje("Santa Fe", "Córdoba", LocalDateTime.of(2023, 6, 3, 15, 45), 1800, omEmpresa, null, null);
        omEmpresa.agregarItinerario(v7);
        Viaje v8 = new Viaje("Mendoza", "Buenos Aires", LocalDateTime.of(2023, 6, 4, 18, 20), 3200, omEmpresa, null, null);
        omEmpresa.agregarItinerario(v8);

		EmpresaTransporte flecha = new EmpresaTransporte("Flecha");
		flecha.addNewOmnibus();
		Omnibus omFlecha = plusmar.buscarOmnibus(1);

		
        Viaje v9 = new Viaje("Rosario", "Santa Fe", LocalDateTime.of(2023, 6, 1, 14, 0), 800, omFlecha, null, null);
        omFlecha.agregarItinerario(v9);
        Viaje v10 = new Viaje("Mar del Plata", "Buenos Aires", LocalDateTime.of(2023, 6, 2, 17, 45), 1500, omFlecha, null, null);
        omFlecha.agregarItinerario(v10);
        Viaje v11 = new Viaje("Córdoba", "Rosario", LocalDateTime.of(2023, 6, 3, 9, 30), 1000, omFlecha, null, null);
        omFlecha.agregarItinerario(v11);
        Viaje v12 = new Viaje("Santa Fe", "Mar del Plata", LocalDateTime.of(2023, 6, 4, 12, 15), 2000, omFlecha, null, null);
        omFlecha.agregarItinerario(v12);
    	
    	//EmpresaTransporte langeyu = new EmpresaTransporte("Langeyú", omnibus);
    	
    	ArrayList<EmpresaTransporte> empresas = new ArrayList<EmpresaTransporte>();
    	
    	empresas.add(plusmar);
    	empresas.add(balin);
    	empresas.add(flecha);
    	
    	Plataforma p = new Plataforma(empresas);
    	
		
		
		// Iniciamos la sesión del usuario que va a utilizar la aplicación
		UsuarioApp app = new UsuarioApp(p);

		
		Main.iniciarSesion(app);
		Main.mostrarMenuPrincipal(app);
	}
}
