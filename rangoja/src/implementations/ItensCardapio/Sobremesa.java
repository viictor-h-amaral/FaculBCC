package implementations.ItensCardapio;

import contracts.abstractions.ItemCardapio;

public class Sobremesa extends ItemCardapio {
    
    public Sobremesa(String nome, double precoPor100g, int pesoGramas){
        super(nome, precoPor100g);
        validarPositivo(pesoGramas);
        this.pesoGramas = pesoGramas;
    }

    private int pesoGramas;

    public int getPesoGramas() {
        return pesoGramas;
    }

    @Override
    public double calcularPreco(){
        double preco = ( getPrecoBase() / 100.0 ) * pesoGramas;
        return preco;
    }

    @Override 
    public String toString() {
        return super.toString() + String.format(" (%d g)", this.pesoGramas);
    }

}
