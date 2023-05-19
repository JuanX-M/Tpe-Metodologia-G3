import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String apellido;
    private int DNI;
    private String mail;
    private String clave;
    //private TarjetaDeCredito metodoPago;
    private double creditos;
    //private ArrayList<Compra> compras;

    public Usuario(){

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public String getMail() {
        return mail;
    }

    public double getCreditos() {
        return creditos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCreditos(double creditos) {
        this.creditos = creditos;
    }
}
