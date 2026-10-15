package contracts.abstractions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import implementations.ItensCardapio.Prato;

@DisplayName("Testes de ItemCardapio")
class ItemCardapioTest {

    private ItemCardapio item;

    @BeforeEach
    void setUp() {
        item = new Prato("Xis", 30.0, 'P');
    }

    @Test
    @DisplayName("Item com nome nulo ou vazio deve ser rejeitado")
    void itemComNomeNuloOuVazio_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Prato(null, 30.0, 'P'));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Prato("", 30.0, 'P'));
    }

    @Test
    @DisplayName("Item com preço base zero deve ser rejeitado")
    void itemComPrecoBaseZero_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Prato("Xis", 0.0, 'P'));
    }

    @Test
    @DisplayName("Preço base positivo ao atualizar item deve ser aplicado")
    void precoBasePositivoAoAtualizarItem_DeveSerAplicado() {
        item.setPrecoBase(35.0);

        Assertions.assertEquals(35.0, item.getPrecoBase(), 0.001);
        Assertions.assertEquals(35.0, item.calcularPreco(), 0.001);
    }

    @Test
    @DisplayName("Preço base inválido ao atualizar item deve manter o valor anterior")
    void precoBaseInvalidoAoAtualizarItem_DeveManterValorAnterior() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> item.setPrecoBase(0.0));

        Assertions.assertEquals(30.0, item.getPrecoBase(), 0.001);
    }
}
