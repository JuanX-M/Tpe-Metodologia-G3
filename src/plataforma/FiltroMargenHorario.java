package plataforma;
import java.time.LocalTime;


public class FiltroMargenHorario extends Filtro {

    private LocalTime min;
    private LocalTime max;
    
    public FiltroMargenHorario(LocalTime min, LocalTime max){
        this.min = min;
        this.max = max;
    }

    public boolean cumple(Viaje v){
        // viaje tiene solo una fecha, hay que poner salida y llegada
    	System.out.println(min);
    	System.out.println(max);
        return v.getSalida().isAfter(min) && v.getLlegada().isBefore(max);
    }

    public LocalTime getSalida() {
        return min;
    }

    public LocalTime getLlegada() {
        return max;
    }

}