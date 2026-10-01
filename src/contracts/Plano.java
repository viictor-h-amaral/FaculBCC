package contracts;

public abstract class Plano {
    private final String nome;
    private final int maxDispositivos;

    protected Plano(String nome, int maxDispositivos) {
        this.nome = nome;
        this.maxDispositivos = maxDispositivos;
    }

    public String getNome() {
        return nome;
    }

    public int getMaxDispositivos() {
        return maxDispositivos;
    }

    public abstract boolean temAnuncios();

    public abstract double calcularMensalidade();

    public final String resumo() {
        return nome + ": R$ " + calcularMensalidade()
             + " por mes, " + maxDispositivos + " dispositivo(s)";
    }
}