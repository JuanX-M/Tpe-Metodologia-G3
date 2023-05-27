import java.time.LocalDateTime;


public class FiltroMargenHorario extends Filtro {

    private LocalDateTime salida;
    private LocalDateTime llegada;
    
    public FiltroMargenHorario(LocalDateTime salida, LocalDateTime llegada){
        this.salida = salida;
        this.llegada = llegada;
    }

    public boolean cumple(Viaje v){
        // viaje tiene solo una fecha, hay que poner salida y llegada
        return v.getSalida.isAfter(salida) && v.getllegada.isBefore(llegada);
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public LocalDateTime getLlegada() {
        return llegada;
    }

}