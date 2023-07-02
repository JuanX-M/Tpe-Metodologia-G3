package plataforma;

import java.util.ArrayList;

public class Compra {

	private ArrayList<Asiento> asientos;
	private ArrayList<Usuario> ocupantes;
	private Viaje viaje;
	private Usuario comprador;


	public Compra(Viaje viaje, Usuario comprador) {
		this.asientos= new ArrayList<Asiento>();
		this.ocupantes = new ArrayList<Usuario>();
		this.viaje = viaje;
		this.comprador = comprador;
	}

	
	/*
	public void addOcupante(Usuario ocupante){
		this.ocupantes.add(ocupante);
	}

	public ArrayList<Usuario> getOcupantes(){
		return new ArrayList<Usuario>(ocupantes);
	}
	
	/*
	public void imprimir(){
		ArrayList<Usuario> aux = new ArrayList<>(ocupantes);
		aux.add(this.pasajero);
		System.out.println("Detalles Pasajeros: ");
		for (int i = 0; i < this.ocupantes.size() ; i++) {
			System.out.println(asientos.get(i) + "|"+ aux.get(i));
		}
	}
	*/
	
	public void addAsiento(Asiento a){
		if(!asientos.contains(a)){
			asientos.add(a);
		}
	}
	
	
	public int getCantAsientos()
	{
		return this.asientos.size();
	}

	public ArrayList<Asiento> getAsientos() {
		return new ArrayList<Asiento>(asientos);
	}

	public Viaje getViaje() {
		return this.viaje;
	}

	public Usuario getComprador() {
		return this.comprador;
	}
}