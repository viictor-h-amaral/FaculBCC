import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

/**
 * Classe de teste da avaliação prática de POO - Turma 004.
 */
class ProdutoTest {

    // ===== EXEMPLO FORNECIDO PELO PROFESSOR =====
    @Test
    void deveAdicionarQuantidadeValida() {
        Produto p = new Produto(1, "Caneta");
        p.adicionar(50);
        assertEquals(50, p.getQuantidade());
    }

    // ===== IMPLEMENTE SEUS TESTES A PARTIR DAQUI (Questão 5) =====

    // ===== PRODUTO.REMOVER() =====

    @Test 
    public void removerQuantidadeNegativaDeveLancarExecao(){
        Produto p = new Produto(2, "Caneta BIC", 10);
        assertThrows(IllegalArgumentException.class, () -> p.remover(-1));
    }

    @Test 
    public void removerQuantidadeMaiorQueEstoqueDeveLancarExcecao(){
        Produto p = new Produto(3, "Lapis 2B", 10);
        assertThrows(IllegalStateException.class, () -> p.remover(11));
    }

    @Test 
    public void removerQuantidadePositivaEMenorQueEmEstoqueDeveRemoverCorretamente(){
        Produto p = new Produto(3, "Lapis 2B", 10);
        p.remover(4);
        assertEquals(10-4, p.getQuantidade());
    }

    // ===== PRODUTO.REMOVER() ===== (fim)
    
    // ===== PRODUTO() =====

    @Test 
    public void criarProdutoComQuantidadeNegativaDeveLancarExcecao(){
        assertThrows(IllegalArgumentException.class, () -> new Produto(4, "invalido", -10));
    }

    @Test 
    public void criarProdutoComParametrosValidosDeveCriarProduto(){
        var produtoValido = new Produto(5, "valido", 150);
        assertNotNull(produtoValido);
        assertEquals(5, produtoValido.getCodigo());
        assertEquals("valido", produtoValido.getNome());
        assertEquals(150, produtoValido.getQuantidade());
    }

    @Test 
    public void criarVariosProdutosIncrementaCorretamenteOTotalDeProdutos(){
        int totalProdutosInicial = Produto.totalProdutos;

        var produto1 = new Produto(6, "p1", 10);
        var produto2 = new Produto(7, "p2", 1);
        var produto3 = new Produto(8, "p3", 5);

        assertEquals(10+1+5, Produto.totalProdutos - totalProdutosInicial);
    }

    // ===== PRODUTO() ===== (fim)
}
