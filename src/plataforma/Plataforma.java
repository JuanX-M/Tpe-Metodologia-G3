package plataforma;
import java.time.LocalDate;
import java.util.ArrayList;

public class Plataforma {
    private ArrayList<EmpresaTransporte> listaEmpresas = new ArrayList<EmpresaTransporte>();

    
    
    
    public Plataforma() {
        
    	
    	GestionadorDatos.cargarDatos("viajes.csv", this);
    	//this.listaEmpresas = listaEmpresas;
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
    
    public ArrayList<Viaje> buscarViaje(String origen, String destino, LocalDate fecha){
        ArrayList<Viaje> v= new ArrayList<Viaje>();
        for (EmpresaTransporte e : listaEmpresas) {
        	//System.out.println(e);
            v.addAll(e.obtenerViajes(origen,destino,fecha));
        }
        return v;
    };


}
