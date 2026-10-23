import abstractions.ItemCardapio;
import implementations.Cliente;
import implementations.Pedido;
import implementations.itens_cardapio.Bebida;
import implementations.itens_cardapio.Prato;

public class Principal {

    public static void main(String[] args) {
        var victor = new Cliente("victor", "47912345678");
        var pedidoVictor = new Pedido(victor, "Rua XV de Novembro", 1200);

        ItemCardapio risoto = new Prato("Risoto de cogumelos", 45.0, 2);
        ItemCardapio sucoUva = new Bebida("Suco de uva", 10.0, 6);
        ItemCardapio aguaGas = new Bebida("Água com gás", 5.0, 2);

        pedidoVictor.adicionarItem(risoto);
        pedidoVictor.adicionarItem(sucoUva);
        pedidoVictor.adicionarItem(aguaGas);

        System.out.println("=== Itens do pedido do victor ===");
        for (ItemCardapio item : pedidoVictor.getItens()) {
            System.out.println(item.toString());
        }

        System.out.println("=== Detalhes do pedido do victor ===");
        System.out.println("Rua: " + pedidoVictor.getRuaEntrega());
        System.out.println("Comprador: " + pedidoVictor.getComprador().getNome());
        System.out.println("Valor total: " + pedidoVictor.calcularValorTotal());
        System.out.println("Impostos totais: " + pedidoVictor.calcularTotalImpostos());

        try {
            System.out.println("Tentando adicionar novamente o risoto ao pedido ...");
            pedidoVictor.adicionarItem(risoto);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de argumento! Mensagem: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Erro de estado! Mensagem: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro genérico! Mensagem: " + e.getMessage());
        }
    }
}
