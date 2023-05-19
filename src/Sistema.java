
import java.time.LocalDate;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Sistema {

    public void solicitarDatosYRegistrar(UsuarioApp app) {
        Usuario u = new Usuario();
        Scanner scan = new Scanner(System.in);
        System.out.println("ingrese su nombre");
        String n = scan.nextLine();
        System.out.println("ingrese su apellido");
        String a = scan.nextLine();
        System.out.println("ingrese su DNI");
        int dni = scan.nextInt();
        if (!app.verificarExisteDNI(dni)) {
            System.out.println("ingrese su mail");
            String mail = scan.next();
            System.out.println("igrese la contraseña: al menos: 8 caracteres, una minuscula, una mayuscula y un numero ");
            String clave = scan.next();
            while (!app.verificarClave(clave)) {
                System.out.println("clave mal ingresada, ingrese otra");
                clave = scan.next();
            }
            //------------------------
            u.setNombre(n);
            u.setApellido(a);
            u.setDNI(dni);
            u.setMail(mail);
            u.setClave(clave);
            app.registrarUsuario(u);
        } else {
            System.out.println("el DNI ingresado ya existe en el sistema");
        }
    }

    public boolean loggear (UsuarioApp app, Usuario u){ //retorna true si el usuario esta ingresado y le mando como parametro un usuario vacio
        Scanner scan = new Scanner(System.in);
        System.out.println("ingrese su DNI");
        int dni = scan.nextInt();
        System.out.println("igrese la contraseña");
        String clave = scan.next();
        if(app.verificarExisteDNI(dni)){
            CondicionMismoDni condicion=new CondicionMismoDni(dni);
            if(app.buscarUsuario(condicion).getClave()==clave){
                u=app.buscarUsuario(condicion); //le mando el usuario con el que voy a hacer el resto de las funciones
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public static void main(String[] args) {

        UsuarioApp app = new UsuarioApp();
        Sistema sistema = new Sistema();
        sistema.solicitarDatosYRegistrar(app);

    }
}