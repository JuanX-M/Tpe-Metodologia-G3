import java.time.LocalDate;

public class TarjetaDeCredito {
    private String titular;
    private long numero;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private int cvv;
    private double saldo;
    private String banco;
    private String marca;

    public String getTitular() {
        return titular;
    }

    public long getNumero() {
        return numero;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
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
        this.numero = numero;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
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
}
