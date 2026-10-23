package implementations.itens_cardapio;

import abstractions.ItemCardapio;
import interfaces.Tributavel;

public class Bebida extends ItemCardapio implements Tributavel {

    private int unidades;
    public int getUnidades(){
        return unidades;
    }
    
    public Bebida(String descricao, double precoBase, int unidades) {
        super(descricao, precoBase);
        if (unidades <= 0){
            throw new IllegalArgumentException("Quantidade de unidades inválida");
        }
        this.unidades = unidades;
    }

    @Override
    public double calcularPreco() {
        if (getUnidades() >= 6){
            return getPrecoBase() * getUnidades() * (1 - 0.15);
        }
        return getPrecoBase() * getUnidades();
    }

    @Override
    public double calcularImposto() {
        return calcularPreco() * 0.12;
    }

}
