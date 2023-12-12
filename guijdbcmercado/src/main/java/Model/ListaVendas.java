package Model;

public class ListaVendas {
    // atributos
    String dataHora;
    double valor;
    boolean vip;

    // Contructor
    public ListaVendas(String dataHora, double valor, boolean vip) {
        this.dataHora = dataHora;
        this.valor = valor;
        this.vip = vip;
    }
    // Getters and Setters
    public String getDataHora() {
        return dataHora;
    }
    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public boolean getVip() {
        return vip;
    }
    public void setVip(boolean vip) {
        this.vip = vip;
    }
}