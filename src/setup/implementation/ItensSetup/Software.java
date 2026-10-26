package setup.implementation.ItensSetup;

import setup.contracts.ComGarantia;
import setup.contracts.ItemSetup;

public class Software extends ItemSetup implements ComGarantia {

    public Software(String descricao, double precoBase, int licencas) {
        super(descricao, precoBase);

        if (licencas <= 0){
            throw new IllegalArgumentException("Quantidade inválida");
        }

        this.licencas = licencas;
    }

    private int licencas;
    public int getLicencas(){
        return licencas;
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * getLicencas();
    }

    @Override
    public double calcularGarantiaEstendida() {
        return calcularPreco() * 0.15;
    }

}
