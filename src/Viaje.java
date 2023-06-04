import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Viaje {
    private String origen;
    private String destino;
    private LocalDateTime fecha;
    private double precio;
    private Omnibus omnibus;
    private LocalTime horaLlegada;
    private LocalTime horaSalida;
    
    
    public Viaje(String origen, 
    		String destino, LocalDateTime fecha, 
    		double precio, Omnibus om, LocalTime llegada, LocalTime salida) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.precio = precio;
        this.omnibus = om;
        this.horaLlegada = llegada;
        this.horaSalida = salida;
    }
    
    
    
    
    @Override
	public String toString() {
    	// TODO: agregar recorridos intermedios?
		return "Empresa: " + omnibus.getNombreEmpresa() + " | hora de llegada: " + horaLlegada + " | hora de salida: " + horaSalida;
	}


	public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public LocalDateTime getFecha() { return fecha; }
    public double getPrecio() { return precio; }
    public Omnibus getOmnibus() { return omnibus; }
    

    public LocalTime getLlegada()
    {
    	return this.horaLlegada;
    }
    
    public LocalTime getSalida()
    {
    	return this.horaSalida;
    }
    
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setOmnibus(Omnibus omnibus) {
        this.omnibus = omnibus;
    }
}
