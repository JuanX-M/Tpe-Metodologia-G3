import java.util.Scanner;

public class Main {

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

}
