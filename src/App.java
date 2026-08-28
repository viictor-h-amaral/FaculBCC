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

            opcao = scannerHelper.lerInt("Escolha uma opção: ", 0, 7);

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
        int duracao = scannerHelper.lerInt("Duração em segundos: ", 1);

        try {
            Musica musica = new Musica(titulo, artista, duracao);
            if (cadastrarMusicaNaPlataforma(musica)) {
                Writer.EscreverNovaLinha("Música cadastrada com sucesso e com id " + musica.getId());
            } else {
                Writer.EscreverNovaLinha("Não foi possível cadastrar a música. Acervo cheio ou dados inválidos.");
            }
        } catch (IllegalArgumentException e) {
            Writer.EscreverNovaLinha("Erro ao cadastrar música: " + e.getMessage());
        } catch (Exception e) {
            Writer.EscreverNovaLinha("Erro! stack em: " + e.getMessage());
        }
    }

    private static void cadastrarUsuarioManual() {
        Writer.EscreverNovaLinha("== Início do cadastro de novo usuário ==");
        String nome = scannerHelper.lerLinha("Nome: ");
        String email = scannerHelper.lerLinha("Email: ");

        try {
            Usuario usuario = new Usuario(nome, email);
            if(cadastrarUsuarioNaPlataforma(usuario)) {
                Writer.EscreverNovaLinha("Usuário cadastrado com sucesso e com id " + usuario.getId());
            } else {
                Writer.EscreverNovaLinha("Não foi possível cadastrar o usuário pois a base está cheia.");
            }
        } catch (IllegalArgumentException e) {
            Writer.EscreverNovaLinha("Erro ao cadastrar usuário: " + e.getMessage());
        } catch (Exception e) {
            Writer.EscreverNovaLinha("Erro! stack em: " + e.getMessage());
        }
    }

    private static void criarPlaylist() {
        Writer.EscreverNovaLinha("== Início de criação de playlist ==");
        try{
            ValidarCriacaoPlaylist();

            String nome = scannerHelper.lerLinha("Nome da playlist: ");
            int donoId = scannerHelper.lerInt("Id do dono da playlist", 0); 

            Usuario dono = plataforma.buscarUsuario(donoId);
            if (dono == null) {
                throw new IllegalArgumentException("Usuário com id " + donoId + " não encontrado.");
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
        catch (IllegalStateException e){
            Writer.EscreverNovaLinha("Dados da plataforma insuficientes! Detalhamento: " + e.getMessage());
        }
        catch (IllegalArgumentException e){
            Writer.EscreverNovaLinha("Algum valor fornecido estava inválido! Detalhamento: " + e.getMessage());
        }
        catch (Exception e){
            Writer.EscreverNovaLinha("Erro! Stack: " + e.getMessage());
        }
        finally{
            Writer.EscreverNovaLinha("Finalização do processo de criação de playlist.");
        }
    }

    //lançamento de illegalstateexception sinaliza que a criação da playlist foi feita em um estado ruim/inconsistente da plataforma
    private static void ValidarCriacaoPlaylist() throws IllegalStateException {
        if(plataforma.getTotalUsuarios() == 0){
            throw new IllegalStateException("Nenhum usuário cadastrado na plataforma. A criação de playlists exige um usuário dono.");
        }

        if(plataforma.getTotalMusicas() == 0){
            throw new IllegalStateException("Nenhuma música cadastrada na plataforma. A criação de playlists exige pelo menos uma música.");
        }
    }

    private static void cadastrarMusicasNaPlaylist(Playlist playlist) {
        Writer.EscreverNovaLinha("== Adicionando músicas à playlist ==");
        Writer.EscreverNovaLinha("Digite os títulos das músicas para adicionar na playlist ou 'fim' para finalizar essa etapa:");

        while (true) {
            try {
                String titulo = scannerHelper.lerLinha("Título da música: ");
                if (titulo.equalsIgnoreCase("fim")) {
                    break;
                }

                Musica musica = buscarMusicaNaPlataformaPorTitulo(titulo);

                if (musica == null) 
                    throw new IllegalArgumentException("Música com o título informado não foi encontrada!");

                if (playlist.adicionar(musica)) {
                    Writer.EscreverNovaLinha("Música adicionada: " + musica.getTitulo());
                } 
                else {
                    Writer.EscreverNovaLinha("Não é possível adicionar mais músicas nesta playlist.");
                    break;
                }
            } catch (IllegalArgumentException e) {
                Writer.EscreverNovaLinha("Algum valor informado estava inválido! Detalhamento: " + e.getMessage());
            }
            catch (Exception e){
                Writer.EscreverNovaLinha("Erro! Stack em: " + e.getMessage());
            }
        }
        Writer.EscreverNovaLinha("Finalizando processo de adição de músicas.");
    }

    private static void buscarMusicaPorId() {
        try{
            Musica musica = buscarMusicaPorIdInternal();
            Writer.EscreverNovaLinha("(id" + musica.getId() + ") Música encontrada: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "' e duração de " + musica.getDuracaoFormatada());
        }
        catch (NullPointerException e){
            Writer.EscreverNovaLinha("Id informado não corresponde à nenhuma música.");
        }
        catch (Exception e){
            Writer.EscreverNovaLinha("Erro! Stack em: " + e.getMessage());
        }
    }

    private static Musica buscarMusicaPorIdInternal() {
        int id = scannerHelper.lerInt("Digite o ID da música: ", 0);
        return buscarMusicaNaPlataformaPorId(id);
    }

    private static void buscarMusicaPorTitulo() {
        try{
            String titulo = scannerHelper.lerLinha("Digite o título da música: ");
            Musica musica = buscarMusicaNaPlataformaPorTitulo(titulo);
            Writer.EscreverNovaLinha("(id" + musica.getId() + ") Música encontrada: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "'' e duração de " + musica.getDuracaoFormatada());
        }
        catch (NullPointerException e){
            Writer.EscreverNovaLinha("Título informado não corresponde à nenhuma música.");
        }
        catch (Exception e){
            Writer.EscreverNovaLinha("Erro! Stack em: " + e.getMessage());
        }
    }

    private static void reproduzirMusica() {
        try{
            Musica musica = buscarMusicaPorIdInternal();
            musica.reproduzir();
            Writer.EscreverNovaLinha("Música reproduzida: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "'' e duração de " + musica.getDuracaoFormatada());
            Writer.EscreverNovaLinha("Total de reproduções: " + musica.getReproducoes());
        }
        catch (NullPointerException e){
            Writer.EscreverNovaLinha("Título informado não corresponde à nenhuma música.");
        }
        catch (Exception e){
            Writer.EscreverNovaLinha("Erro! Stack em: " + e.getMessage());
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

    private static boolean cadastrarMusicaNaPlataforma(Musica musica) throws IllegalArgumentException {
        return plataforma.cadastrarMusica(musica);
    }

    private static boolean cadastrarUsuarioNaPlataforma(Usuario usuario) throws IllegalArgumentException {
        return plataforma.cadastrarUsuario(usuario);
    }

    private static Musica buscarMusicaNaPlataformaPorId(int id) {
        return plataforma.buscarMusicaPorId(id);
    }

    private static Musica buscarMusicaNaPlataformaPorTitulo(String titulo) {
        return plataforma.buscarMusica(titulo);
    }
}