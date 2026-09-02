import model.Musica;
import model.Plataforma;
import model.Usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class PlataformaTest {
 
    public static Plataforma plataformaBase;
    public static Musica billieJean;
    public static Usuario jose;

    @BeforeAll
    public static void GerarObjetoPlataformaBase(){
        
        billieJean = new Musica("Billie Jean", "Michael Jackson", 294);
        var musicas = new Musica[]{
            new Musica("As It Was", "Harry Styles", 167),
            billieJean,
            new Musica("Hotel California", "Eagles", 390),
            new Musica("Stayin' Alive", "Bee Gees", 285)
        };

        jose = new Usuario("Jose", "jose@gmail.com");
        var usuarios = new Usuario[]{
            new Usuario("Joao", "joao@gmail.com"),
            new Usuario("Maria", "maria@gmail.com"),
            jose
        };

        plataformaBase = new Plataforma();
        plataformaBase.cadastrarMusicas(musicas);
        plataformaBase.cadastrarUsuarios(usuarios);
    }

    /// TESTES PARA BUSCAR MUSICA POR ID E TITULO 

    @Test
    @DisplayName("id inexistente deve retornar null e não deve lançar exceção")
    public void BuscarIdInexistente_DeveRetornarNull(){
        Assertions.assertNull(plataformaBase.buscarMusicaPorId(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("título inexistente deve retornar null não deve lançar exceção")
    public void BuscarTituloInexistente_DeveRetornarNull(){
        Assertions.assertNull(plataformaBase.buscarMusica("Esse titulo não existe!"));
    }

    @Test
    @DisplayName("id existente deve retornar a música correta")
    public void BuscarIdExistente_DeveRetornarMusicaCorreta(){
        var musicaEncontrada = plataformaBase.buscarMusicaPorId(billieJean.getId());
        Assertions.assertEquals(billieJean.getId(), musicaEncontrada.getId());
        Assertions.assertEquals(billieJean.getArtista(), musicaEncontrada.getArtista());
        Assertions.assertEquals(billieJean.getTitulo(), musicaEncontrada.getTitulo());
        Assertions.assertEquals(billieJean.getDuracaoSegundos(), musicaEncontrada.getDuracaoSegundos());
    }

    @Test
    @DisplayName("título existente deve retornar a música correta")
    public void BuscarTituloExistente_DeveRetornarMusicaCorreta(){
        var musicaEncontrada = plataformaBase.buscarMusica(billieJean.getTitulo());
        Assertions.assertEquals(billieJean.getId(), musicaEncontrada.getId());
        Assertions.assertEquals(billieJean.getArtista(), musicaEncontrada.getArtista());
        Assertions.assertEquals(billieJean.getTitulo(), musicaEncontrada.getTitulo());
        Assertions.assertEquals(billieJean.getDuracaoSegundos(), musicaEncontrada.getDuracaoSegundos());
    }

    /// TESTES PARA BUSCAR MUSICA POR ID E TITULO (fim)

    @Test
    @DisplayName("id inexistente de usuário deve retornar null e não deve lançar exceção")
    public void BuscarIdInexistenteUsuario_DeveRetornarNull(){
        Assertions.assertNull(plataformaBase.buscarUsuario(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("título inexistente de usuário deve retornar null não deve lançar exceção")
    public void BuscarTituloInexistenteUsuario_DeveRetornarNull(){
        Assertions.assertNull(plataformaBase.buscarUsuario("Esse usuário não existe!"));
    }

    @Test
    @DisplayName("id existente de usuário deve retornar a usuário correto")
    public void BuscarIdExistenteUsuario_DeveRetornarUsuarioCorreto(){
        var usuarioEncontrado = plataformaBase.buscarUsuario(jose.getId());
        Assertions.assertEquals(jose.getId(), usuarioEncontrado.getId());
        Assertions.assertEquals(jose.getNome(), usuarioEncontrado.getNome());
        Assertions.assertEquals(jose.getEmail(), usuarioEncontrado.getEmail());
    }

    @Test
    @DisplayName("nome existente de usuário deve retornar a usuário correto")
    public void BuscarNomeExistenteUsuario_DeveRetornarUsuarioCorreto(){
        var usuarioEncontrado = plataformaBase.buscarUsuario(jose.getNome());
        Assertions.assertEquals(jose.getId(), usuarioEncontrado.getId());
        Assertions.assertEquals(jose.getNome(), usuarioEncontrado.getNome());
        Assertions.assertEquals(jose.getEmail(), usuarioEncontrado.getEmail());
    }

}
