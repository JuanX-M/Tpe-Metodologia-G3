package plataforma;

import java.util.ArrayList;

public class Compra {

	private ArrayList<Asiento> asientos;
	private ArrayList<Usuario> ocupantes;
	private Viaje v;
	private Usuario pasajero;


	public Compra(Viaje v, Usuario pasajero) {
		this.asientos= new ArrayList<Asiento>();
		this.ocupantes = new ArrayList<Usuario>();
		this.v = v;
		this.pasajero = pasajero;
	}

	public void addOcupante(Usuario uu){
		this.ocupantes.add(uu);
	}

	public ArrayList<Usuario> getOcupantes(){
		return new ArrayList<Usuario>(ocupantes);
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

	public void imprimir(){
		ArrayList<Usuario> aux = new ArrayList<>(ocupantes);
		aux.add(this.pasajero);
		System.out.println("Detalles Pasajeros: ");
		for (int i = 0; i < this.ocupantes.size() ; i++) {
			System.out.println(asientos.get(i) + "|"+ aux.get(i));
		}
	}

	public Usuario getPasajero() {
		return pasajero;
	}
}