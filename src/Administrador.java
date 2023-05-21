import java.util.ArrayList;
import java.util.Scanner;
public class Administrador {
    private String nombre;
    private String apellido;
    private int DNI;
    private String clave;
    private String email;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public String getClave() {
        return clave;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    // TODO: Cambiar esta función
    public boolean verificarDatos(Viaje viaje){
    	return (viaje.getDestino() == null && 
    		    viaje.getFecha() == null &&
    		    viaje.getOrigen() == null &&
    		    viaje.getOmnibus() == null &&
    		    viaje.getPrecio() == 0.0);
    }

    public void darAlta(Viaje viaje, Omnibus omnibus){
        Scanner scan= new Scanner(System.in);
        System.out.println("presione 1 si desea verificar los datos");
        int opcion= scan.nextInt();
        if(opcion==1){
            if(this.verificarDatos(viaje)){
                omnibus.agregarItinerario(viaje);
                System.out.println("el viaje se dio de alta");
            }
        }else{
            System.out.println("el viaje no se dio de alta");
        }
    }
}
