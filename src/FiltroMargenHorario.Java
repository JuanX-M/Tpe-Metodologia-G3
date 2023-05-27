import java.util.LocalDateTime;

public class FiltroMargenHorario extends Filtro {

    private LocalDateTime salida;
    private LocalDateTime llegada;
    
    public FiltroMargenHorario(LocalDate salida, LocalDate llegada){
        this.salida = salida;
        this.llegada = llegada;
    }

    public boolean cumple(Viaje v){
        // viaje tiene solo una fecha, hay que poner salida y llegada
        return v.getSalida.isAfter(salida) && v.getllegada.isBefore(llegada);
    }

}