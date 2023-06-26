package plataforma;



public class FiltroAND extends Filtro {

	Filtro f1;
	Filtro f2;
	
	public FiltroAND(Filtro f1, Filtro f2) {
		this.f1 = f1;
		this.f2 = f2;
	}

	public boolean cumple(Viaje v) {
		return f1.cumple(v)  &&  f2.cumple(v);
	}

}