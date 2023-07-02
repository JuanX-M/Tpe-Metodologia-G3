package plataforma;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioApp {
    private RegistroUsuario userRegistro;
    private boolean logged;
    private Usuario usuarioLogeado;    
    private Plataforma p;
    
    
    public UsuarioApp(Plataforma p){
        this.p = p;
        this.userRegistro = new RegistroUsuario();
        this.logged = false;
    }

    
    public void deslogear()
    {
        this.logged = false;
    }
    
    
    public boolean iniciarSesion(int dni, String clave){
        if (!logged) {
            if (!this.userRegistro.existeDNI(dni))
                return false;

            Condicion condicion = new CondicionMismoDni(dni);
            Usuario usuarioRegistrado = userRegistro.buscarUsuarioPorCondicion(condicion).get(0);
            // hacemos el get(0) ya que deberia de ser un único usuario el que cumple la condicion
            boolean coincide = usuarioRegistrado.coincide(clave);
            if (coincide)
            {
            	this.logged = true;
            	this.usuarioLogeado = usuarioRegistrado;
            }
            return coincide;
        }
        return true;
    }

    
    public Usuario getUsuarioLogeado()
    {
    	return this.usuarioLogeado;
    }
    
    
    
    public boolean estaLogeado()
    {
        return this.logged;
    }
    
    public void registrarUsuario(Usuario u) throws Exception {
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
    
    
    /*
    public Compra seleccionar(Usuario u, ArrayList <Asiento> a, Viaje v) { //mirar
    	ArrayList<int> dnis= new ArrayList<int>();
        Compra compra= new Compra(v,u);
        //imprimimos asientos libres
        ArrayList<Asiento> libres=this.getAsientosLibres(v);
        int contador=0;
        for (Asiento libre : libres) {
                System.out.println( contador + "| "+ libre);
                contador++;
        }

        String seguir="Y";
        Scanner cc= new Scanner(System.in);
        while (seguir.equals("Y") ){
            System.out.println("Ingrese Nro: ");
            int sellecionado=0;

            sellecionado= cc.nextInt();

            compra.addAsiento(libres.get(sellecionado));
            libres.get(sellecionado).setReservado(true);
            System.out.println("Desea seleccionar otro asiento Y/N: ");
            seguir= cc.nextLine();
        }

        if (compra.getAsientos().size()>1) {
            for (int i=1; i<= compra.getAsientos().size();i++) {
                System.out.println("Ingrese Nro DNI del ocupante: ");
                int dni=cc.nextInt();
                if (!this.verificarExisteDNI(dni)){
                    Usuario nuevo = new Usuario("",0,dni,"","");
                    System.out.println("Ingrese nombre: ");
                    nuevo.setNombre(cc.nextLine());

                    System.out.println("Ingrese apellido: ");
                    nuevo.setApellido( cc.nextLine());

                    System.out.println("Ingrese mail: ");
                    nuevo.setMail( cc.nextLine());

                    this.userRegistro.agregarUsuario(u);

                    compra.addOcupante(nuevo);
                }
                else{
                    Condicion cond = new CondicionMismoDni(dni);
                    ArrayList<Usuario> arreglo = userRegistro.buscarUsuarioPorCondicion(cond);
                    compra.addOcupante(arreglo.get(0));
                }

            }

        }

        if (!u.tieneTarjeta()){

            TarjetaDeCredito t= new TarjetaDeCredito(u.getNombre(),0,null,0,"","");

            System.out.println("Ingrese Nro Tarjeta: ");
            t.setNumero(cc.nextInt());

            System.out.println("Ingrese cvv: ");
            t.setCvv(cc.nextInt());

            System.out.println("Ingrese Saldo: ");
            t.setSaldo(cc.nextInt());

            System.out.println("Ingrese Banco: ");
            t.setBanco(cc.nextLine());

            System.out.println("Ingrese Marca: ");
            t.setBanco(cc.nextLine());

            u.asociarTarjeta(t);
        }
        return compra;
    	
    }
    */
    
    public void confirmarCompra(String nombreEmpresa, Compra c) {
        //imprimimos por pantalla
        /*System.out.println("Viaje: "+ c.getViaje());
        c.imprimir();
        System.out.println(c.getPasajero().getMetodoPago());
        System.out.println("Desea confirmar la compra? Y/N");
        Scanner ss = new Scanner(System.in);
        String aux = ss.nextLine();
        if(aux.equals("Y")){
            // se descuenta el precio del viaje al usuario
            if(c.getPasajero().getMetodoPago().getSaldo() <= c.getViaje().getPrecio()) {
                c.getPasajero().getMetodoPago().setSaldo(c.getPasajero().getMetodoPago().getSaldo() - c.getViaje().getPrecio());
                EmpresaTransporte e = p.buscarEmpresa(nombreEmpresa);
                e.agregarReserva(c);
                System.out.println("transaccion realizada, pasajes enviado por mail");
            }
            else {
                System.out.println("no se pudo confirmar la compra: saldo insuficiente");
            }
        }
        */
    }
    

}
