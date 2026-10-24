package A2.implementation;

import A2.contracts.Midia;

public class Filme extends Midia {

    public Filme(String descricao, double precoBase) {
        super(descricao, precoBase);
    }

    public double calcularPreco(){
        return this.getPrecoBase();
    }
}
