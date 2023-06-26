package interfaz;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public abstract class SliderRango extends JPanel {
    protected JSlider sliderInicio;
    protected JSlider sliderFin;
    protected JLabel etiquetaRango;
    protected double escala;

    public SliderRango(double valorMinimo, double valorMaximo, double escala) {
        setLayout(new BorderLayout());

        assert 0 < escala && escala <= 100;

        this.escala = escala;

        int valorMinimoEscala = (int) (valorMinimo * escala);
        int valorMaximoEscala = (int) (valorMaximo * escala);
        int valorInicioEscala = (int) (valorMinimo * escala);
        int valorFinEscala = (int) (valorMaximo * escala);

        sliderInicio = new JSlider(JSlider.HORIZONTAL, valorMinimoEscala, valorMaximoEscala, valorInicioEscala);
        sliderFin = new JSlider(JSlider.HORIZONTAL, valorMinimoEscala, valorMaximoEscala, valorFinEscala);

        sliderInicio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sliderInicio.getValue() > sliderFin.getValue()) {
                    sliderInicio.setValue(sliderFin.getValue());
                }
                actualizarEtiquetaRango();
            }
        });

        sliderFin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sliderFin.getValue() < sliderInicio.getValue()) {
                    sliderFin.setValue(sliderInicio.getValue());
                }
                actualizarEtiquetaRango();
            }
        });

        etiquetaRango = new JLabel();
        actualizarEtiquetaRango();

        JPanel panelSliders = new JPanel(new GridLayout(2, 1));
        panelSliders.add(sliderInicio);
        panelSliders.add(sliderFin);

        add(panelSliders, BorderLayout.CENTER);
        add(etiquetaRango, BorderLayout.SOUTH);
    }

    protected abstract String formatearValor(int valor);

    protected void actualizarEtiquetaRango() {
        String valorInicio = formatearValor(sliderInicio.getValue());
        String valorFin = formatearValor(sliderFin.getValue());
        etiquetaRango.setText("Rango Seleccionado: " + valorInicio + " - " + valorFin);
    }

    public JSlider obtenerSliderInicio() {
        return sliderInicio;
    }

    public JSlider obtenerSliderFin() {
        return sliderInicio;
    }

    public String getValorInicio() {
        return formatearValor(sliderInicio.getValue());
    }

    public String getValorFin() {
        return formatearValor(sliderFin.getValue());
    }
}
