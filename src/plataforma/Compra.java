package plataforma;

import java.util.ArrayList;

public class Compra {

	private ArrayList<Asiento> asientos;
	private ArrayList<Integer> dnis;
	private Viaje v;
	private Usuario pasajero;


	public Compra(Viaje v, Usuario pasajero) {
		this.asientos= new ArrayList<Asiento>();
		this.dnis= new ArrayList<Integer>();
		this.v = v;
		this.pasajero = pasajero;
	}

	public void addDNI(int dni){
		this.dnis.add(dni);
	}

	public ArrayList<Integer> getDnis(){
		return new ArrayList<Integer>(dnis);
	}

	public void addAsiento(Asiento a){
		if(!asientos.contains(a)){
			asientos.add(a);
		}
	}

	public ArrayList<Asiento> getAsientos() {
		return new ArrayList<Asiento>(asientos);
	}

	public Viaje getViaje() {
		return v;
	}

	@Override
	public String toString() {
		for (int i = 0; i < ; i++) {
			return
		}
		return super.toString();
	}

	public void imprimir(){
		System.out.println("Detalles Pasajeros: ");
		for (int i = 0; i < this.dnis.size() ; i++) {
			System.out.println(asientos.get(i) + "|"+);
		}
	}

	public Usuario getPasajero() {
		return pasajero;
	}
}