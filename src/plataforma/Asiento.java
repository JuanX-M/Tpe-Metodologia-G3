package plataforma;
public class Asiento {
    private int numero;
    private boolean reservado;
    private static int contador=0;


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

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    @Override
    public boolean equals(Object obj) {
        Asiento a= (Asiento)obj;

        return a.getNumero() == this.numero;
    }

    @Override
    public String toString() {
        return "NroAsiento: "+this.numero;
    }
}
