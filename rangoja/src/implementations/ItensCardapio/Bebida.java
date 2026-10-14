package implementations.ItensCardapio;

import contracts.abstractions.ItemCardapio;

public class Bebida extends ItemCardapio {
    
    public Bebida(String nome, double precoBase, int volumeMl, boolean alcoolica){
        super(nome, precoBase);
        ValidarPositivo(volumeMl);
        this.volumeMl = volumeMl;
        this.alcoolica = alcoolica;
    }

    private int volumeMl;

    public int getVolumeMl() {
        return volumeMl;
    }

    private boolean alcoolica;

    public boolean isAlcoolica() {
        return alcoolica;
    }

    @Override
    public double calcularPreco(){
        double preco = getPrecoBase();
        if (alcoolica){
            preco += 3.0;
        }
        return preco;
    }

    @Override 
    public String toString() {
        return super.toString() 
            + String.format(" (%dml)", this.volumeMl)
            + (this.alcoolica ? " [alcoólica]" : "");
    }

}
