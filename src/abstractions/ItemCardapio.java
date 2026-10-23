package abstractions;

public abstract class ItemCardapio {

    private static int totalItens = 0;

    public static int getTotalItens(){
        return totalItens;
    }

    public ItemCardapio(String descricao, double precoBase){
        if (precoBase <= 0){
            throw new IllegalArgumentException("Preço base inválido");
        }

        this.descricao = descricao;
        this.precoBase = precoBase;

        totalItens++;
        this.codigo = totalItens;
    }

    private int codigo;
    public int getCodigo(){
        return codigo;
    }

    private String descricao;
    public String getDescricao(){
        return descricao;
    }

    private double precoBase;
    public double getPrecoBase(){
        return precoBase;
    }

    public abstract double calcularPreco();
 
    @Override
    public String toString(){
        return codigo + " - " + descricao + " - " + String.format("R$ %.2f", this.calcularPreco());
    }
}
