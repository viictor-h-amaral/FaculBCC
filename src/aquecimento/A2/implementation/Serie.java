package aquecimento.A2.implementation;

import aquecimento.A2.contracts.Legendavel;
import aquecimento.A2.contracts.Midia;

public class Serie extends Midia implements Legendavel {

    public Serie(String descricao, double precoBase, int temporadas) {
        super(descricao, precoBase);
        setTemporadas(temporadas);
    }

    private int temporadas;
    public int getTemporadas(){
        return temporadas;
    }

    public void setTemporadas(int temporadas){
        if (temporadas <= 0){
            throw new IllegalArgumentException("Quantidade de temporadas não pode ser menor ou igual a zero.");
        }
        this.temporadas = temporadas;
    }

    @Override 
    public double calcularPreco(){
        return this.getPrecoBase() * this.getTemporadas();
    }

    @Override 
    public double calcularCustoLegenda(){
        return calcularPreco() * 0.1;
    }
}
