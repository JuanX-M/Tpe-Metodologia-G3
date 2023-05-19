

import java.util.ArrayList;

public class RegistroUsuario {
    private ArrayList<Usuario> usuarios;

    public RegistroUsuario(){
        this.usuarios= new ArrayList<Usuario>();
    }

    public void agregarUsuario(Usuario u){//por ahora sin criterio de ordenamiento
        this.usuarios.add(u);
    }
    public void darBajaUsuario(Usuario u){
        this.usuarios.remove(u);
    }
    public boolean existeDNI(int dni){
        for(Usuario u:usuarios){
            if(u.getDNI()==dni){
                return true;
            }
        }
        return false;
    }
    public Usuario buscarUsuarioPorCondicion(Condicion c){
        for(Usuario u:usuarios){
            if(c.cumple(u)){
                return u;
            }
        }
        return null;
    }
}
