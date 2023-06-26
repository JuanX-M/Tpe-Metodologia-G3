package plataforma;

public class FiltroEmpresa extends Filtro {

    private String nombre;

    public FiltroEmpresa(String nombre){
        this.nombre = nombre;
    }

    public boolean cumple(Viaje v){
        // Verifica si empresa asociada al viaje y empresa de filtrado tienen el mismo nombre
        return nombre.equals(v.getOmnibus().getNombreEmpresa());
    }

}