import model.Musica;
import model.Plataforma;
import model.Playlist;
import model.Usuario;

public class TestesSonora_fase1 {
    public static void main(String[] args) {
        testarIdentificadoresUnicos();
        testarDuracaoFormatada();
        testarPlaylistEReproducao();
        testarBuscaEReproducaoEmMassa();
        testarRemocaoSemBuraco();
        testarPlaylistCheia();
        testarPlataforma();
        System.out.println("Todos os testes passaram.");
    }

    private static void testarIdentificadoresUnicos() {
        Musica m1 = new Musica("Song 1", "Artista 1", 180);
        Musica m2 = new Musica("Song 2", "Artista 2", 200);
        Musica m3 = new Musica("Song 3", "Artista 3", 220);

        Usuario u1 = new Usuario("Ana", "ana@email.com");
        Usuario u2 = new Usuario("Bruno", "bruno@email.com");

        verificar(m1.getId() != m2.getId() && m2.getId() != m3.getId() && m1.getId() != m3.getId(),
                "IDs das músicas devem ser únicos");
        verificar(m1.getId() < m2.getId() && m2.getId() < m3.getId(),
                "IDs das músicas devem seguir ordem crescente");
        verificar(u1.getId() != u2.getId(), "IDs dos usuários devem ser únicos");
        verificar(u1.getId() < u2.getId(), "IDs dos usuários devem seguir ordem crescente");
    }

    private static void testarDuracaoFormatada() {
        Musica m354 = new Musica("Duracao 354", "Artista", 354);
        Musica m65 = new Musica("Duracao 65", "Artista", 65);
        Musica m600 = new Musica("Duracao 600", "Artista", 600);

        verificar("05:54".equals(m354.getDuracaoFormatada()),
                "354s deve virar 05:54");
        verificar("01:05".equals(m65.getDuracaoFormatada()),
                "65s deve virar 01:05");
        verificar("10:00".equals(m600.getDuracaoFormatada()),
                "600s deve virar 10:00");
    }

    private static void testarPlaylistEReproducao() {
        Usuario dono = new Usuario("Carlos", "carlos@email.com");
        Playlist playlist = new Playlist("Favoritas", dono);

        Musica m1 = new Musica("A", "Artista A", 120);
        Musica m2 = new Musica("B", "Artista B", 180);

        verificar(playlist.adicionar(m1), "Deve adicionar a primeira música");
        verificar(playlist.adicionar(m2), "Deve adicionar a segunda música");
        verificar(playlist.getQuantidade() == 2, "Quantidade deve ser 2");
        verificar(playlist.getDuracaoTotalSegundos() == 300,
                "Duração total deve somar 300 segundos");

        m1.reproduzir();
        m1.reproduzir();
        m1.reproduzir();
        verificar(m1.getReproducoes() == 3, "Reprodução deve incrementar em 1");
    }

    private static void testarBuscaEReproducaoEmMassa() {
        Plataforma plataformaLocal = new Plataforma();
        Musica m1 = new Musica("Titulos", "Cantor", 150);
        Musica m2 = new Musica("Outros", "Cantor", 180);

        plataformaLocal.cadastrarMusica(m1);
        plataformaLocal.cadastrarMusica(m2);

        verificar(plataformaLocal.buscarMusicaPorId(m1.getId()) == m1,
                "Busca por ID deve localizar a música correta");
        verificar(plataformaLocal.buscarMusicaPorId(m1.getId() + 9999) == null,
                "Busca por ID inexistente deve retornar null");
        verificar(plataformaLocal.buscarMusica("Outros") == m2,
                "Busca por título deve localizar a música correta");
        verificar(plataformaLocal.buscarMusica("Inexistente") == null,
                "Busca por título inexistente deve retornar null");

        Usuario dono = new Usuario("Dina", "dina@email.com");
        Playlist playlist = new Playlist("Mix", dono);
        playlist.adicionar(m1);
        playlist.adicionar(m2);
        playlist.reproduzirTudo();

        verificar(m1.getReproducoes() == 1 && m2.getReproducoes() == 1,
                "reproduzirTudo deve tocar todas as músicas da playlist");
    }

    private static void testarRemocaoSemBuraco() {
        Usuario dono = new Usuario("Elias", "elias@email.com");
        Playlist playlist = new Playlist("Remover", dono);

        Musica m1 = new Musica("Um", "A", 100);
        Musica m2 = new Musica("Dois", "A", 100);
        Musica m3 = new Musica("Tres", "A", 100);

        playlist.adicionar(m1);
        playlist.adicionar(m2);
        playlist.adicionar(m3);

        verificar(playlist.removerNaPosicao(0), "Remoção do início deve funcionar");
        verificar(playlist.getQuantidade() == 2, "Quantidade após remoção deve cair para 2");
        verificar(playlist.getNaPosicao(0) == m2 && playlist.getNaPosicao(1) == m3,
                "Elementos após remoção devem ficar contíguos");

        try {
            playlist.removerNaPosicao(99);
            throw new IllegalStateException("Índice fora do intervalo deve lançar exceção");
        } catch (IndexOutOfBoundsException e) {
            verificar(e.getMessage() != null && !e.getMessage().trim().isEmpty(),
                    "Mensagem da exceção de índice inválido deve ser clara");
        }
    }

    private static void testarPlaylistCheia() {
        Usuario dono = new Usuario("Fernanda", "fernanda@email.com");
        Playlist playlist = new Playlist("Cheia", dono);

        for (int i = 0; i < 100; i++) {
            Musica musica = new Musica("Musica " + i, "Artista", 30);
            verificar(playlist.adicionar(musica), "Adicionar música até a capacidade deve funcionar");
        }

        Musica extra = new Musica("Extra", "Artista", 30);
        verificar(!playlist.adicionar(extra), "A 101ª música não deve entrar");
        verificar(playlist.getQuantidade() == 100, "Quantidade da playlist cheia deve continuar 100");
    }

    private static void testarPlataforma() {
        Plataforma plataformaLocal = new Plataforma();
        Musica m1 = new Musica("Musica Plata", "Artista", 120);
        Musica m2 = new Musica("Outra", "Artista", 180);

        verificar(plataformaLocal.cadastrarMusica(m1), "Cadastro de música deve funcionar");
        verificar(plataformaLocal.cadastrarMusica(m2), "Cadastro de música deve funcionar");
        verificar(plataformaLocal.getTotalMusicas() == 2, "Total de músicas deve ser 2");

        Usuario u1 = new Usuario("Gabriel", "gabriel@email.com");
        Usuario u2 = new Usuario("Helena", "helena@email.com");
        verificar(plataformaLocal.cadastrarUsuario(u1), "Cadastro de usuário deve funcionar");
        verificar(plataformaLocal.cadastrarUsuario(u2), "Cadastro de usuário deve funcionar");
        verificar(plataformaLocal.getTotalUsuarios() == 2, "Total de usuários deve ser 2");
    }

    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new IllegalStateException(mensagem);
        }
    }
}
