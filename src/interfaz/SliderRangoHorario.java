package interfaz;
import java.time.LocalTime;


public class SliderRangoHorario extends SliderRango {

    public SliderRangoHorario() {
        super(0, 24 * 60 - 1, 1);
    }

    @Override
    protected String formatearValor(int valor) {
        int horas = valor / 60;
        int minutos = valor % 60;
        return LocalTime.of(horas, minutos).toString();
    }
}