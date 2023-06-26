package plataforma;
import java.time.LocalDate;
import java.util.ArrayList;
public class Omnibus {
    private EmpresaTransporte empresaDuenia;
    private int numOmnibus;
    private ArrayList<Viaje> itinerario; //los viajes que tiene que hacer el omnibus
    private ArrayList<Asiento> asientos;
    private int limiteAsientos=50; //mirar

    public Omnibus(EmpresaTransporte em, int num){
        numOmnibus = num;
        empresaDuenia = em;
        itinerario= new ArrayList<Viaje>();
        asientos= new ArrayList<Asiento>();
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
    public ArrayList<Asiento> getAsientos(){
        ArrayList<Asiento> copia=new ArrayList<Asiento>();
        copia.addAll(this.asientos);
        return copia;
    }

    public void agregarItinerario(Viaje viaje){
        this.itinerario.add(viaje);
    }
    public void agregarAsiento(Asiento asiento){
        this.asientos.add(asiento);
    }
    
    public String getNombreEmpresa() {
        return empresaDuenia.getNombre();
    }
}
