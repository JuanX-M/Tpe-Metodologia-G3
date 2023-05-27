
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.Period;

public class UsuarioApp {
    private RegistroUsuario userRegistro;
    //private Plataforma plataforma;
    private boolean logged;
    Plataforma p;
    public UsuarioApp(Plataforma p){
    	this.p = p;
        this.userRegistro=new RegistroUsuario();
        //this.plataforma= new Plataforma();
        this.logged=false;
    }

    public void iniciarSesion(){
        System.out.println("ingrese el Dni");
        Scanner scan = new Scanner(System.in);
        int dni= scan.nextInt();
        if(this.verificarExisteDNI(dni)) {
            System.out.println("ingrese la clave");
            String clave= scan.next();
            while(!this.verificarClave(clave)){
                System.out.println("la clave es incorrecta, ingresela de nuevo: ");
                clave= scan.next();
            }
            System.out.println("se inició sesión correctamente");
        }else{
            System.out.println("El Dni no se encuentra en el sistema, tiene que registrarse");
        }
    }

    public void registrarUsuario(Usuario u){
        userRegistro.agregarUsuario(u);
    }

    public boolean verificarClave(String clave) { //retorna un true si la clave cumple con las condiciones
        if (clave.length() < 8) {
            return false;
        }
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(clave);

        return matcher.matches();
    }
    public boolean verificarExisteDNI(int dni){
        return this.userRegistro.existeDNI(dni);
    }

    public Usuario buscarUsuario(Condicion c){
        return this.userRegistro.buscarUsuarioPorCondicion(c);
    }

    public void asociarTarjeta(Usuario u) {
        Scanner scan = new Scanner(System.in);
        System.out.println("ingrese el numero de la tarjeta");
        long numero = scan.nextInt();
        System.out.println("ingrese el nombre del titular de la tarjeta");
        String titular = scan.next();
        System.out.println("ingrese la fecha de vencimiento de la tarjeta");
        LocalDate fecha = LocalDate.parse(scan.next());
        if (!fecha.isBefore(LocalDate.now())) {
            System.out.println("ingrese el codigo de seguridad ");
            int codigo = scan.nextInt();
            System.out.println("la tarjeta fue asociada correctamente");
        } else {
            System.out.println("la tarjeta a ingresada esta vencida");
        }
    }


    public ArrayList<Viaje> buscarViaje(String  origen, String destino, LocalDate fecha){
           return p.buscarViaje(origen, destino, fecha);
    }
    
    
    
    public ArrayList<Viaje> filtrar(ArrayList<Viaje> viajes, Filtro f){
        ArrayList<Viaje> res = new ArrayList<Viaje>();
        for (int i = 0; i < viajes.size(); i++){
            Viaje v = viajes.get(i);
            if (f.cumple(v))
                res.add(v);
        }
        return res;
    }

}
