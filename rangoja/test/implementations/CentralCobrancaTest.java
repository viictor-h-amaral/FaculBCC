package implementations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import implementations.ItensCardapio.Prato;

@DisplayName("Testes de CentralCobranca")
class CentralCobrancaTest {

    private Cliente cliente;
    private CentralCobranca central;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ana", "ana@furb.br", 20);
        central = new CentralCobranca();
    }

    @Test
    @DisplayName("Pedido e assinatura anual registrados devem compor o total a receber")
    void pedidoEAssinaturaAnualRegistrados_DevemComporTotalAReceber() {
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new Prato("Xis", 57.0, 'P'));
        pedido.finalizar();
        AssinaturaClube assinatura = new AssinaturaClube(cliente, 19.90, 12);

        central.registrar(pedido);
        central.registrar(assinatura);

        Assertions.assertEquals(266.98, central.calcularTotalAReceber(), 0.001);
    }

    @Test
    @DisplayName("Cobrança nula ao registrar deve ser rejeitada")
    void cobrancaNulaAoRegistrar_DeveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> central.registrar(null));
    }

    @Test
    @DisplayName("Pedido não finalizado registrado como cobrança deve ser aceito")
    void pedidoNaoFinalizadoRegistradoComoCobranca_DeveSerAceito() {
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new Prato("Xis", 20.0, 'P'));

        central.registrar(pedido);

        Assertions.assertEquals(1, central.getQuantidadeCobrancas());
        Assertions.assertEquals(27.0, central.calcularTotalAReceber(), 0.001);
    }
}
