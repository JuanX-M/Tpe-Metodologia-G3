package plataforma;


public class Asiento {
    private int numero;
    private boolean reservado;
    private Usuario pasajero;
    private static int contador = 0;


    public Asiento(){
        contador+=1;
        numero=contador;
        this.reservado=false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void reservar(Usuario u) {
    	if (!this.isReservado())
    	{
            this.reservado = true;
            this.pasajero = u;	
    	}
    }

    @Override
    public boolean equals(Object obj) {
        Asiento a = (Asiento) obj;
        return a.getNumero() == this.numero;
    }

    @Override
    public String toString() {
        return "NroAsiento: "+this.numero;
    }

	public void cancelar() {
		pasajero = null;
		this.reservado = false;
	}
}
