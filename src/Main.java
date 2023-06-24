import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;



// TODO: refactorizar lógica del menú en una clase

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


        Main.mostrarViajes(viajesBuscados);
        
        boolean seleccionando = true;
        while (seleccionando) {
            System.out.println("1. Seleccionar viaje");
            System.out.println("2. filtrar viajes");
            System.out.println("3. Salir");

            System.out.print("Ingrese la opción deseada: ");
            int opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                	System.out.println("Ingrese el número de viaje que desea elegir: ");
                	int numViaje = scan.nextInt();
                    // TODO: se tendria pasar al pago
                    break;
                case 2:
                	Main.filtrar(viajesBuscados, app);
                    break;
                case 3:
                	seleccionando = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }
        // Ahora deberiamos preguntar si se quiere filtrar o seleccionar un viaje
    }
    
        
        
        
    public static void filtrar(ArrayList<Viaje> viajes, UsuarioApp app)
    {
        boolean filtrando = true;
        Scanner scan = new Scanner(System.in);
        while (filtrando) {

            System.out.println("1. por costo");
            System.out.println("2. por horario");
            System.out.println("3. por empresa");
            System.out.println("4. dejar de filtrar");

            System.out.print("Ingrese la opción deseada: ");
            int opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    FiltroCosto filtro = Main.preguntarFiltroCosto();
                    ArrayList<Viaje> filtrados = app.filtrar(viajes, filtro);
                    Main.mostrarViajes(filtrados);
                    break;
                case 2:
                    FiltroMargenHorario filtro2 = Main.preguntarFiltroHorario();
                    ArrayList<Viaje> filtrados2 = app.filtrar(viajes, filtro2);
                    Main.mostrarViajes(filtrados2);
                    break;
                case 3:
                    FiltroEmpresa filtro3 = Main.preguntarFiltroEmpresa();
                    ArrayList<Viaje> filtrados3 = app.filtrar(viajes, filtro3);
                    Main.mostrarViajes(filtrados3);
                    break;
                case 4:
                	filtrando = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }	
        }
    }
    
    public static FiltroCosto preguntarFiltroCosto()
    {
    	Scanner scan = new Scanner(System.in);
        System.out.println("ingrese el mínimo costo: ");
        double piso = scan.nextDouble();
        System.out.println("ingrese el máximo costo: ");
        double max = scan.nextDouble();
        return new FiltroCosto(piso, max);
    }

    public static FiltroMargenHorario preguntarFiltroHorario()
    {
    	Scanner scan = new Scanner(System.in);
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
    	
        System.out.println("ingrese un horario de salida en el formato HH:mm: ");
        LocalTime salida = LocalTime.parse(scan.nextLine(), formato);
        
        System.out.println("ingrese un horario de llegada en el formato HH:mm: ");
        LocalTime llegada = LocalTime.parse(scan.nextLine(), formato);
        
        return new FiltroMargenHorario(salida, llegada);
    }
    
    
    
    public static FiltroEmpresa preguntarFiltroEmpresa()
    {
    	Scanner scan = new Scanner(System.in);
        System.out.println("ingrese el nombre de la empresa: ");
        String nombre = scan.next();
        return new FiltroEmpresa(nombre);
    }
    
    
    
    
    public static void mostrarViajes(ArrayList<Viaje> viajes)
    {
        if (viajes.isEmpty())
            System.out.println("No se encontraron viajes para la entrada dada. ");


        
        for (int i = 0; i < viajes.size(); i++)
        {
        	System.out.println("Viajes disponibles para la entrada dada: ");
        	System.out.print(i + " ");
        	System.out.println(viajes.get(i));
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
        v1.addRecorridoIntermedio("Necochea");
        v1.addRecorridoIntermedio("San Manuel");
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
        v5.addRecorridoIntermedio("Ciudad de Rio Tercero");
        v5.addRecorridoIntermedio("San Luis");
        omEmpresa.agregarItinerario(v5);
        Viaje v6 = new Viaje("Buenos Aires", "Rosario", LocalDateTime.of(2023, 6, 2, 12, 30), 1200, omEmpresa, null, null);
        v6.addRecorridoIntermedio("Zarate");
        v6.addRecorridoIntermedio("Arroyo Seco");
        omEmpresa.agregarItinerario(v6);
        Viaje v7 = new Viaje("Santa Fe", "Córdoba", LocalDateTime.of(2023, 6, 3, 15, 45), 1800, omEmpresa, null, null);
        v7.addRecorridoIntermedio("San Francisco");
        v7.addRecorridoIntermedio("Arroyito");
        omEmpresa.agregarItinerario(v7);
        Viaje v8 = new Viaje("Mendoza", "Buenos Aires", LocalDateTime.of(2023, 6, 4, 18, 20), 3200, omEmpresa, null, null);
        v8.addRecorridoIntermedio("San Luis");
        omEmpresa.agregarItinerario(v8);

		EmpresaTransporte flecha = new EmpresaTransporte("Flecha");
		flecha.addNewOmnibus();
		Omnibus omFlecha = plusmar.buscarOmnibus(1);

		
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
