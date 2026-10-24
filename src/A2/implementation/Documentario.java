package A2.implementation;

import A2.contracts.Legendavel;
import A2.contracts.Midia;

// ITEM b) CRIAÇÃOD A CLASSE Documentario
public class Documentario extends Midia implements Legendavel {
    
    public Documentario(String descricao, double precoBase) {
        super(descricao, precoBase);
    }
    
    @Override
    public double calcularPreco() {
        return getPrecoBase();
    }
    
    @Override
    public double calcularCustoLegenda() {
        return calcularPreco() * 0.10;
    }
}