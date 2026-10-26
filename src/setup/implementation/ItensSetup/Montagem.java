package setup.implementation.ItensSetup;

import setup.contracts.ItemSetup;

public class Montagem extends ItemSetup {

    public Montagem(String descricao, double precoBase, int horas) {
        super(descricao, precoBase);

        if (horas <= 0){
            throw new IllegalArgumentException("Quantidade de horas inválida");
        }
        this.horas = horas;
    }

    private int horas;
    public int getHoras(){
        return horas;
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * getHoras();
    }

}
