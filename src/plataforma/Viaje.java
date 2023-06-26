package plataforma;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Viaje {
    private String origen;
    private String destino;
    private LocalDate fecha;
    private double precio;

    private Omnibus omnibus;
    private LocalTime horaLlegada;
    private LocalTime horaSalida;
    private ArrayList<String> recorridosIntermedios;
    
    public Viaje(String origen, 
    		String destino, LocalDate fecha, 
    		double precio, Omnibus om, LocalTime llegada, LocalTime salida) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.precio = precio;
        this.omnibus = om;
        this.horaLlegada = llegada;
        this.horaSalida = salida;
        recorridosIntermedios= new ArrayList<String>();
    }
    
    
    
    
    @Override
	public String toString() {
		return "Empresa: " + omnibus.getNombreEmpresa() + " | hora de llegada: " + horaLlegada + " | hora de salida: " + horaSalida
                      + "| recorridos intermedios: " + this.imprimirRecorridosIntermedios();
	}

    public String imprimirRecorridosIntermedios(){
        StringBuilder sb=new StringBuilder();

        for(String s:recorridosIntermedios){
            sb.append(s);
            sb.append(" - ");
        }
        return sb.toString();
    }

    public ArrayList<String> getRecorridosIntermedios(){
        return  new ArrayList<String>(this.recorridosIntermedios);
    }
    public void addRecorridoIntermedio(String recorrido){
        if(!recorridosIntermedios.contains(recorrido))
            recorridosIntermedios.add(recorrido);
    }
	public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public LocalDate getFecha() { return fecha; }
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

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setOmnibus(Omnibus omnibus) {
        this.omnibus = omnibus;
    }

}
