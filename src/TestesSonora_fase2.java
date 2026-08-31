import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import helpers.ScannerHelper;
import model.Musica;
import model.Plataforma;
import model.Playlist;
import model.Usuario;

public class TestesSonora_fase2 {

    public static void main(String[] args) {
        testarValidacoesDosConstrutores();
        testarExcecoesDaPlaylist();
        testarRetornosDeFluxoNormal();
        testarLeituraNumericaComRecuperacao();
        testarRoteiroDeDemonstracao();
        System.out.println("Todos os testes da Fase 02 passaram.");
    }

    private static void testarValidacoesDosConstrutores() {
        verificarIllegalArgumentException(() -> new Musica(null, "Artista", 100), "título nulo");
        verificarIllegalArgumentException(() -> new Musica("", "Artista", 100), "título vazio");
        verificarIllegalArgumentException(() -> new Musica("   ", "Artista", 100), "título só com espaços");
        verificarIllegalArgumentException(() -> new Musica("Título", null, 100), "artista nulo");
        verificarIllegalArgumentException(() -> new Musica("Título", "", 100), "artista vazio");
        verificarIllegalArgumentException(() -> new Musica("Título", "Artista", 0), "duração zero");
        verificarIllegalArgumentException(() -> new Musica("Título", "Artista", -1), "duração negativa");

        verificarIllegalArgumentException(() -> new Usuario(null, "a@b.com"), "nome nulo");
        verificarIllegalArgumentException(() -> new Usuario("", "a@b.com"), "nome vazio");
        verificarIllegalArgumentException(() -> new Usuario("   ", "a@b.com"), "nome só com espaços");
        verificarIllegalArgumentException(() -> new Usuario("Ana", null), "email nulo");
        verificarIllegalArgumentException(() -> new Usuario("Ana", ""), "email vazio");
        verificarIllegalArgumentException(() -> new Usuario("Ana", "ab.com"), "email sem @");

        Usuario dono = new Usuario("Dono", "dono@sonora.com");
        verificarIllegalArgumentException(() -> new Playlist(null, dono), "nome de playlist nulo");
        verificarIllegalArgumentException(() -> new Playlist("", dono), "nome de playlist vazio");
        verificarIllegalArgumentException(() -> new Playlist("   ", dono), "nome de playlist só com espaços");
        verificarIllegalArgumentException(() -> new Playlist("Favoritas", null), "dono nulo");
    }

    private static void testarExcecoesDaPlaylist() {
        Playlist playlistVazia = new Playlist("Favoritas", new Usuario("Dono 2", "dono2@sonora.com"));
        verificarIndexOutOfBounds(() -> playlistVazia.getNaPosicao(0), "get em playlist vazia");
        verificarIndexOutOfBounds(() -> playlistVazia.removerNaPosicao(0), "remoção em playlist vazia");

        Playlist playlistComUmaMusica = new Playlist("Uma música", new Usuario("Dono 3", "dono3@sonora.com"));
        playlistComUmaMusica.adicionar(new Musica("Só", "Artista", 30));
        verificarIndexOutOfBounds(() -> playlistComUmaMusica.getNaPosicao(1), "get com índice fora do intervalo real");
        verificarIndexOutOfBounds(() -> playlistComUmaMusica.removerNaPosicao(1), "remoção com índice fora do intervalo real");
        verificarIndexOutOfBounds(() -> playlistComUmaMusica.getNaPosicao(-1), "get com índice negativo");
        verificarIndexOutOfBounds(() -> playlistComUmaMusica.removerNaPosicao(-1), "remoção com índice negativo");

        verificarIllegalArgumentException(() -> playlistVazia.adicionar(null), "adição de música nula");
    }

    private static void testarRetornosDeFluxoNormal() {
        Usuario dono = new Usuario("Dono 3", "dono3@sonora.com");
        Playlist playlist = new Playlist("Cheia", dono);
        for (int i = 0; i < 100; i++) {
            verificar(playlist.adicionar(new Musica("Música " + i, "Artista", 30)),
                    "música deve entrar enquanto há espaço");
        }
        verificar(!playlist.adicionar(new Musica("Extra", "Artista", 30)),
                "playlist cheia deve retornar false");

        Plataforma plataforma = new Plataforma();
        verificar(plataforma.buscarMusicaPorId(999999) == null,
                "busca por ID inexistente deve retornar null");
        verificar(plataforma.buscarMusica("Inexistente") == null,
                "busca por título inexistente deve retornar null");
    }

    private static void testarLeituraNumericaComRecuperacao() {
        ScannerHelper scanner = new ScannerHelper(new Scanner(new ByteArrayInputStream(
            "abc\n7\n".getBytes(StandardCharsets.UTF_8))));
        ByteArrayOutputStream saidaErro = new ByteArrayOutputStream();
        PrintStream erroAnterior = System.err;
        try {
            System.setErr(new PrintStream(saidaErro));
            verificar(scanner.lerInt("Opção: ", 0, 10) == 7,
                    "leitura deve aceitar o número após uma entrada inválida");
        } finally {
            System.setErr(erroAnterior);
            scanner.fecharScanner();
        }
        verificar(saidaErro.toString(StandardCharsets.UTF_8).contains("Digite um número"),
                "entrada inválida deve produzir aviso ao usuário no stderr");
    }

    private static void testarRoteiroDeDemonstracao() {
        verificarIllegalArgumentException(() -> new Musica("", "Artista", 100), "roteiro 1 - título vazio");
        verificarIllegalArgumentException(() -> new Musica("Titulo", "Artista", 0), "roteiro 2 - duração zero");
        verificarIllegalArgumentException(() -> new Usuario("Ana", "ana.com"), "roteiro 3 - email sem @");

        Playlist playlist = new Playlist("Demo", new Usuario("Dono", "dono@sonora.com"));
        playlist.adicionar(new Musica("A", "Artista", 10));
        verificarIndexOutOfBounds(() -> playlist.getNaPosicao(1), "roteiro 4 - índice fora do intervalo");

        Playlist playlistCheia = new Playlist("Cheia", new Usuario("Dono2", "dono2@sonora.com"));
        for (int i = 0; i < 100; i++) {
            verificar(playlistCheia.adicionar(new Musica("M" + i, "Artista", 30)),
                    "roteiro 5 - deve adicionar até a capacidade");
        }
        verificar(!playlistCheia.adicionar(new Musica("Extra", "Artista", 30)),
                "roteiro 5 - playlist cheia deve voltar false");

        Plataforma plataforma = new Plataforma();
        verificar(plataforma.buscarMusicaPorId(999) == null,
                "roteiro 6 - busca por id inexistente deve retornar null");

        ScannerHelper scanner = new ScannerHelper(new Scanner(new ByteArrayInputStream(
            "abc\n5\n".getBytes(StandardCharsets.UTF_8))));
        ByteArrayOutputStream saidaErro = new ByteArrayOutputStream();
        PrintStream erroAnterior = System.err;
        try {
            System.setErr(new PrintStream(saidaErro));
            verificar(scanner.lerInt("Opção: ", 0, 10) == 5,
                    "roteiro 7 - leitura inválida deve recuperar o número válido");
        } finally {
            System.setErr(erroAnterior);
            scanner.fecharScanner();
        }

        try {
            int pos = Integer.parseInt("0");
            Playlist p = new Playlist("X", new Usuario("D", "d@x.com"));
            p.adicionar(new Musica("A", "B", 10));
            Musica m = p.getNaPosicao(pos);
            verificar(m != null, "roteiro 8 - acesso válido deve retornar música");
        } catch (NumberFormatException e) {
            throw new AssertionError("roteiro 8 - não era para cair em NumberFormatException");
        } catch (IndexOutOfBoundsException e) {
            throw new AssertionError("roteiro 8 - não era para cair em índice fora do intervalo");
        }

        try {
            System.out.println("iniciando final");
        } finally {
            System.out.println("finalizou com finally");
        }
    }

    private static void verificarIllegalArgumentException(Runnable operacao, String caso) {
        try {
            operacao.run();
        } catch (IllegalArgumentException e) {
            verificar(e.getMessage() != null && !e.getMessage().trim().isEmpty()
                    && !e.getMessage().equalsIgnoreCase("erro"),
                    caso + " deve ter mensagem clara");
            return;
        }
        throw new AssertionError(caso + " deveria lançar IllegalArgumentException");
    }

    private static void verificarIndexOutOfBounds(Runnable operacao, String caso) {
        try {
            operacao.run();
        } catch (IndexOutOfBoundsException e) {
            verificar(e.getMessage() != null && !e.getMessage().trim().isEmpty(),
                    caso + " deve ter mensagem clara");
            return;
        }
        throw new AssertionError(caso + " deveria lançar IndexOutOfBoundsException");
    }

    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    private interface Runnable {
        void run();
    }
}