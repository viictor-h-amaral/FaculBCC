import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.PlanoFamilia;
import model.PlanoGratuito;
import model.PlanoIndividual;
import model.Podcast;

public class PlanoTest {

    @Test
    @DisplayName("Plano gratuito mantém mensalidade zero e anúncios")
    public void planoGratuito_temConfiguracaoEsperada() {
        var plano = new PlanoGratuito();

        Assertions.assertEquals("Gratuito", plano.getNome());
        Assertions.assertEquals(1, plano.getMaxDispositivos());
        Assertions.assertTrue(plano.temAnuncios());
        Assertions.assertEquals(0.0, plano.calcularMensalidade());
    }

    @Test
    @DisplayName("Planos pagos compartilham preço e calculam mensalidades")
    public void planosPagos_calculamMensalidades() {
        var individual = new PlanoIndividual(19.90);
        var familia = new PlanoFamilia(24.90, 3);

        Assertions.assertFalse(individual.temAnuncios());
        Assertions.assertEquals(19.90, individual.calcularMensalidade(), 0.01);
        Assertions.assertEquals(34.70, familia.calcularMensalidade(), 0.01);
        Assertions.assertEquals(6, familia.getMaxDispositivos());
    }

    @Test
    @DisplayName("Planos pagos rejeitam preços e quantidades de membros inválidos")
    public void planosPagos_rejeitamValoresInvalidos() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PlanoIndividual(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PlanoFamilia(20, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PlanoFamilia(20, 7));
    }

    @Test
    @DisplayName("Podcast usa créditos e contador herdados de Conteudo")
    public void podcast_reproduzEContaNaSuperclasse() {
        var podcast = new Podcast("Episódio", "Apresentador", 30, 2, 'M');

        Assertions.assertEquals("episódio 2 - Apresentador", podcast.getCreditos());
        Assertions.assertEquals(0, podcast.getReproducoes());

        podcast.reproduzir();

        Assertions.assertEquals(1, podcast.getReproducoes());
    }
}