package implementations.itens_cardapio;

import abstractions.ItemCardapio;

public class Prato extends ItemCardapio{

    private int quantidade;
    public int getQuantidade(){
        return quantidade;
    }

    public Prato(String descricao, double precoBase, int quantidade) {
        super(descricao, precoBase);
        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade de pratos inválida");
        }
        this.quantidade = quantidade;
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * getQuantidade();
    }

}
