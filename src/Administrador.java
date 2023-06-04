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

    public void darAlta(Viaje viaje, Omnibus omnibus){
    	omnibus.agregarItinerario(viaje);
    }
}
