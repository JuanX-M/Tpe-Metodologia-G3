package plataforma;
import java.time.LocalDate;
import java.util.ArrayList;
public class Omnibus {
    private EmpresaTransporte empresaDuenia;
    private int numOmnibus;
    private ArrayList<Viaje> itinerario; //los viajes que tiene que hacer el omnibus
    private int limiteAsientos = 30;

    public Omnibus(EmpresaTransporte em, int num){
        numOmnibus = num;
        empresaDuenia = em;
        itinerario= new ArrayList<Viaje>();
    }
    
    public int getNum()
    {
    	return numOmnibus;
    }
    
    public ArrayList<Viaje> getItinerario(){
        ArrayList<Viaje> copia = new ArrayList<Viaje>();
        copia.addAll(this.itinerario);
        return copia;
    }
    public ArrayList<Viaje> getViajes(String origen, String destino, LocalDate fecha){
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        for (Viaje viaje : this.getItinerario()) {
            if (viaje.getOrigen().equals(origen) 
            		&& viaje.getDestino().equals(destino) 
            		&& viaje.getFecha().equals(fecha)) {
                viajes.add(viaje);
            }
        }
        return viajes;    
    }
    
    
    public void agregarItinerario(Viaje viaje){
    	viaje.setOmnibus(this);
        this.itinerario.add(viaje);
    }
    
    public void eliminarViaje(Viaje viaje)
    {
    	boolean borrado = this.itinerario.remove(viaje);
    	assert borrado;
    }
    
    public String getNombreEmpresa() {
        return empresaDuenia.getNombre();
    }
    
    public int getLimiteAsientos() {
    	return limiteAsientos;
    }
}
