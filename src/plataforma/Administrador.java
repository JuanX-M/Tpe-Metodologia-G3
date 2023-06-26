package plataforma;
public class Administrador extends Usuario{


    public Administrador(String nombre, String apellido, int dNI, String mail, String clave) throws Exception {
		super(nombre, apellido, dNI, mail, clave);
		// TODO Auto-generated constructor stub
	}
    
    
    public String toString()
    {
    	return "Administrador " + super.getNombre();
    }
    
    
	public void darAlta(Viaje viaje, Omnibus omnibus){
    	omnibus.agregarItinerario(viaje);
    }
}
