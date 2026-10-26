package setup.implementation;

import java.util.ArrayList;

import setup.contracts.ComGarantia;
import setup.contracts.ItemSetup;

public class Orcamento {

    public Orcamento(Cliente solicitante, String cidade, int prazoDias){
        setSolicitante(solicitante);
        this.entrega = new Entrega(cidade, prazoDias);
    }

    private Cliente solicitante;

    public Cliente getSolicitante(){
        return solicitante;
    }

    public void setSolicitante(Cliente solicitante){
        if (solicitante == null){
            throw new IllegalArgumentException("Solicitante obrigatório");
        }
        this.solicitante = solicitante;
    }

    private Entrega entrega;

    public String getCidadeEntrega(){
        return entrega.getCidade();
    }

    public int getPrazoEntrega(){
        return entrega.getPrazoDias();
    }

    private ArrayList<ItemSetup> itens = new ArrayList<>();

    public ArrayList<ItemSetup> getItens(){
        return itens;
    }

    public void adicionarItem(ItemSetup item){

        if (item == null){
            throw new IllegalArgumentException("Item inválido");
        }
        else if (itens.contains(item)){
            throw new IllegalStateException("Item já incluído no orçamento");
        }
        else if (itens.size() > 4){
            throw new IllegalStateException("Orçamento lotado");
        }
        itens.add(item);
    }

    public void removerItem(ItemSetup item){
        if (!itens.contains(item)){
            throw new IllegalStateException("Item não encontrado no orçamento");
        }
        itens.remove(item);
    }

    public double calcularValorTotal(){
        double total = 0;
        for (ItemSetup item : getItens()) {
            total += item.calcularPreco();
        }
        return total;
    }

    public double calcularTotalGarantia(){
        double total = 0;
        for (ItemSetup item : getItens()) {
            // O CAST DEVE SER PARA A INTERFACE POIS NÃO FAZ SENTIDO SER PARA A CLASSE Peca. POR MAIS QUE ELA (Peca) IMPLEMENTE 
            // A INTERFACE, É A INTERFACE QUE CONTÉM A IDEIA/CONTRATO QUE DEFINE O MÉTODO/FUNCIONALIDADE calcularGarantiaEstendida().
            // PODEM HAVER OUTRAS CLASSES IMPLEMENTANDO A MESMA INTERFACE AMANHÃ
            if (item instanceof ComGarantia)
                total += ((ComGarantia)item).calcularGarantiaEstendida();
        }
        return total;
    }

}
