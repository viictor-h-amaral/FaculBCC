package implementations;

import java.util.ArrayList;
import contracts.interfaces.Cobravel;

public class CentralCobranca {

    private ArrayList<Cobravel> cobrancas = new ArrayList<>();

    public void registrar(Cobravel cobranca){
        if (cobranca == null) {
            throw new IllegalArgumentException("Cobrança não pode ser nula");
        }

        cobrancas.add(cobranca);
    }

    public double calcularTotalAReceber(){
        double totalReceber = 0;
        for (Cobravel cobranca : cobrancas){
            totalReceber += cobranca.calcularValorCobrar();
        }
        return totalReceber;
    }

    public int getQuantidadeCobrancas(){
        return cobrancas.size();
    }

    public String gerarRelatorio(){
        String relatorio = "";
        for (Cobravel cobranca : cobrancas){
            relatorio += cobranca.gerarComprovante() + "\n";
        }
        relatorio += String.format("Total a receber: R$ %.2f", this.calcularTotalAReceber());
        return relatorio;
    }

}
