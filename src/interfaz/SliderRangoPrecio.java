package interfaz;
import java.time.LocalTime;


public class SliderRangoPrecio extends SliderRango {

    public SliderRangoPrecio(double valorMinimo, double valorMaximo, double escala) {
        super(valorMinimo, valorMaximo, escala);
    }

    @Override
    protected String formatearValor(int valor) {
        return String.valueOf(valor / escala);
    }
}