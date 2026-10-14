package implementations.ItensCardapio;

import contracts.abstractions.ItemCardapio;

public class Prato extends ItemCardapio {
    
    public Prato(String nome, double precoBase, char tamanho){
        super(nome, precoBase);
        tamanho = Character.toUpperCase(tamanho);
        if (tamanho != 'P' && tamanho != 'M' && tamanho != 'G') {
            throw new IllegalArgumentException("Tamanho inválido! Escolha 'P', 'M' ou 'G'");
        }

        this.tamanho = tamanho;
    }

    private char tamanho;

    public char getTamanho() {
        return tamanho;
    }

    public double calcularPreco(){
        switch (tamanho){
            case 'P':
                return getPrecoBase();
            case 'M':
                return getPrecoBase() * 1.3;
            case 'G':
                return getPrecoBase() * 1.6;
            default:
                throw new IllegalArgumentException("Tamanho inválido! Use 'P', 'M' ou 'G'");
        }
    }

    @Override 
    public String toString() {
        return super.toString() + String.format(" (tamanho: %c)", this.tamanho);
    }
}
