package implementations;

import java.util.ArrayList;

import abstractions.ItemCardapio;
import interfaces.Tributavel;

public class Pedido {

    public Pedido(Cliente comprador, String rua, int numero){
        setComprador(comprador);
        this.entrega = new Endereco(rua, numero);
    }

    private Cliente comprador;

    public Cliente getComprador(){
        return comprador;
    }

    public void setComprador(Cliente comprador){
        if (comprador == null){
            throw new IllegalArgumentException("Comprador obrigatório");
        }
        this.comprador = comprador;
    }

    private Endereco entrega;

    public String getRuaEntrega(){
        return entrega.getRua();
    }

    public int getNumeroEntrega(){
        return entrega.getNumero();
    }

    private ArrayList<ItemCardapio> itens = new ArrayList<>();

    public ArrayList<ItemCardapio> getItens(){
        return itens;
    }

    public void adicionarItem(ItemCardapio i){
        if (i == null){
            throw new IllegalArgumentException("Item inválido");
        }
        else if (itens.contains(i)){
            throw new IllegalStateException("Item já incluído no pedido");
        }
        else if (itens.size() >= 8){
            throw new IllegalStateException("Pedido lotado");
        }

        itens.add(i);
    }

    public void removerItem(ItemCardapio i){
        if (!itens.contains(i)){
            throw new IllegalStateException("Item não encontrado no pedido");
        }
        itens.remove(i);
    }


    public double calcularValorTotal(){
        double total = 0;
        for (ItemCardapio item : getItens()){
            total += item.calcularPreco();
        }
        return total;
    }

    public double calcularTotalImpostos(){
        double total = 0;
        for (ItemCardapio item : getItens()){
            if(item instanceof Tributavel){
                total += ((Tributavel)item).calcularImposto();
            }
        }
        return total;
    }
}
