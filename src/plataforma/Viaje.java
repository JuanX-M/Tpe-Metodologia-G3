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
    private ArrayList<String> recorridosIntermedios = new ArrayList<String>();;

    private int id;
    private static int contador = 0;
    
    private ArrayList<Asiento> asientos;
    
    public Viaje(String origen, 
    		String destino, LocalDate fecha, 
    		double precio, LocalTime llegada, LocalTime salida) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.precio = precio;
        this.id = contador;
        contador++;
        this.horaLlegada = llegada;
        this.horaSalida = salida;
    }
    
    
    public Viaje(String origen, 
    		String destino, LocalDate fecha, 
    		double precio, Omnibus om, LocalTime llegada, LocalTime salida) {
        this(origen, destino, fecha, precio, llegada, salida);
        this.omnibus = om;
        this.asientos  = new ArrayList<Asiento>();
        for (int i = 1; i <= omnibus.getLimiteAsientos(); i++)
        	asientos.add(new Asiento());
    }
    
    
    public int getId()
    {
    	return this.id;
    }
    
	@Override
	public boolean equals(Object obj) {
		Viaje otroViaje = (Viaje) obj;
		return this.getId() == otroViaje.getId();
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
    
    
    public ArrayList<Asiento> getAsientos()
    {
    	return new ArrayList<Asiento>(this.asientos);
    }
    
    public ArrayList<Asiento> asientosLibres(){
    	ArrayList<Asiento> res = new ArrayList<Asiento>();
    	for (int i = 0; i<asientos.size();i++) {
    		if (!asientos.get(i).isReservado())
    			res.add(asientos.get(i));
    	}
    	return res;
    }
    
    public void reservarAsiento(int nroAsiento, Usuario pasajero) {
    	for (int i = 0; i< asientos.size();i++) {
    		if (asientos.get(i).getNumero() == nroAsiento)
    			asientos.get(i).reservar(pasajero);
    	}
    }
    
    

}
