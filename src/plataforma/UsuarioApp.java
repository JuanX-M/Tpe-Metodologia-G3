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
=======
    public Compra seleccionar(Usuario u,ArrayList <Asiento> a, Viaje v) {
>>>>>>> ea0a021a5671c0664db0ce2cde8b911419c3da0e
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

        if (compra.getAsientos().size()>1) {//si selecciono mas de un asiento, debe ingresar los datos del ocupante
            for (int i=1; i<= compra.getAsientos().size();i++) {
                System.out.println("Ingrese Nro DNI del ocupante: ");
                int dni=cc.nextInt();
                if (!this.verificarExisteDNI(dni)){
                    Usuario nuevo = null;

                    try {//esto se agrego para evitar un error
                        nuevo = new Usuario("","",dni,"","");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("Ingrese nombre: ");
                    nuevo.setNombre(cc.nextLine());

                    System.out.println("Ingrese apellido: ");
                    nuevo.setApellido( cc.nextLine());

                    System.out.println("Ingrese mail: ");
                    nuevo.setMail( cc.nextLine());

                    try {//esto se agrego para evitar un error
                        this.userRegistro.agregarUsuario(u);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

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
    
    public void confirmarCompra(String nombreEmpresa, Compra c) throws Exception {
    	Usuario comprador = c.getComprador();
    	TarjetaDeCredito tarjeta = comprador.getMetodoPago();
    	double saldo = tarjeta.getSaldo();
    	double precio = c.getPrecio();
    	if (saldo < precio)
    		throw new Exception("El saldo es insuficiente");

        tarjeta.setSaldo(saldo - precio);
        EmpresaTransporte e = p.buscarEmpresa(nombreEmpresa);
        e.agregarReserva(c, comprador);
        comprador.registrarCompra(c);
    }
}
