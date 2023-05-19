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
}
