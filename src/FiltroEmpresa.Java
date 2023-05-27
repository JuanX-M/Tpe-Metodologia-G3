public class FiltroEmpresa extends Filtro {

    private EmpresaTransporte empresa;

    public FiltroEmpresa(EmpresaTransporte empresa){
        this.EmpresaTransporte = empresa;
    }

    public boolean cumple(Viaje v){
        // Verifica si empresa asociada al viaje y empresa de filtrado tienen el mismo nombre
        return empresa.getNombre().equals(v.getOmnibus().getNombreEmpresa());
    }

}