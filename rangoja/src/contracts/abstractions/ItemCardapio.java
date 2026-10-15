package contracts.abstractions;

public abstract class ItemCardapio {
    
    public ItemCardapio(String nome, double precoBase){
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        validarPositivo(precoBase);
        this.nome = nome;
        this.precoBase = precoBase;
    }

    protected void validarPositivo(double valor){
        if (valor <= 0){
            throw new IllegalArgumentException("Valor informado é negativo ou zero!");
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
        validarPositivo(precoBase);
        this.precoBase = precoBase;
    }

    public abstract double calcularPreco();

    public final double calcularPrecoComDesconto(double percentual){
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual de desconto deve estar entre 0% e 100%");
        }
        double percentualComplementar = 100 - percentual;
        return calcularPreco() * (percentualComplementar / 100);
    }

    @Override 
    public String toString() {
        return String.format("%s - R$ %.2f", this.nome, this.calcularPreco());
    }
}
