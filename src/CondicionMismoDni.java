public class CondicionMismoDni extends Condicion {
    private int dni;
    public CondicionMismoDni(int dni){
        super();
        this.dni=dni;
    }

    public boolean cumple (Usuario u){
        return this.dni==u.getDNI();
    }
}
