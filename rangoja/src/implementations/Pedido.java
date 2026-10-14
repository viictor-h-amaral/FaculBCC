package implementations;

import java.util.ArrayList;

import contracts.abstractions.ItemCardapio;
import contracts.interfaces.Cobravel;
import implementations.ItensCardapio.Bebida;

public class Pedido implements Cobravel {

    public static double TAXA_ENTREGA = 7.0;
    public static double VALOR_FRETE_GRATIS = 100.0;
    private static int proximoNumero = 1;
    public static int getTotalPedidosCriados(){
        return proximoNumero - 1;
    }

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        this.cliente = cliente;
        this.numero = proximoNumero;
        proximoNumero++;
        this.finalizado = false;
    }

    private int numero;

    public int getNumero() {
        return numero;
    }

    private boolean finalizado;

    public boolean isFinalizado() {
        return finalizado;
    }
    
    public void finalizar() {
        if (this.isFinalizado()){
            throw new IllegalStateException("Pedido já finalizado");
        }
        else if (itens.isEmpty()) {
            throw new IllegalStateException("Não é possível finalizar um pedido sem itens");
        }

        this.finalizado = true;
        cliente.creditarCashback(calcularSubtotal() * 0.05);
    }

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    private ArrayList<ItemCardapio> itens = new ArrayList<>();

    public void adicionarItem(ItemCardapio item) {
        this.adicionarItem(item, 1);
    }

    public void adicionarItem(ItemCardapio item, int quantidade) {
        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade de itens deve ser maior que zero");
        }
        else if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        else if (this.isFinalizado()){
            throw new IllegalStateException("Pedido já finalizado, não é possível adicionar itens");
        }
        else if (item instanceof Bebida && ((Bebida)item).isAlcoolica() && !this.cliente.isMaiorDeIdade()) {
            throw new IllegalArgumentException("Cliente menor de idade não pode adicionar bebidas alcoólicas");
        }

        for (int i = 1; i <= quantidade; i++) {
            itens.add(item);
        }
    }

    public int getQuantidadeItens() {
        return itens.size();
    }

    public double calcularSubtotal(){
        double subtotal = 0;
        for (ItemCardapio item : itens) {
            subtotal += item.calcularPreco();
        }
        return subtotal;
    }

    public double calcularTaxaEntrega() {
        if (calcularSubtotal() >= VALOR_FRETE_GRATIS) {
            return 0;
        }
        return TAXA_ENTREGA;
    }

    @Override
    public double calcularValorCobrar(){
        return calcularSubtotal() + calcularTaxaEntrega();
    }

}
