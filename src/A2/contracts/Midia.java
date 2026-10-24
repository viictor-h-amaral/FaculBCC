package A2.contracts;

public abstract class Midia {

    public Midia(String descricao, double precoBase){
        setDescricao(descricao);
        setPrecoBase(precoBase);
    }

    private String descricao;
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        if (descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("Descrição não pode estar vazia");
        }  
        this.descricao = descricao;
    }

    private double precoBase;
    public double getPrecoBase(){
        return precoBase;
    }
    public void setPrecoBase(double precoBase){
        if (precoBase <= 0){
            throw new IllegalArgumentException("Preço Base não pode ser menor ou igual a zero.");
        }
        this.precoBase = precoBase;
    }

    public abstract double calcularPreco();
}
