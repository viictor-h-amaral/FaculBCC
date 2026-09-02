import model.Musica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class MusicaTest {

    /// TESTES PARA O METODO getDuracaoFormatada

    @Test
    @DisplayName("Duração com minutos e segundos")
    public void DuracaoComMunitosESegundos_FormatadaCorretamente() {
        var musica125seg = new Musica("M1", "A1", 125);
        Assertions.assertEquals("02:05", musica125seg.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Duração redonda em minutos")
    public void DuracaoRedondaEmMinutos_FormatadaCorretamente() {
        var musica90seg = new Musica("M1", "A1", 90);
        Assertions.assertEquals("01:30", musica90seg.getDuracaoFormatada());
    }

    @Test
    @DisplayName("Menos de um minuto, com zero a esquerda")
    public void DuracaoMenosDeUmMinuto_FormatadaCorretamente() {
        var musica5seg = new Musica("M1", "A1", 5);
        Assertions.assertEquals("00:05", musica5seg.getDuracaoFormatada());
    } 

    @Test
    @DisplayName("Dois dígitos nos minutos")
    public void DuracaoDoisDigitosNosMinutos_FormatadaCorretamente() {
        var musica600seg = new Musica("M1", "A1", 600);
        Assertions.assertEquals("10:00", musica600seg.getDuracaoFormatada());
    } 

    @Test
    @DisplayName("Valor logo abaixo de dez minutos")
    public void DuracaoLogoAbaixoDeDezMinutos_FormatadaCorretamente() {
        var musica599seg = new Musica("M1", "A1", 599);
        Assertions.assertEquals("09:59", musica599seg.getDuracaoFormatada());
    }

    /// TESTES PARA O METODO getDuracaoFormatada (fim)
    /// TESTES PARA CONSTRUTOR DE MUSICA

    @Test
    @DisplayName("Título vazio deve ser rejeitado")
    public void TituloVazio_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica(" ", "Queen", 355);
        });
    }

    @Test
    @DisplayName("Título nulo deve ser rejeitado")
    public void TituloNulo_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica(null, "Queen", 355);
        });
    }

    @Test
    @DisplayName("Artista vazio deve ser rejeitado")
    public void ArtistaVazio_DeveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica("Bohemian Rhapsody", " ", 355);
        });
    }

    @Test
    @DisplayName("Duração zero deve ser rejeitada")
    public void DuracaoZero_DeveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica("Bohemian Rhapsody", "Queen", 0);
        });
    }

    @Test
    @DisplayName("Duração negativa deve ser rejeitada")
    public void DuracaoNegativa_DeveSerRejeitada() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Musica("Bohemian Rhapsody", "Queen", -10);
        });
    }

    @Test
    @DisplayName("Dados validos criam a música")
    public void DadosValidos_CriamAMusica() {
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
    public void ReproduzirMusica_IncrementaContadorReproduzoes(){
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
    public void CriarNovaMusica_GeraIdValidoENaSequencia(){
        int ultimoId = 0;
        int qntRepeticoes = 4;

        for (int i = 1; i <= qntRepeticoes; i++){
            var novaMusica = new Musica("titulo "+i, "artista "+i, 100+i);
            Assertions.assertEquals(novaMusica.getId(), ultimoId+1);
            ultimoId++;
        }
    }

    /// TESTES PARA GERACAO CORRETA DE IDS (fim)
}
