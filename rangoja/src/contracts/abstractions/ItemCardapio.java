package contracts.abstractions;

public abstract class ItemCardapio {
    
    public ItemCardapio(String nome, double precoBase){
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        ValidarPositivo(precoBase);
        this.nome = nome;
        this.precoBase = precoBase;
    }

    protected void ValidarPositivo(double valor){
        if (valor < 0){
            throw new IllegalArgumentException("Valor informado é negativo!");
        }
    }

    private String nome;
    
    public String getNome() {
        return nome;
    }

    private double precoBase;

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        ValidarPositivo(precoBase);
        this.precoBase = precoBase;
    }

    public abstract double calcularPreco();

    public final double calcularPrecoComDesconto(double percentual){
        ValidarPositivo(percentual);
        if (percentual > 100){
            throw new IllegalArgumentException("Percentual de desconto não pode ser maior que 100%");
        }
        double percentualComplementar = 100 - percentual;
        return calcularPreco() * (percentualComplementar / 100);
    }

    @Override 
    public String toString() {
        return String.format("%s - R$ %.2f", this.nome, this.calcularPreco());
    }
}
