package plataforma;


import java.util.ArrayList;

public class RegistroUsuario {
    private ArrayList<Usuario> usuarios;

    public RegistroUsuario(){
        this.usuarios= new ArrayList<Usuario>();
    }

    public void agregarUsuario(Usuario u) throws Exception{//por ahora sin criterio de ordenamiento
    	boolean parcialMod = false;
    	for (int i = 0; i < this.usuarios.size(); i++) {
    	    Usuario registrado = this.usuarios.get(i);
    	    if (registrado.getDNI() == u.getDNI()) {
    	        if (registrado.isRegistroParcial()) {
    	            this.usuarios.set(i, u);
    	            parcialMod = true;
    	        } else {
    	            throw new Exception("El DNI ingresado ya fue registrado");
    	        }
    	    }
    	}
    	
    	if (!parcialMod)
    		this.usuarios.add(u);
    }
    
    
    public void darBajaUsuario(Usuario u) throws Exception{
        if (this.existeDNI(u.getDNI()))
        	throw new Exception("El DNI no esta registrado");
        this.usuarios.remove(u);
    }
    public boolean existeDNI(int dni){
        for(Usuario u:usuarios){
            if(u.getDNI()==dni)
                return true;
        }
        return false;
    }
    public ArrayList<Usuario> buscarUsuarioPorCondicion(Condicion c){
        ArrayList<Usuario> usuariosAptos = new ArrayList<Usuario>();
    	for(Usuario u:usuarios){
            if(c.cumple(u)){
                usuariosAptos.add(u);
            }
        }
        return usuariosAptos;
    }
}
