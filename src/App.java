import java.util.Scanner;

import helpers.ScannerHelper;
import helpers.Writer;
import model.Musica;
import model.Plataforma;
import model.Playlist;
import model.Usuario;

public class App {
    
    private static ScannerHelper scannerHelper = new ScannerHelper(new Scanner(System.in));
    private static Plataforma plataforma = new Plataforma();

    public static void main(String[] args) {
        mostrarMenu();
    }

    private static void mostrarMenu() {

        int opcao = -1;
        while (opcao != 0) {
            Writer.EscreverNovaLinha("===== Sonora =====");
            Writer.EscreverNovaLinha("1 - Cadastrar música manualmente");
            Writer.EscreverNovaLinha("2 - Cadastrar usuário");
            Writer.EscreverNovaLinha("3 - Criar playlist e adicionar músicas");
            Writer.EscreverNovaLinha("4 - Buscar música por id");
            Writer.EscreverNovaLinha("5 - Buscar música por título");
            Writer.EscreverNovaLinha("6 - Reproduzir uma música");
            Writer.EscreverNovaLinha("7 - Listar acervo");
            Writer.EscreverNovaLinha("0 - Sair");

            opcao = scannerHelper.lerInt("Escolha uma opção: ", "Você precisa digitar um número! Por favor, tente novamente: ", 0, 7);

            switch (opcao) {
                case 1:
                    cadastrarMusicaManual();
                    break;
                case 2:
                    cadastrarUsuarioManual();
                    break;
                case 3:
                    criarPlaylist();
                    break;
                case 4:
                    buscarMusicaPorId();
                    break;
                case 5:
                    buscarMusicaPorTitulo();
                    break;
                case 6:
                    reproduzirMusica();
                    break;
                case 7:
                    listarAcervo();
                    break;
                case 0:
                    Writer.EscreverNovaLinha("Ok! Encerrando ... ");
                    break;
                default:
                    Writer.EscreverNovaLinha("Ops, opção inválida! Tente novamente ...");
                    break;
            }
        }
        scannerHelper.fecharScanner();
    }

    private static void cadastrarMusicaManual() {
        Writer.EscreverNovaLinha("== Iniciado cadastro de nova música ==");

        String titulo = scannerHelper.lerLinha("Título: ");
        String artista = scannerHelper.lerLinha("Artista: ");
        int duracao = scannerHelper.lerInt("Duração em segundos: ", "Duração inválida!", 1);

        Musica musica = new Musica(titulo, artista, duracao);
        if (cadastrarMusicaNaPlataforma(musica)) {
            Writer.EscreverNovaLinha("Música cadastrada com sucesso e com id " + musica.getId());
        } else {
            Writer.EscreverNovaLinha("Não foi possível cadastrar a música. Acervo cheio ou dados inválidos.");
        }
    }

    private static void cadastrarUsuarioManual() {
        Writer.EscreverNovaLinha("== Início do cadastro de novo usuário ==");
        String nome = scannerHelper.lerLinha("Nome: ");
        String email = scannerHelper.lerLinha("Email: ");

        Usuario usuario = new Usuario(nome, email);
        if (cadastrarUsuarioNaPlataforma(usuario)) {
            Writer.EscreverNovaLinha("Usuário cadastrado com sucesso e com id " + usuario.getId());
        } else {
            Writer.EscreverNovaLinha("Não foi possível cadastrar o usuário. Base cheia ou dados inválidos.");
        }
    }

    private static void criarPlaylist() {

        if(plataforma.getTotalUsuarios() == 0){
            Writer.EscreverNovaLinha("Nenhum usuário cadastrado na plataforma. A criação de playlists exige um usuário dono.");
            return;
        }

        if(plataforma.getTotalMusicas() == 0){
            Writer.EscreverNovaLinha("Nenhuma música cadastrada na plataforma. A criação de playlists exige pelo menos uma música.");
            return;
        }

        Writer.EscreverNovaLinha("== Início de criação de playlist ==");
        String nome = scannerHelper.lerLinha("Nome da playlist: ");
        int donoId = scannerHelper.lerInt("Id do dono da playlist", "Valor inválido. Tente novamente ...", 0); 

        Usuario dono = plataforma.buscarUsuario(donoId);
        if (dono == null) {
            Writer.EscreverNovaLinha("Usuário não encontrado.");
            return;
        }

        Playlist playlist = new Playlist(nome, dono);

        cadastrarMusicasNaPlaylist(playlist);
        Writer.EscreverNovaLinha("Playlist " + playlist.getNome() + " de " + playlist.getDono().getNome() + " criada com " + playlist.getQuantidade() + " músicas.");
        Writer.EscreverNovaLinha("Músicas na playlist:");
        for (int i = 0; i < playlist.getQuantidade(); i++) {
            Musica musica = playlist.getNaPosicao(i);
            Writer.EscreverNovaLinha("ID" + musica.getId() + " - " + musica.getTitulo() + " de " + musica.getArtista() + ", " + musica.getDuracaoFormatada());
        }
    }

    private static void cadastrarMusicasNaPlaylist(Playlist playlist) {
        Writer.EscreverNovaLinha("== Adicionando músicas à playlist ==");
        Writer.EscreverNovaLinha("Digite os títulos das músicas para adicionar na playlist ou 'fim' para finalizar essa etapa:");

        while (true) {
            String titulo = scannerHelper.lerLinha("Título da música: ");
            if (titulo.equalsIgnoreCase("fim")) {
                break;
            }

            Musica musica = buscarMusicaNaPlataformaPorTitulo(titulo);
            if (musica == null) {
                Writer.EscreverNovaLinha("Título não encontrado! Tente novamente ...");
                continue;
            }

            if (playlist.adicionar(musica)) {
                Writer.EscreverNovaLinha("Música adicionada: " + musica.getTitulo());
            } else {
                Writer.EscreverNovaLinha("Não foi possível adicionar mais músicas nesta playlist.");
                break;
            }
        }
    }

    private static void buscarMusicaPorId() {
        int id = scannerHelper.lerInt("Digite o ID da música: ", "ID inválido. Tente novamente ...", 0);
        Musica musica = buscarMusicaNaPlataformaPorId(id);
        if (musica == null) {
            Writer.EscreverNovaLinha("Música não encontrada.");
        } else {
            Writer.EscreverNovaLinha("(id" + musica.getId() + ") Música encontrada: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "' e duração de " + musica.getDuracaoFormatada());
        }
    }

    private static void buscarMusicaPorTitulo() {
        String titulo = scannerHelper.lerLinha("Digite o título da música: ");
        Musica musica = buscarMusicaNaPlataformaPorTitulo(titulo);
        if (musica == null) {
            Writer.EscreverNovaLinha("Música não encontrada.");
        } else {
            Writer.EscreverNovaLinha("(id" + musica.getId() + ") Música encontrada: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "'' e duração de " + musica.getDuracaoFormatada());
        }
    }

    private static void reproduzirMusica() {
        int id = scannerHelper.lerInt("Digite o ID da música: ", "ID inválido. Tente novamente ...", 0);
        Musica musica = buscarMusicaNaPlataformaPorId(id);
        if (musica == null) {
            Writer.EscreverNovaLinha("Música não encontrada.");
        } else {
            musica.reproduzir();
            Writer.EscreverNovaLinha("Música reproduzida: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "'' e duração de " + musica.getDuracaoFormatada());
            Writer.EscreverNovaLinha("Total de reproduções: " + musica.getReproducoes());
        }
    }

    private static void listarAcervo() {
        Writer.EscreverNovaLinha("== Início da listagem de músicas do acervo ==");
        for(Musica musica : plataforma.getMusicas()){
            if(musica != null){
                Writer.EscreverNovaLinha("(id" + musica.getId() + ") " + musica.getTitulo() + " do artista '" + musica.getArtista() + "' e duração de " + musica.getDuracaoFormatada());
            }
        }
        Writer.EscreverNovaLinha("== Fim da listagem com " + plataforma.getTotalMusicas() + " músicas ==");
    }

    private static boolean cadastrarMusicaNaPlataforma(Musica musica) {
        return plataforma.cadastrarMusica(musica);
    }

    private static boolean cadastrarUsuarioNaPlataforma(Usuario usuario) {
        return plataforma.cadastrarUsuario(usuario);
    }

    private static Musica buscarMusicaNaPlataformaPorId(int id) {
        return plataforma.buscarMusicaPorId(id);
    }

    private static Musica buscarMusicaNaPlataformaPorTitulo(String titulo) {
        return plataforma.buscarMusica(titulo);
    }
}