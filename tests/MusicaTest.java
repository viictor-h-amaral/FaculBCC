import model.Musica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class MusicaTest {

    /// TESTES PARA O METODO getDuracaoFormatada

    @Test
    @DisplayName("Duração com minutos e segundos")
    public void duracaoComMunitosESegundos_formatadaCorretamente() {
        var musica125seg = new Musica("M1", "A1", 125);
        Assertions.assertEquals("02:05", musica125seg.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração redonda em minutos")
    public void duracaoRedondaEmMinutos_formatadaCorretamente() {
        var musica90seg = new Musica("M1", "A1", 90);
        Assertions.assertEquals("01:30", musica90seg.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Menos de um minuto, com zero a esquerda")
    public void duracaoMenosDeUmMinuto_formatadaCorretamente() {
        var musica5seg = new Musica("M1", "A1", 5);
        Assertions.assertEquals("00:05", musica5seg.getDuracaoFormatada());
    } 

    @Test
    @DisplayName("Dois dígitos nos minutos")
    public void duracaoDoisDigitosNosMinutos_formatadaCorretamente() {
        var musica600seg = new Musica("M1", "A1", 600);
        Assertions.assertEquals("10:00", musica600seg.getDuracaoFormatada());
    } 

    @Test
    @DisplayName("Valor logo abaixo de dez minutos")
    public void duracaoLogoAbaixoDeDezMinutos_formatadaCorretamente() {
        var musica599seg = new Musica("M1", "A1", 599);
        Assertions.assertEquals("09:59", musica599seg.getDuracaoFormatada());
    }

    /// TESTES PARA O METODO getDuracaoFormatada (fim)
    /// TESTES PARA CONSTRUTOR DE MUSICA

    @Test
    @DisplayName("Título vazio deve ser rejeitado")
    public void tituloVazio_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica(" ", "Queen", 355);
        });
    }

    @Test
    @DisplayName("Título nulo deve ser rejeitado")
    public void tituloNulo_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica(null, "Queen", 355);
        });
    }

    @Test
    @DisplayName("Artista vazio deve ser rejeitado")
    public void artistaVazio_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica("Bohemian Rhapsody", " ", 355);
        });
    }

    @Test
    @DisplayName("Duração zero deve ser rejeitada")
    public void duracaoZero_deveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica("Bohemian Rhapsody", "Queen", 0);
        });
    }

    @Test
    @DisplayName("Duração negativa deve ser rejeitada")
    public void duracaoNegativa_deveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica("Bohemian Rhapsody", "Queen", -10);
        });
    }

    @Test
    @DisplayName("Dados validos criam a música")
    public void dadosValidos_criamAMusica() {
        var musica = new Musica("Bohemian Rhapsody", "Queen", 355);
        Assertions.assertNotNull(musica);
        Assertions.assertEquals("Bohemian Rhapsody", musica.getTitulo());
        Assertions.assertEquals("Queen", musica.getArtista());
        Assertions.assertEquals(355, musica.getDuracaoSegundos());
        Assertions.assertEquals(musica.getReproducoes(), 0);
    }

    /// TESTES PARA CONSTRUTOR DE MUSICA (fim)
    /// TESTES PARA reproduzir MUSICA

    @Test
    @DisplayName("Reproduzir música deve incrementar contador de reproduções")
    public void reproduzirMusica_incrementaContadorReproducoes(){
        var musica = new Musica("titulo", "artista", 100);

        var qntReproducoesDesejadas = 10;
        for (int i = 1; i <= qntReproducoesDesejadas; i++){
            musica.reproduzir();
            Assertions.assertEquals(musica.getReproducoes(), i);
        }
    }

    /// TESTES PARA reproduzir MUSICA (fim)
    /// TESTES PARA GERACAO CORRETA DE IDS

    @Test
    @DisplayName("Criar nova música gera ID válido e novo (sequencial)")
    public void criarNovaMusica_geraIdValidoENaSequencia(){
        int proximoIdEsperado = Musica.getProximoId();
        int qntRepeticoes = 4;

        for (int i = 1; i <= qntRepeticoes; i++){
            var novaMusica = new Musica("titulo "+i, "artista "+i, 100+i);
            Assertions.assertEquals(proximoIdEsperado, novaMusica.getId());
            proximoIdEsperado++;
        }
    }

    /// TESTES PARA GERACAO CORRETA DE IDS (fim)
}
