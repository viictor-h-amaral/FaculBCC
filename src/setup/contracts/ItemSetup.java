package setup.contracts;

public abstract class ItemSetup {

    private static int totalItens = 0;
    public static int getTotalItens(){
        return totalItens;
    }

    public ItemSetup(String descricao, double precoBase){
        if (descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("Descrição não pode estar vazia");
        }
        else if (precoBase <= 0){
            throw new IllegalArgumentException("Preço base inválido");
        }

        this.descricao = descricao;
        this.precoBase = precoBase;
        ItemSetup.totalItens++;
        this.codigo = ItemSetup.getTotalItens();
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
        return codigo + " - " + descricao + " - " + String.format("R$ %.1f", calcularPreco());
    }
}
