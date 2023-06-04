import java.time.LocalTime;


public class FiltroMargenHorario extends Filtro {

    private LocalTime salida;
    private LocalTime llegada;
    
    public FiltroMargenHorario(LocalTime salida, LocalTime llegada){
        this.salida = salida;
        this.llegada = llegada;
    }

    public boolean cumple(Viaje v){
        // viaje tiene solo una fecha, hay que poner salida y llegada
        return v.getSalida().isAfter(salida) && v.getLlegada().isBefore(llegada);
    }

    public LocalTime getSalida() {
        return salida;
    }

    public LocalTime getLlegada() {
        return llegada;
    }

}