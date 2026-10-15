package implementations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes de AssinaturaClube")
class AssinaturaClubeTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ana", "ana@furb.br", 20);
    }

    @Test
    @DisplayName("Assinatura de doze meses deve receber quinze por cento de desconto")
    void assinaturaDeDozeMeses_DeveReceberQuinzePorCentoDeDesconto() {
        AssinaturaClube assinatura = new AssinaturaClube(cliente, 19.90, 12);

        Assertions.assertEquals(202.98, assinatura.calcularValorCobrar(), 0.001);
    }

    @Test
    @DisplayName("Assinatura com menos de doze meses não deve receber desconto")
    void assinaturaComMenosDeDozeMeses_NaoDeveReceberDesconto() {
        AssinaturaClube assinatura = new AssinaturaClube(cliente, 19.90, 11);

        Assertions.assertEquals(218.90, assinatura.calcularValorCobrar(), 0.001);
    }

    @Test
    @DisplayName("Cliente nulo ao criar assinatura deve ser rejeitado")
    void clienteNuloAoCriarAssinatura_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new AssinaturaClube(null, 19.90, 12));
    }

    @Test
    @DisplayName("Valor mensal zero ao criar assinatura deve ser rejeitado")
    void valorMensalZeroAoCriarAssinatura_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new AssinaturaClube(cliente, 0.0, 12));
    }

    @Test
    @DisplayName("Quantidade de meses zero ao criar assinatura deve ser rejeitada")
    void quantidadeDeMesesZeroAoCriarAssinatura_DeveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new AssinaturaClube(cliente, 19.90, 0));
    }

    @Test
    @DisplayName("Assinatura cobrável deve gerar comprovante pelo método padrão")
    void assinaturaCobravel_GeraComprovantePeloMetodoPadrao() {
        AssinaturaClube assinatura = new AssinaturaClube(cliente, 10.0, 1);

        Assertions.assertEquals(String.format("Valor a cobrar: R$ %.2f", 10.0),
                assinatura.gerarComprovante());
    }
}
