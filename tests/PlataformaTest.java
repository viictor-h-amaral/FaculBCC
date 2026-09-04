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
    public static void gerarObjetoPlataformaBase(){
        
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

    /// TESTES PARA CADASTRAR MUSICA

    @Test
    @DisplayName("Música nula deve ser rejeitada")
    public void cadastrarMusicaNula_deveLancarExcecao(){
        var plataforma = new Plataforma();
        Assertions.assertThrows(IllegalArgumentException.class, () -> plataforma.cadastrarMusica(null));
    }

    @Test
    @DisplayName("Plataforma cheia não deve cadastrar a música")
    public void plataformaCheia_naoDeveCadastrarMusica(){
        var plataforma = new Plataforma();
        for (int i = 1; i <= 500; i++){
            plataforma.cadastrarMusica(new Musica("musica " + i, "artista " + i, i + 30));
        }

        var musicaExtra = new Musica("musica extra", "artista extra", 120);
        Assertions.assertFalse(plataforma.cadastrarMusica(musicaExtra));
        Assertions.assertNull(plataforma.buscarMusica(musicaExtra.getTitulo()));
    }

    @Test
    @DisplayName("Plataforma com espaço deve cadastrar a música")
    public void plataformaComEspaco_deveCadastrarMusica(){
        var plataforma = new Plataforma();
        var musica = new Musica("titulo", "artista", 180);

        Assertions.assertTrue(plataforma.cadastrarMusica(musica));
        Assertions.assertNotNull(plataforma.buscarMusicaPorId(musica.getId()));
    }

    @Test
    @DisplayName("Array de músicas com espaço deve cadastrar todas")
    public void arrayMusicasComEspaco_deveCadastrarTodas(){
        var plataforma = new Plataforma();
        var musicas = new Musica[] {
            new Musica("M1", "A1", 100),
            new Musica("M2", "A2", 110),
            new Musica("M3", "A3", 120)
        };

        Assertions.assertTrue(plataforma.cadastrarMusicas(musicas));
        Assertions.assertEquals(3, plataforma.getTotalMusicas());
        Assertions.assertNotNull(plataforma.buscarMusica("M2"));
    }

    @Test
    @DisplayName("Array de músicas excedendo a capacidade deve ser rejeitado")
    public void arrayMusicasExcedendoCapacidade_deveRetornarFalse(){
        var plataforma = new Plataforma();
        var musicas = new Musica[501];
        for (int i = 0; i < 501; i++){ 
            musicas[i] = new Musica("M" + i, "A" + i, 100 + i);
        }

        Assertions.assertFalse(plataforma.cadastrarMusicas(musicas));
        Assertions.assertEquals(0, plataforma.getTotalMusicas());
    }

    /// TESTES PARA CADASTRAR MUSICA (fim)
    /// TESTES PARA CADASTRAR USUARIO

    @Test
    @DisplayName("Usuário nulo deve ser rejeitado")
    public void cadastrarUsuarioNulo_deveLancarExcecao(){
        var plataforma = new Plataforma();
        Assertions.assertThrows(IllegalArgumentException.class, () -> plataforma.cadastrarUsuario(null));
    }

    @Test
    @DisplayName("Plataforma cheia não deve cadastrar o usuário")
    public void plataformaCheia_naoDeveCadastrarUsuario(){
        var plataforma = new Plataforma();
        for (int i = 1; i <= 500; i++){
            plataforma.cadastrarUsuario(new Usuario("usuario " + i, "usuario" + i + "@gmail.com"));
        }

        var usuarioExtra = new Usuario("usuario extra", "usuarioextra@gmail.com");
        Assertions.assertFalse(plataforma.cadastrarUsuario(usuarioExtra));
        Assertions.assertNull(plataforma.buscarUsuario(usuarioExtra.getNome()));
    }

    @Test
    @DisplayName("Plataforma com espaço deve cadastrar o usuário")
    public void plataformaComEspaco_deveCadastrarUsuario(){
        var plataforma = new Plataforma();
        var usuario = new Usuario("usuario", "usuario@gmail.com");

        Assertions.assertTrue(plataforma.cadastrarUsuario(usuario));
        Assertions.assertNotNull(plataforma.buscarUsuario(usuario.getId()));
    }

    @Test
    @DisplayName("Array de usuários com espaço deve cadastrar todos")
    public void arrayUsuariosComEspaco_deveCadastrarTodos(){
        var plataforma = new Plataforma();
        var usuarios = new Usuario[] {
            new Usuario("U1", "u1@gmail.com"),
            new Usuario("U2", "u2@gmail.com"),
            new Usuario("U3", "u3@gmail.com")
        };

        Assertions.assertTrue(plataforma.cadastrarUsuarios(usuarios));
        Assertions.assertEquals(3, plataforma.getTotalUsuarios());
        Assertions.assertNotNull(plataforma.buscarUsuario("U2"));
    }

    @Test
    @DisplayName("Array de usuários excedendo a capacidade deve ser rejeitado")
    public void arrayUsuariosExcedendoCapacidade_deveRetornarFalse(){
        var plataforma = new Plataforma();
        var usuarios = new Usuario[501];
        for (int i = 0; i < usuarios.length; i++){
            usuarios[i] = new Usuario("U" + i, "u" + i + "@gmail.com");
        }

        Assertions.assertFalse(plataforma.cadastrarUsuarios(usuarios));
        Assertions.assertEquals(0, plataforma.getTotalUsuarios());
    }

    /// TESTES PARA CADASTRAR USUARIO (fim)

    /// TESTES PARA BUSCAR MUSICA POR ID E TITULO 

    @Test
    @DisplayName("id inexistente deve retornar null e não deve lançar exceção")
    public void buscarIdInexistente_deveRetornarNull(){
        Assertions.assertNull(plataformaBase.buscarMusicaPorId(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("título inexistente deve retornar null não deve lançar exceção")
    public void buscarTituloInexistente_deveRetornarNull(){
        Assertions.assertNull(plataformaBase.buscarMusica("Esse titulo não existe!"));
    }

    @Test
    @DisplayName("id existente deve retornar a música correta")
    public void buscarIdExistente_deveRetornarMusicaCorreta(){
        var musicaEncontrada = plataformaBase.buscarMusicaPorId(billieJean.getId());
        Assertions.assertEquals(billieJean.getId(), musicaEncontrada.getId());
        Assertions.assertEquals(billieJean.getArtista(), musicaEncontrada.getArtista());
        Assertions.assertEquals(billieJean.getTitulo(), musicaEncontrada.getTitulo());
        Assertions.assertEquals(billieJean.getDuracaoSegundos(), musicaEncontrada.getDuracaoSegundos());
    }

    @Test
    @DisplayName("título existente deve retornar a música correta")
    public void buscarTituloExistente_deveRetornarMusicaCorreta(){
        var musicaEncontrada = plataformaBase.buscarMusica(billieJean.getTitulo());
        Assertions.assertEquals(billieJean.getId(), musicaEncontrada.getId());
        Assertions.assertEquals(billieJean.getArtista(), musicaEncontrada.getArtista());
        Assertions.assertEquals(billieJean.getTitulo(), musicaEncontrada.getTitulo());
        Assertions.assertEquals(billieJean.getDuracaoSegundos(), musicaEncontrada.getDuracaoSegundos());
    }

    /// TESTES PARA BUSCAR MUSICA POR ID E TITULO (fim)

    @Test
    @DisplayName("id inexistente de usuário deve retornar null e não deve lançar exceção")
    public void buscarIdInexistenteUsuario_deveRetornarNull(){
        Assertions.assertNull(plataformaBase.buscarUsuario(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("título inexistente de usuário deve retornar null não deve lançar exceção")
    public void buscarTituloInexistenteUsuario_deveRetornarNull(){
        Assertions.assertNull(plataformaBase.buscarUsuario("Esse usuário não existe!"));
    }

    @Test
    @DisplayName("id existente de usuário deve retornar a usuário correto")
    public void buscarIdExistenteUsuario_deveRetornarUsuarioCorreto(){
        var usuarioEncontrado = plataformaBase.buscarUsuario(jose.getId());
        Assertions.assertEquals(jose.getId(), usuarioEncontrado.getId());
        Assertions.assertEquals(jose.getNome(), usuarioEncontrado.getNome());
        Assertions.assertEquals(jose.getEmail(), usuarioEncontrado.getEmail());
    }

    @Test
    @DisplayName("nome existente de usuário deve retornar a usuário correto")
    public void buscarNomeExistenteUsuario_deveRetornarUsuarioCorreto(){
        var usuarioEncontrado = plataformaBase.buscarUsuario(jose.getNome());
        Assertions.assertEquals(jose.getId(), usuarioEncontrado.getId());
        Assertions.assertEquals(jose.getNome(), usuarioEncontrado.getNome());
        Assertions.assertEquals(jose.getEmail(), usuarioEncontrado.getEmail());
    }

}
