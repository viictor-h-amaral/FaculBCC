package setup.implementation.ItensSetup;

import setup.contracts.ComGarantia;
import setup.contracts.ItemSetup;

public class Peca extends ItemSetup implements ComGarantia {

    public Peca(String descricao, double precoBase, int quantidade) {
        super(descricao, precoBase);

        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade inválida");
        }

        this.quantidade = quantidade;
    }

    private int quantidade;
    public int getQuantidade(){
        return quantidade;
    }

    @Override
    public double calcularPreco() {
        if (getQuantidade() >= 3){
            return getPrecoBase() * getQuantidade() * 0.9;
        }
        return getPrecoBase() * getQuantidade();
    }

    @Override
    public double calcularGarantiaEstendida() {
        return calcularPreco() * 0.15;
    }

}
