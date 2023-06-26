package plataforma;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Plataforma {
    private ArrayList<EmpresaTransporte> listaEmpresas;

    
    
    
    public Plataforma(ArrayList<EmpresaTransporte> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
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
