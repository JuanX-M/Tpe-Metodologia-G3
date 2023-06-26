package plataforma;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Plataforma {
    private ArrayList<EmpresaTransporte> listaEmpresas = new ArrayList<EmpresaTransporte>();

    
    
    
    public Plataforma() {
        
    	
    	GestionadorDatos.cargarDatos("viajes.csv", this);
    	//this.listaEmpresas = listaEmpresas;
    }
    
    public void cargarDatos()
    {
    	GestionadorDatos.cargarDatos("viajes.csv", this);
    }
    
    public void escribirDatos()
    {
    	GestionadorDatos.guardarDatos("viajes.csv", this);
    }
    
    
    public void agregarEmpresa(EmpresaTransporte e)
    {
    	if (!this.listaEmpresas.contains(e))
    		this.listaEmpresas.add(e);
    }
    
    
    public ArrayList<EmpresaTransporte> getEmpresas()
    {
    	return new ArrayList<EmpresaTransporte>(this.listaEmpresas);
    }
    
    
    public EmpresaTransporte buscarEmpresa(String nombreEmpresa)
    {
    	for (EmpresaTransporte empresa : this.listaEmpresas)
    		if (empresa.getNombre().equals(nombreEmpresa))
    			return empresa;
    	return null;
    }
    
    public ArrayList<Viaje> buscarViaje(String origen, String destino, LocalDate fecha){
        ArrayList<Viaje> v= new ArrayList<Viaje>();
        for (EmpresaTransporte e : listaEmpresas) {
        	//System.out.println(e);
            v.addAll(e.obtenerViajes(origen,destino,fecha));
        }
        return v;
    };    
}
