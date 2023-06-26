package plataforma;

public class FiltroCosto extends Filtro {

    private double piso;
    private double techo;
    
    public FiltroCosto(double piso, double techo){
        this.piso = piso;
        this.techo = techo;
    }

    public boolean cumple(Viaje v){
        
        return piso <= v.getPrecio() && techo >= v.getPrecio();
    }

}