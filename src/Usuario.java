import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
    private String nombre;
    private String apellido;
    private int DNI;
    private String mail;
    private String clave;
    private TarjetaDeCredito metodoPago;
    private double creditos;
    //private ArrayList<Compra> compras;


    public String getNombre() {
        return nombre;
    }

    public Usuario(String nombre, String apellido, int dNI, String mail, String clave) throws Exception {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.mail = mail;
		
		this.verificarFormatoClave();
		this.clave = clave;
    }

    
    public void verificarFormatoClave() throws Exception
    {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(clave);

        if (!matcher.matches())
        	throw new Exception("La clave no cumple con el formato");
    }
    
    
    public boolean coincide(String claveSupuesta)
    {
    	return this.clave.equals(claveSupuesta);
    }
    
    
    
    @Override
    public boolean equals(Object o)
    {
    	Usuario otroUsuario = (Usuario) o;
    	return this.getDNI() == (otroUsuario.getDNI()) 
    			&& this.getClave().equals(otroUsuario.getClave());
    }
    
    
    public void asociarTarjeta(TarjetaDeCredito t)
    {
    	metodoPago = t;
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
