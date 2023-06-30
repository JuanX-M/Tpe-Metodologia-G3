package plataforma;
public class Compra {
	
	private int nroAsiento;
	private Viaje v;
	private Usuario pasajero;
	
	public Compra(int nroAsiento, Viaje v, Usuario pasajero) {
		this.nroAsiento = nroAsiento;
		this.v = v;
		this.pasajero = pasajero;
	}
	
	public int getNroAsiento() {
		return nroAsiento;
	}
	
	public Viaje getViaje() {
		return v;
	}
	
	public Usuario getPasajero() {
		return pasajero;
	}
}