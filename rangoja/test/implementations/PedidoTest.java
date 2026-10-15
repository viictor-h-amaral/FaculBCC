package implementations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import implementations.ItensCardapio.Bebida;
import implementations.ItensCardapio.Prato;
import implementations.ItensCardapio.Sobremesa;

@DisplayName("Testes de Pedido")
class PedidoTest {

    private Cliente ana;
    private Cliente beto;
    private Prato xisMedio;
    private Bebida refri;
    private Sobremesa pudim;

    @BeforeEach
    void setUp() {
        ana = new Cliente("Ana", "ana@furb.br", 20);
        beto = new Cliente("Beto", "beto@furb.br", 17);
        xisMedio = new Prato("Xis Salada", 30.0, 'M');
        refri = new Bebida("Refri", 6.0, 350, false);
        pudim = new Sobremesa("Pudim", 8.0, 150);
    }

    @Test
    @DisplayName("Pedido com itens variados deve calcular subtotal, taxa e total")
    void pedidoComItensVariados_DeveCalcularSubtotalTaxaETotal() {
        Pedido pedido = new Pedido(ana);
        pedido.adicionarItem(xisMedio);
        pedido.adicionarItem(refri);
        pedido.adicionarItem(pudim);

        Assertions.assertEquals(57.0, pedido.calcularSubtotal(), 0.001);
        Assertions.assertEquals(7.0, pedido.calcularTaxaEntrega(), 0.001);
        Assertions.assertEquals(64.0, pedido.calcularValorCobrar(), 0.001);
    }

    @Test
    @DisplayName("Pedido acima do limite deve ter frete grátis")
    void pedidoAcimaDoLimite_DeveTerFreteGratis() {
        Pedido pedido = new Pedido(ana);
        pedido.adicionarItem(new Prato("Xis", 40.0, 'G'), 2);

        Assertions.assertEquals(128.0, pedido.calcularSubtotal(), 0.001);
        Assertions.assertEquals(0.0, pedido.calcularTaxaEntrega(), 0.001);
        Assertions.assertEquals(128.0, pedido.calcularValorCobrar(), 0.001);
    }

    @Test
    @DisplayName("Menor de idade que adiciona bebida alcoólica deve receber exceção")
    void menorDeIdadeAdicionandoBebidaAlcoolica_DeveReceberExcecao() {
        Pedido pedido = new Pedido(beto);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> pedido.adicionarItem(new Bebida("Chopp", 12.0, 500, true)));
    }

    @Test
    @DisplayName("Quantidade zero deve ser rejeitada")
    void quantidadeZero_DeveSerRejeitada() {
        Pedido pedido = new Pedido(ana);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> pedido.adicionarItem(xisMedio, 0));
    }

    @Test
    @DisplayName("Pedido vazio ao finalizar deve receber exceção e permanecer aberto")
    void pedidoVazioAoFinalizar_DeveReceberExcecaoEContinuarAberto() {
        Pedido pedido = new Pedido(ana);

        Assertions.assertThrows(IllegalStateException.class, pedido::finalizar);
        Assertions.assertFalse(pedido.isFinalizado());
    }

    @Test
    @DisplayName("Pedido finalizado deve creditar cinco por cento do subtotal")
    void pedidoFinalizado_DeveCreditarCincoPorCentoDoSubtotal() {
        Pedido pedido = new Pedido(ana);
        pedido.adicionarItem(xisMedio);
        pedido.adicionarItem(refri);
        pedido.adicionarItem(pudim);

        pedido.finalizar();

        Assertions.assertEquals(2.85, ana.getSaldoCashback(), 0.001);
        Assertions.assertTrue(pedido.isFinalizado());
    }

    @Test
    @DisplayName("Pedidos criados em sequência devem receber números consecutivos")
    void pedidosCriadosEmSequencia_DevemReceberNumerosConsecutivos() {
        Pedido primeiro = new Pedido(ana);
        Pedido segundo = new Pedido(beto);

        Assertions.assertEquals(primeiro.getNumero() + 1, segundo.getNumero());
    }

    @Test
    @DisplayName("Pedido finalizado ao adicionar item deve receber exceção")
    void pedidoFinalizadoAoAdicionarItem_DeveReceberExcecao() {
        Pedido pedido = new Pedido(ana);
        pedido.adicionarItem(xisMedio);
        pedido.finalizar();

        Assertions.assertThrows(IllegalStateException.class, () -> pedido.adicionarItem(refri));
    }

    @Test
    @DisplayName("Item nulo ao adicionar no pedido deve ser rejeitado")
    void itemNuloAoAdicionarNoPedido_DeveSerRejeitado() {
        Pedido pedido = new Pedido(ana);

        Assertions.assertThrows(IllegalArgumentException.class, () -> pedido.adicionarItem(null));
    }
}
