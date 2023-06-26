package plataforma;

import java.time.LocalDate;

public class TarjetaDeCredito {
    private String titular;
    private long numero;
    private LocalDate fechaVencimiento;
    private int cvv;
    private double saldo;
    private String banco;
    private String marca;

    public TarjetaDeCredito(String titular, long numero, LocalDate fechaVencimiento, int cvv,
                            String banco, String marca) {
        if (!validarNumero(numero)) {
            throw new IllegalArgumentException("El número de tarjeta debe tener 16 dígitos");
        }

        if (!validarFechaVencimiento(fechaVencimiento)) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha actual");
        }

        this.titular = titular;
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        //this.saldo = saldo;
        this.banco = banco;
        this.marca = marca;
    }

    public String getTitular() {
        return titular;
    }

    public long getNumero() {
        return numero;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCvv() {
        return cvv;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getBanco() {
        return banco;
    }

    public String getMarca() {
        return marca;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setNumero(long numero) {
        if (!validarNumero(numero)) {
            throw new IllegalArgumentException("El número de tarjeta debe tener 16 dígitos");
        }
        this.numero = numero;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        if (!validarFechaVencimiento(fechaVencimiento)) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha actual");
        }
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    private boolean validarNumero(long numero) {
        String numeroStr = String.valueOf(numero);
        return numeroStr.length() == 16;
    }

    private boolean validarFechaVencimiento(LocalDate fechaVencimiento) {
        LocalDate fechaActual = LocalDate.now();
        return !fechaVencimiento.isBefore(fechaActual);
    }
}
