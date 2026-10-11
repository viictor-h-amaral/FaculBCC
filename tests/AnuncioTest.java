import model.Anuncio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class AnuncioTest {
    
    @Test
    @DisplayName("Anúncio com anunciante vazio deve ser rejeitado")
    public void anuncianteVazio_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Anuncio("", "Título do Anúncio", 30, 100);
        });
    }

    @Test
    @DisplayName("Anúncio com título vazio deve ser rejeitado")
    public void tituloVazio_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Anuncio("Anunciante", "", 30, 100);
        });
    }

    @Test
    @DisplayName("Anúncio com duração negativa deve ser rejeitado")
    public void duracaoNegativa_deveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Anuncio("Anunciante", "Título do Anúncio", -10, 100);
        });
    }

    @Test
    @DisplayName("Anúncio com valor por mil impressões negativo deve ser rejeitado")
    public void valorPorMilImpressoesNegativo_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Anuncio("Anunciante", "Título do Anúncio", 30, -50);
        });
    }

    @Test   
    @DisplayName("Anúncio válido deve ser criado corretamente")
    public void anuncioValido_deveSerCriadoCorretamente() {
        var anuncio = new Anuncio("Anunciante", "Título do Anúncio", 30, 100);

        Assertions.assertNotNull(anuncio);
        Assertions.assertEquals("Anunciante", anuncio.getAnunciante());
        Assertions.assertEquals("Título do Anúncio", anuncio.getTitulo());
        Assertions.assertEquals(30, anuncio.getDuracaoSegundos());
        Assertions.assertEquals(100, anuncio.getValorPorMilImpressoes());
        Assertions.assertEquals(0, anuncio.getImpressoes());
    }
}
