package implementations;

import contracts.interfaces.Cobravel;

public class AssinaturaClube implements Cobravel {
    
    public AssinaturaClube(Cliente cliente, double valorMensal, int meses) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        else if (valorMensal <= 0) {
            throw new IllegalArgumentException("Valor mensal não pode ser zero ou negativo");
        }
        else if (meses <= 0) {
            throw new IllegalArgumentException("Meses não pode ser zero ou negativo");
        }

        this.cliente = cliente;
        this.valorMensal = valorMensal;
        this.meses = meses;
    }

    private Cliente cliente;

    public Cliente getCliente() {
        return this.cliente;
    }

    private double valorMensal;

    public double getValorMensal() {
        return valorMensal;
    }

    private int meses;

    public int getMeses() {
        return meses;
    }

    @Override
    public double calcularValorCobrar() {
        double valorBase = this.valorMensal * this.meses;
        if (this.meses >= 12) {
            return valorBase * (1 - 0.15); // 15% de desconto
        }
        return valorBase;
    }

}
