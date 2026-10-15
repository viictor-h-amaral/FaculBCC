package implementations.ItensCardapio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes de Bebida")
class BebidaTest {

    private Bebida chopp;

    @BeforeEach
    void setUp() {
        chopp = new Bebida("Chopp", 12.0, 500, true);
    }

    @Test
    @DisplayName("Bebida alcoólica deve ter acréscimo de três reais")
    void bebidaAlcoolica_DeveTerAcrescimoDeTresReais() {
        Assertions.assertEquals(15.0, chopp.calcularPreco(), 0.001);
    }

    @Test
    @DisplayName("Bebida não alcoólica deve custar o preço base")
    void bebidaNaoAlcoolica_DeveCustarPrecoBase() {
        Bebida refri = new Bebida("Refri", 6.0, 350, false);

        Assertions.assertEquals(6.0, refri.calcularPreco(), 0.001);
    }

    @Test
    @DisplayName("Bebida com volume zero deve ser rejeitada")
    void bebidaComVolumeZero_DeveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Bebida("Refri", 6.0, 0, false));
    }

    @Test
    @DisplayName("Bebida alcoólica ao exibir texto deve incluir volume e identificação")
    void bebidaAlcoolicaAoExibirTexto_DeveIncluirVolumeEIdentificacao() {
        Assertions.assertTrue(chopp.toString().contains("(500 ml) [alcoólica]"));
    }
}
