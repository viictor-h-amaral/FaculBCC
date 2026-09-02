import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Usuario;

public class UsuarioTest {

    /// TESTES PARA GERACAO CORRETA DE IDS

    @Test
    @DisplayName("Criar novo usuário gera ID válido e novo (sequencial)")
    public void CriarNovoUsuario_GeraIdValidoENaSequencia(){
        int ultimoId = 0;
        int qntRepeticoes = 7;

        for (int i = 1; i <= qntRepeticoes; i++){
            var nome = "u"+i;
            var novoUsuario = new Usuario(nome, nome+"@gmail.com");
            Assertions.assertEquals(novoUsuario.getId(), ultimoId+1);
            ultimoId++;
        }
    }

    /// TESTES PARA GERACAO CORRETA DE IDS (fim)
}
