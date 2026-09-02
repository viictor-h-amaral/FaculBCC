import model.Musica;
import model.Playlist;
import model.Usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class PlaylistTest {

    public Playlist playlistBase = new Playlist("playlistBase", new Usuario("donoBase", "dono@gmail.com"));

    @BeforeEach
    public void GerarObjetoPlaylistBase(){
        playlistBase = new Playlist("playlistBase", new Usuario("donoBase", "dono@gmail.com"));
    }


    /// TESTES PARA ADICIONAR MUSICA

    @Test
    @DisplayName("Parametro null deve ser rejeitado")
    public void ParametroNull_DeveSerRejeitado(){
        Musica musica = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> playlistBase.adicionar(musica));
    }

    public void carregarPlaylistComMusicas(int numeroMusicasAdicionar){
        for (int i = 1; i <= numeroMusicasAdicionar; i++){
            playlistBase.adicionar(new Musica("t", "a", i+100));
        }
    }

    @Test
    @DisplayName("Playlist cheia não deve adicionar a música")
    public void PlaylistCheia_NaoDeveAdicionarMusica(){
        carregarPlaylistComMusicas(100);

        var musica101 = new Musica("titulo101", "artista101", 101);
        int idMusica101 = musica101.getId();
        Assertions.assertFalse(playlistBase.adicionar(musica101));
        Assertions.assertNull(playlistBase.buscarMusicaPorId(idMusica101));
    }

    @Test
    @DisplayName("Playlist com espaço deve adicionar a música")
    public void PlaylistComEspaco_DeveAdicionarMusica(){
        carregarPlaylistComMusicas(10);

        var novaMusica = new Musica("titulo11", "artista11", 11);
        int idNovaMusica = novaMusica.getId();
        Assertions.assertTrue(playlistBase.adicionar(novaMusica));
        Assertions.assertNotNull(playlistBase.buscarMusicaPorId(idNovaMusica));
    }

    /// TESTES PARA ADICIONAR MUSICA (fim)
    /// TESTES PARA BUSCAR MUSICA PELO INDEX

    @Test
    @DisplayName("Index negativo deve ser rejeitado")
    public void IndexNegativo_DeveLancarExcecao(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> playlistBase.getNaPosicao(-1));
    }

    @Test
    @DisplayName("Index de posição não preenchida deve ser rejeitado")
    public void IndexPosicaoNaoPreenchida_DeveLancarExcecao(){
        carregarPlaylistComMusicas(10);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> playlistBase.getNaPosicao(50));
    }

    @Test
    @DisplayName("Index maior que tamanho do array deve ser rejeitado")
    public void IndexMaiorQueTamanhoDoArray_DeveLancarExcecao(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> playlistBase.getNaPosicao(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Index válido deve retornar música esperada")
    public void IndexValido_DeveRetornarMusicaEsperada(){
        int qntMusicasCarregarPorLote = 10; //adicionar 10 musicas faz com que o indice da decima seja 9
        int indiceProcurado = qntMusicasCarregarPorLote; //logo, o proximo indice eh o proprio 10

        carregarPlaylistComMusicas(qntMusicasCarregarPorLote);
        var musicaProcurada = new Musica("titulo musica $#*", "artista musica $#*", 321);
        playlistBase.adicionar(musicaProcurada);
        carregarPlaylistComMusicas(qntMusicasCarregarPorLote);

        var musicaEncontrada = playlistBase.getNaPosicao(indiceProcurado);
        Assertions.assertEquals(musicaProcurada.getId(), musicaEncontrada.getId());
        Assertions.assertEquals(musicaProcurada.getArtista(), musicaEncontrada.getArtista());
        Assertions.assertEquals(musicaProcurada.getTitulo(), musicaEncontrada.getTitulo());
        Assertions.assertEquals(musicaProcurada.getDuracaoSegundos(), musicaEncontrada.getDuracaoSegundos());
    }

    /// TESTES PARA BUSCAR MUSICA PELO INDEX (fim)
    /// TESTES PARA REMOVER MUSICA PELO INDEX

    @Test
    @DisplayName("Remover index negativo deve ser rejeitado")
    public void RemoverIndexNegativo_DeveLancarExcecao(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> playlistBase.removerNaPosicao(-1));
    }

    @Test
    @DisplayName("Remover index de posição não preenchida deve ser rejeitado")
    public void RemoverIndexPosicaoNaoPreenchida_DeveLancarExcecao(){
        carregarPlaylistComMusicas(10);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> playlistBase.removerNaPosicao(50));
    }

    @Test
    @DisplayName("Remover index maior que tamanho do array deve ser rejeitado")
    public void RemoverIndexMaiorQueTamanhoDoArray_DeveLancarExcecao(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> playlistBase.removerNaPosicao(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Index válido deve remover música esperada")
    public void IndexValido_DeveRemoverMusicaEsperada(){
        int qntMusicasCarregarPorLote = 10; //adicionar 10 musicas faz com que o indice da decima seja 9
        int indiceRemover = qntMusicasCarregarPorLote; //logo, o proximo indice eh o proprio 10

        carregarPlaylistComMusicas(qntMusicasCarregarPorLote);
        var musicaDeveRemover = new Musica("titulo r", "artista r", 100);
        int idMusicaDeveRemover = musicaDeveRemover.getId();
        playlistBase.adicionar(musicaDeveRemover);
        carregarPlaylistComMusicas(qntMusicasCarregarPorLote);

        Assertions.assertNotNull(musicaDeveRemover);
        playlistBase.removerNaPosicao(indiceRemover);
        Assertions.assertNull(playlistBase.buscarMusicaPorId(idMusicaDeveRemover));
    }

    public boolean validarSePlaylistOrdenada(Playlist playlist){
        boolean jaEncontrouIndiceNull = false;
        for (var musica : playlist.getArrayMusicas()){

            if(!jaEncontrouIndiceNull && musica == null){
                jaEncontrouIndiceNull = true;
                continue;
            }
            else if(jaEncontrouIndiceNull && musica != null)
                return false;
        }
        return true;
    }

    @Test
    @DisplayName("Remover música deve reordenar as outras, deixando nenhum 'buraco'")
    public void RemoverMusica_DeveRemoverReordenarMusicas(){
        int qntMusicasCargaInicial = 60;
        carregarPlaylistComMusicas(qntMusicasCargaInicial);
        var indicesRemover = new int[]{59, 57, 20, 5, 1}; //itens únicos, em ordem decrescente

        for(int indiceRemover : indicesRemover){
            playlistBase.removerNaPosicao(indiceRemover);
            Assertions.assertTrue(validarSePlaylistOrdenada(playlistBase));
        }
    }

    /// TESTES PARA REMOVER MUSICA PELO INDEX (fim)

}
