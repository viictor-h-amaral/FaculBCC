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
            Writer.escreverNovaLinha("===== Sonora =====");
            Writer.escreverNovaLinha("1 - Cadastrar música manualmente");
            Writer.escreverNovaLinha("2 - Cadastrar usuário");
            Writer.escreverNovaLinha("3 - Criar playlist e adicionar músicas");
            Writer.escreverNovaLinha("4 - Buscar música por id");
            Writer.escreverNovaLinha("5 - Buscar música por título");
            Writer.escreverNovaLinha("6 - Reproduzir uma música");
            Writer.escreverNovaLinha("7 - Listar acervo");
            Writer.escreverNovaLinha("8 - Listar usuários");
            Writer.escreverNovaLinha("0 - Sair");

            opcao = scannerHelper.lerInt("Escolha uma opção: ", 0, 8);

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
                case 8:
                    listarUsuarios();
                    break;
                case 0:
                    Writer.escreverNovaLinha("Ok! Encerrando ... ");
                    break;
                default:
                    Writer.escreverNovaLinha("Ops, opção inválida! Tente novamente ...");
                    break;
            }
        }
        scannerHelper.fecharScanner();
    }

    private static void cadastrarMusicaManual() {
        Writer.escreverNovaLinha("== Iniciado cadastro de nova música ==");

        String titulo = scannerHelper.lerLinha("Título: ");
        String artista = scannerHelper.lerLinha("Artista: ");
        int duracao = scannerHelper.lerInt("Duração em segundos: "); //removido valor minimo para testar Validação no construtor

        try {
            Musica musica = new Musica(titulo, artista, duracao);
            if (cadastrarMusicaNaPlataforma(musica)) {
                Writer.escreverNovaLinha("Música cadastrada com sucesso e com id " + musica.getId());
            } else {
                Writer.escreverNovaLinha("Não foi possível cadastrar a música. Acervo cheio ou dados inválidos.");
            }
        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Erro ao cadastrar música: " + e.getMessage());
        } catch (Exception e) {
            Writer.escreverErro("Erro! stack em: " + e.getMessage());
        }
    }

    private static void cadastrarUsuarioManual() {
        Writer.escreverNovaLinha("== Início do cadastro de novo usuário ==");
        String nome = scannerHelper.lerLinha("Nome: ");
        String email = scannerHelper.lerLinha("Email: ");

        try {
            Usuario usuario = new Usuario(nome, email);
            if(cadastrarUsuarioNaPlataforma(usuario)) {
                Writer.escreverNovaLinha("Usuário cadastrado com sucesso e com id " + usuario.getId());
            } else {
                Writer.escreverNovaLinha("Não foi possível cadastrar o usuário pois a base está cheia.");
            }
        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Erro ao cadastrar usuário: " + e.getMessage());
        } catch (Exception e) {
            Writer.escreverErro("Erro! stack em: " + e.getMessage());
        }
    }

    private static void criarPlaylist() {
        Writer.escreverNovaLinha("== Início de criação de playlist ==");
        try{
            validarCriacaoPlaylist();

            String nome = scannerHelper.lerLinha("Nome da playlist: ");

            listarUsuarios();

            int donoId = scannerHelper.lerInt("Id do dono da playlist", 0); 

            Usuario dono = plataforma.buscarUsuario(donoId);
            if (dono == null) {
                throw new NullPointerException("Usuário com id " + donoId + " não encontrado.");
            }

            Playlist playlist = new Playlist(nome, dono);

            cadastrarMusicasNaPlaylist(playlist);

            Writer.escreverNovaLinha("Playlist " + playlist.getNome() + " de " + playlist.getDono().getNome() + " criada com " + playlist.getQuantidade() + " músicas.");
            Writer.escreverNovaLinha("Músicas na playlist:");
            for (int i = 0; i < playlist.getQuantidade(); i++) {
                Musica musica = playlist.getNaPosicao(i);
                Writer.escreverNovaLinha("ID" + musica.getId() + " - " + musica.getTitulo() + " de " + musica.getArtista() + ", " + musica.getDuracaoFormatada());
            }
        }
        catch (IllegalStateException e){
            Writer.escreverErro("Dados da plataforma insuficientes! Detalhamento: " + e.getMessage());
        }
        catch (NullPointerException e){
            Writer.escreverErro("Algum valor fornecido estava inválido! Detalhamento: " + e.getMessage());
        }
        catch (Exception e){
            Writer.escreverErro("Erro! Stack: " + e.getMessage());
        }
        finally{
            Writer.escreverNovaLinha("Finalização do processo de criação de playlist.");
        }
    }

    //lançamento de illegalstateexception sinaliza que a criação da playlist foi feita em um estado ruim/inconsistente da plataforma
    private static void validarCriacaoPlaylist() throws IllegalStateException {
        if(plataforma.getTotalUsuarios() == 0){
            throw new IllegalStateException("Nenhum usuário cadastrado na plataforma. A criação de playlists exige um usuário dono.");
        }

        if(plataforma.getTotalMusicas() == 0){
            throw new IllegalStateException("Nenhuma música cadastrada na plataforma. A criação de playlists exige pelo menos uma música.");
        }
    }

    private static void cadastrarMusicasNaPlaylist(Playlist playlist) {
        Writer.escreverNovaLinha("== Adicionando músicas à playlist ==");

        listarAcervo();

        Writer.escreverNovaLinha("Digite os títulos das músicas para adicionar na playlist ou 'fim' para finalizar essa etapa:");

        while (true) {
            try {
                String titulo = scannerHelper.lerLinha("Título da música: ");
                if (titulo.equalsIgnoreCase("fim")) {
                    break;
                }

                Musica musica = buscarMusicaNaPlataformaPorTitulo(titulo);

                if (musica == null) {
                    Writer.escreverErro("Música não encontrada.");
                    return;
                }         

                if (playlist.adicionar(musica)) {
                    Writer.escreverNovaLinha("Música adicionada: " + musica.getTitulo());
                } 
                else {
                    Writer.escreverNovaLinha("Não é possível adicionar mais músicas nesta playlist.");
                    break;
                }
            } catch (IllegalArgumentException e) {
                Writer.escreverErro("Algum valor informado estava inválido! Detalhamento: " + e.getMessage());
            }
            catch (Exception e){
                Writer.escreverErro("Erro! Stack em: " + e.getMessage());
            }
        }
        Writer.escreverNovaLinha("Finalizando processo de adição de músicas.");
    }

    private static void buscarMusicaPorId() {
        try{
            int id = scannerHelper.lerInt("Digite o ID da música: ", 0);
            Musica musica = buscarMusicaNaPlataformaPorId(id);

            if (musica == null) {
                Writer.escreverErro("Música não encontrada.");
                return;
            }

            Writer.escreverNovaLinha("(id" + musica.getId() + ") Música encontrada: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "' e duração de " + musica.getDuracaoFormatada());
        }
        catch (Exception e){
            Writer.escreverErro("Erro! Stack em: " + e.getMessage());
        }
    }

    private static void buscarMusicaPorTitulo() {
        try{
            String titulo = scannerHelper.lerLinha("Digite o título da música: ");
            Musica musica = buscarMusicaNaPlataformaPorTitulo(titulo);

            if (musica == null) {
                Writer.escreverErro("Música não encontrada.");
                return;
            }

            Writer.escreverNovaLinha("(id" + musica.getId() + ") Música encontrada: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "' e duração de " + musica.getDuracaoFormatada());
        }
        catch (Exception e){
            Writer.escreverErro("Erro! Stack em: " + e.getMessage());
        }
    }

    private static void reproduzirMusica() {

        listarAcervo();

        try{
            String titulo = scannerHelper.lerLinha("Digite o título da música: ");
            Musica musica = buscarMusicaNaPlataformaPorTitulo(titulo);

            if (musica == null) {
                Writer.escreverErro("Música não encontrada.");
                return;
            }

            musica.reproduzir();
            Writer.escreverNovaLinha("Música reproduzida: " + musica.getTitulo() + " do artista '" + musica.getArtista() + "'' e duração de " + musica.getDuracaoFormatada());
            Writer.escreverNovaLinha("Total de reproduções: " + musica.getReproducoes());
        }
        catch (Exception e){
            Writer.escreverErro("Erro! Stack em: " + e.getMessage());
        }
        finally {
            Writer.escreverNovaLinha("Operação de reprodução finalizada.");
        }
    }

    private static void listarAcervo() {
        Writer.escreverNovaLinha("== Início da listagem de músicas do acervo ==");
        for(Musica musica : plataforma.getMusicas()){
            if(musica != null){
                Writer.escreverNovaLinha("(id" + musica.getId() + ") " + musica.getTitulo() + " do artista '" + musica.getArtista() + "' e duração de " + musica.getDuracaoFormatada());
            }
        }
        Writer.escreverNovaLinha("== Fim da listagem com " + plataforma.getTotalMusicas() + " músicas ==");
    }

    private static void listarUsuarios() {
        Writer.escreverNovaLinha("== Início da listagem de usuários ==");
        for(var usuario : plataforma.getUsuarios()){
            if(usuario != null){
                Writer.escreverNovaLinha("(id" + usuario.getId() + ") " + usuario.getNome() + " com email " + usuario.getEmail());
            }
        }
        Writer.escreverNovaLinha("== Fim da listagem com " + plataforma.getTotalUsuarios() + " usuários ==");
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