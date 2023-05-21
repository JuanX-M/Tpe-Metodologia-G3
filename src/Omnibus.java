import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class Omnibus {
    private String nombreEmpresa;
    private ArrayList<Viaje> itinerario; //los viajes que tiene que hacer el omnibus
    private ArrayList<Asiento> asientos;
    private int limiteAsientos=50; //mirar

    public Omnibus(String nombre){
        this.nombreEmpresa=nombre;
        itinerario= new ArrayList<Viaje>();
        asientos= new ArrayList<Asiento>();
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public ArrayList<Viaje> getItinerario(){
        ArrayList<Viaje> copia=new ArrayList<Viaje>();
        copia.addAll(this.itinerario);
        return copia;
    }
    public ArrayList<Viaje> getViajes(String origen, String destino, LocalDate fecha){
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        for (Viaje viaje : this.getItinerario()) {
            if (viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino) && viaje.getFecha().equals(fecha)) {
                viajes.add(viaje);
            }
        }
        return viajes;    }
    public ArrayList<Asiento> getAsientos(){
        ArrayList<Asiento> copia=new ArrayList<Asiento>();
        copia.addAll(this.asientos);
        return copia;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    public void agregarItinerario(Viaje viaje){
        this.itinerario.add(viaje);
    }
    public void agregarAsiento(Asiento asiento){
        this.asientos.add(asiento);
    }
}
