import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EmpresaTransporte {
    private ArrayList<Omnibus> omnibus;
    //private RegistroCompras reservas;
    private String nombre;

    public EmpresaTransporte(String nombre) {
        this.nombre = nombre;
        this.omnibus = new ArrayList<>();
        //this.reservas = new RegistroCompras();
    }

    public ArrayList<Viaje> obtenerViajes(String origen, String destino, LocalDate fecha) {
        ArrayList<Viaje> viajesDisponibles = new ArrayList<Viaje>();
        for (Omnibus bus : omnibus) {
            for (Viaje viaje : bus.getViajes(origen, destino, fecha)) {
                viajesDisponibles.add(viaje);
                }
            }
        return viajesDisponibles;
    }

    // Getters and setters
    public ArrayList<Omnibus> getOmnibus() {
        return omnibus;
    }

    public void setOmnibus(ArrayList<Omnibus> omnibus) {
        this.omnibus = omnibus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
