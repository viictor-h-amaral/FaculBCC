package implementations.ItensCardapio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes de Prato")
class PratoTest {

    private Prato xisMedio;

    @BeforeEach
    void setUp() {
        xisMedio = new Prato("Xis", 30.0, 'M');
    }

    @Test
    @DisplayName("Prato com tamanho não suportado deve ser rejeitado")
    void pratoComTamanhoNaoSuportado_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Prato("Xis", 30.0, 'X'));
    }

    @Test
    @DisplayName("Prato médio com desconto de dez por cento deve calcular preço corretamente")
    void pratoMedioComDescontoDeDezPorCento_DeveCalcularPrecoCorretamente() {
        Assertions.assertEquals(35.1, xisMedio.calcularPrecoComDesconto(10), 0.001);
    }

    @Test
    @DisplayName("Desconto acima de cem por cento deve ser rejeitado")
    void descontoAcimaDeCemPorCento_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> xisMedio.calcularPrecoComDesconto(120));
    }

    @Test
    @DisplayName("Prato pequeno deve custar o preço base")
    void pratoPequeno_DeveCustarPrecoBase() {
        Assertions.assertEquals(30.0, new Prato("Xis", 30.0, 'P').calcularPreco(), 0.001);
    }

    @Test
    @DisplayName("Prato grande deve ter acréscimo de sessenta por cento")
    void pratoGrande_DeveTerAcrescimoDeSessentaPorCento() {
        Assertions.assertEquals(48.0, new Prato("Xis", 30.0, 'G').calcularPreco(), 0.001);
    }

    @Test
    @DisplayName("Descontos de zero e cem por cento devem ser aceitos")
    void descontosDeZeroECemPorCento_DevemSerAceitos() {
        Assertions.assertEquals(39.0, xisMedio.calcularPrecoComDesconto(0), 0.001);
        Assertions.assertEquals(0.0, xisMedio.calcularPrecoComDesconto(100), 0.001);
    }
}
