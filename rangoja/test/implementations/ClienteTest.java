package implementations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.SaldoInsuficienteException;

@DisplayName("Testes de Cliente")
class ClienteTest {

    private Cliente ana;

    @BeforeEach
    void setUp() {
        ana = new Cliente("Ana", "ana@furb.br", 20);
    }

    @Test
    @DisplayName("Construtor deve rejeitar cadastro com idade menor que zero")
    void clienteComIdadeNegativa_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Cliente("Ana", "ana@furb.br", -1));
    }

    @Test
    @DisplayName("Email com formatação inválida deve ser rejeitado")
    void emailSemArroba_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Cliente("Ana", "ana.furb.br", 20));
    }

    @Test
    @DisplayName("Cliente indicador deve receber cashback de 10 reais")
    void clienteCriadoComIndicacao_DeveAplicarSaldoClienteIndicador() {
        // não há necessidade de armazenar o objeto em uma variável, seu único intuido e disparar o processo que altera o cashback da Ana
        new Cliente("Beto", "beto@furb.br", 17, ana); 
        Assertions.assertEquals(10.0, ana.getSaldoCashback(), 0.001);
    }

    @Test
    @DisplayName("Ao o cliente tentar usar mais saldo de cashback do que possui, exceção é lançada")
    void saldoDeCashbackInsuficiente_DeveSerRejeitado() throws Exception {
        ana.creditarCashback(10.0); //como a ana é recriada a cada Teste, aqui seu saldo estará zerado

        Assertions.assertThrows(SaldoInsuficienteException.class, () -> ana.usarCashback(15.0));
        Assertions.assertEquals(10.0, ana.getSaldoCashback(), 0.001);
    }

    @Test
    @DisplayName("Comparador de clientes é case insensitive")
    void comparadorDeCliente_IgnoraCaseDoEmail() {
        Cliente mesmaAna = new Cliente("Outra Ana", "ANA@FURB.BR", 30);

        Assertions.assertTrue(ana.equals(mesmaAna));
    }

    @Test
    @DisplayName("Construtor de Cliente não aceita idade acima de 120 anos")
    void clienteAcimaDe120Anos_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Cliente("Centenaria", "centenaria@furb.br", 121));
    }

    @Test
    @DisplayName("Usar cashback atualiza corretamente saldo")
    void usarCashbackValido_AtualizaSaldoCorretamente() {
        double cashbackInicial = 10;
        double cashbackUsado = 2;

        ana.creditarCashback(cashbackInicial);
        Assertions.assertDoesNotThrow(() -> ana.usarCashback(cashbackUsado));
        Assertions.assertEquals(cashbackInicial - cashbackUsado, ana.getSaldoCashback(), 0.001);
    }
}
