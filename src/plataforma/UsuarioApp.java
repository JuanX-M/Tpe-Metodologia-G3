package plataforma;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioApp {
    private RegistroUsuario userRegistro;
    private boolean logged;
    private Plataforma p;
    public UsuarioApp(Plataforma p){
    	this.p = p;
        this.userRegistro=new RegistroUsuario();
        this.logged=false;
    }

    
    public void deslogear()
    {
    	this.logged = false;
    }
    
    
    public boolean iniciarSesion(int dni, String clave){
       	if (!logged)
       	{
        	if (!this.userRegistro.existeDNI(dni))
        		return false;
        	
        	Condicion condicion = new CondicionMismoDni(dni);
        	Usuario usuarioRegistrado = userRegistro.buscarUsuarioPorCondicion(condicion).get(0); 
        	// hacemos el get(0) ya que deberia de ser un único usuario el que cumple la condicion
        	boolean coincide = usuarioRegistrado.coincide(clave);
        	if (coincide)
        		this.logged = true;
        	return coincide;	
       	}
       	return true;
    }

    
    public boolean estaLogeado()
    {
    	return this.logged;
    }
    
    public void registrarUsuario(Usuario u) throws Exception{
        userRegistro.agregarUsuario(u);
    }

    public boolean verificarExisteDNI(int dni){
        return this.userRegistro.existeDNI(dni);
    }

    public ArrayList<Usuario> buscarUsuario(Condicion c){
        return this.userRegistro.buscarUsuarioPorCondicion(c);
    }

    public void asociarTarjeta(Usuario u, TarjetaDeCredito tarjeta) {
    	u.asociarTarjeta(tarjeta);
    }


    public ArrayList<Viaje> buscarViaje(String  origen, String destino, LocalDate fecha){
           return p.buscarViaje(origen, destino, fecha);
    }
    
    public Plataforma getPlataforma()
    {
    	return p;
    }
    
    public ArrayList<Viaje> filtrar(ArrayList<Viaje> viajes, Filtro f){
        ArrayList<Viaje> res = new ArrayList<Viaje>();
        for (int i = 0; i < viajes.size(); i++){
            Viaje v = viajes.get(i);
            if (f.cumple(v))
                res.add(v);
        }
        return res;
    }
    
    public ArrayList<Asiento> getAsientosLibres(Viaje v) {
    	return v.asientosLibres();
    }
    public Compra seleccionar(Usuario u, Asiento a, Viaje v) { //mirar
    	return new Compra(a.getNumero(),v,u);
    }
    
    public void confirmarCompra(String nombreEmpresa, Compra c) {
    	EmpresaTransporte e = p.buscarEmpresa(nombreEmpresa);
    	e.agregarReserva(c);
    	// aca tmb iria lo de mandar mail
    }
    

}
