import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Usuario;

public class UsuarioTest {

    /// TESTES PARA CONSTRUTOR DE USUARIO

    @Test
    @DisplayName("Nome vazio deve ser rejeitado")
    public void nomeVazio_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(" ", "usuario@gmail.com");
        });
    }

    @Test
    @DisplayName("Nome nulo deve ser rejeitado")
    public void nomeNulo_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(null, "usuario@gmail.com");
        });
    }

    @Test
    @DisplayName("Email vazio deve ser rejeitado")
    public void emailVazio_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Usuario", " ");
        });
    }

    @Test
    @DisplayName("Email nulo deve ser rejeitado")
    public void emailNulo_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Usuario", null);
        });
    }

    @Test
    @DisplayName("Email sem arroba deve ser rejeitado")
    public void emailSemArroba_deveSerRejeitado() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Usuario", "usuarioahobagmail.com");
        });
    }

    @Test
    @DisplayName("Dados válidos criam o usuário")
    public void dadosValidos_criamUsuario() {
        var usuario = new Usuario("Usuario", "usuario@gmail.com");
        Assertions.assertNotNull(usuario);
        Assertions.assertEquals("Usuario", usuario.getNome());
        Assertions.assertEquals("usuario@gmail.com", usuario.getEmail());
        Assertions.assertTrue(usuario.getId() > 0);
    }

    /// TESTES PARA CONSTRUTOR DE USUARIO (fim)
    /// TESTES PARA GERACAO CORRETA DE IDS

    @Test
    @DisplayName("Criar novo usuário gera ID válido e novo (sequencial)")
    public void criarNovoUsuario_geraIdValidoENaSequencia(){
        int proximoIdEsperado = Usuario.getProximoId();
        int qntRepeticoes = 7;

        for (int i = 1; i <= qntRepeticoes; i++){
            var nome = "u"+i;
            var novoUsuario = new Usuario(nome, nome+"@gmail.com");
            Assertions.assertEquals(proximoIdEsperado, novoUsuario.getId());
            proximoIdEsperado++;
        }
    }

    @Test
    @DisplayName("Seguir usuário repetido deve ser rejeitado")
    public void seguirUsuarioRepetido_deveSerRejeitado(){
        var usuario = new Usuario("Usuario 1", "usuario1@gmail.com");
        var outro = new Usuario("Usuario 2", "usuario2@gmail.com");

        usuario.seguir(outro);

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuario.seguir(outro));
        Assertions.assertEquals(1, usuario.getQuantidadeSeguindo());
    }

    @Test
    @DisplayName("Usuário não deve seguir a si mesmo")
    public void seguirASiMesmo_deveSerRejeitado(){
        var usuario = new Usuario("Usuario", "usuario@ gmail.com".replace(" ", ""));

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuario.seguir(usuario));
        Assertions.assertEquals(0, usuario.getQuantidadeSeguindo());
    }

    @Test
    @DisplayName("Seguir usuário nulo deve ser rejeitado")
    public void seguirUsuarioNulo_deveSerRejeitado(){
        var usuario = new Usuario("Usuario", "usuario@gmail.com");

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuario.seguir(null));
    }

    @Test
    @DisplayName("Deixar de seguir deve remover a relação")
    public void deixarDeSeguir_deveRemoverRelacao(){
        var usuario = new Usuario("Usuario 1", "usuario1@gmail.com");
        var outro = new Usuario("Usuario 2", "usuario2@gmail.com");
        usuario.seguir(outro);

        usuario.deixarDeSeguir(outro);

        Assertions.assertEquals(0, usuario.getQuantidadeSeguindo());
    }

    @Test
    @DisplayName("Deixar de seguir usuário nulo deve ser rejeitado")
    public void deixarDeSeguirUsuarioNulo_deveSerRejeitado(){
        var usuario = new Usuario("Usuario", "usuario@gmail.com");

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuario.deixarDeSeguir(null));
    }

    /// TESTES PARA GERACAO CORRETA DE IDS (fim)
}
