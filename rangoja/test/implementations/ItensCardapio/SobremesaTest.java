package implementations.ItensCardapio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes de Sobremesa")
class SobremesaTest {

    private Sobremesa pudim;

    @BeforeEach
    void setUp() {
        pudim = new Sobremesa("Pudim", 8.0, 150);
    }

    @Test
    @DisplayName("Sobremesa de cento e cinquenta gramas deve ter preço proporcional")
    void sobremesaDeCentoECinquentaGramas_DeveTerPrecoProporcional() {
        Assertions.assertEquals(12.0, pudim.calcularPreco(), 0.001);
    }

    @Test
    @DisplayName("Sobremesa com peso zero deve ser rejeitada")
    void sobremesaComPesoZero_DeveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Sobremesa("Pudim", 8.0, 0));
    }

    @Test
    @DisplayName("Sobremesa ao exibir texto deve incluir peso e unidade")
    void sobremesaAoExibirTexto_DeveIncluirPesoEUnidade() {
        Assertions.assertTrue(pudim.toString().contains("150 g") || pudim.toString().contains("150g"));
    }
}
