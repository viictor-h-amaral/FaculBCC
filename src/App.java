import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import contracts.Conteudo;
import contracts.Plano;
import helpers.ScannerHelper;
import helpers.Writer;
import model.Anuncio;
import model.Musica;
import model.Plataforma;
import model.Playlist;
import model.PlanoFamilia;
import model.PlanoGratuito;
import model.PlanoIndividual;
import model.Podcast;
import model.Usuario;

public class App {
    
    private static ScannerHelper scannerHelper = new ScannerHelper(new Scanner(System.in));
    private static Plataforma plataforma = new Plataforma();
    private static ArrayList<Playlist> playlists = new ArrayList<>();

    public static void main(String[] args) {

        demonstracaoFase7();

        //Tipo Estático: Conteudo. Tipo Dinamico: Podcast
        Conteudo c = new Podcast("Episódio", "Apresentador", 30, 2);
        //c.getApresentador(); // Erro de compilação! O getApresentador() não apenas existe na classe Podcast, não na classe Conteudo. E o tipo estático de 'c' é Conteudo

        mostrarMenu();
    }

    private static void demonstracaoFase7() {
        //deve mostrar que a receita mensal aumenta conforme anuncios sao exibidos por meio de reprodução da playlist

        var anuncios = new ArrayList<Anuncio>(Arrays.asList(
            new Anuncio("Anunciante 1", "Anúncio 1", 30, 10),
            new Anuncio("Anunciante 2", "Anúncio 2", 45, 20)
        ));

        plataforma.cadastrarAnuncios(anuncios);

        Writer.escreverNovaLinha("Receita mensal da plataforma antes de reproduzir playlist: R$ " + String.format("%.2f", plataforma.calcularReceitaMensal()));

        var usuario = new Usuario("Usuário Teste", "usuario@teste.com");
        plataforma.cadastrarUsuario(usuario);  

        var playlist = new Playlist("Playlist Teste", usuario);
        playlist.adicionar(
            new Musica("m1", "a1", 1), 
            new Musica("m2", "a2", 2),
            new Podcast("p1", "ap1", 3, 1),
            new Podcast("p2", "ap2", 3, 2)
        );

        plataforma.tocarPlaylist(usuario, playlist);

        Writer.escreverNovaLinha("Receita mensal da plataforma após reproduzir playlist: R$ " + String.format("%.2f", plataforma.calcularReceitaMensal()));
    }

    private static void mostrarMenu() {

        int opcao = -1;
        while (opcao != 0) {
            Writer.escreverNovaLinha("===== Sonora =====");
            Writer.escreverNovaLinha("1 - Cadastrar conteúdo manualmente");
            Writer.escreverNovaLinha("2 - Cadastrar usuário");
            Writer.escreverNovaLinha("3 - Criar playlist");
            Writer.escreverNovaLinha("4 - Buscar conteúdo por id");
            Writer.escreverNovaLinha("5 - Buscar conteúdo por título");
            Writer.escreverNovaLinha("6 - Reproduzir um conteúdo");
            Writer.escreverNovaLinha("7 - Listar acervo");
            Writer.escreverNovaLinha("8 - Listar usuários");
            Writer.escreverNovaLinha("9 - Seguir usuário");
            Writer.escreverNovaLinha("10 - Deixar de seguir usuário");
            Writer.escreverNovaLinha("11 - Listar usuários seguidos");
            Writer.escreverNovaLinha("12 - Trocar plano de usuário");
            Writer.escreverNovaLinha("13 - Exibir plano atual de usuário");
            Writer.escreverNovaLinha("14 - Cadastrar anúncio");
            Writer.escreverNovaLinha("15 - Adicionar conteúdo a uma playlist");
            Writer.escreverNovaLinha("16 - Tocar playlist para usuário");
            Writer.escreverNovaLinha("17 - Listar episódios de um apresentador (podcast)");
            Writer.escreverNovaLinha("18 - Exibir receita mensal da plataforma");
            Writer.escreverNovaLinha("0 - Sair");

            opcao = scannerHelper.lerInt("Escolha uma opção: ", 0, 18);

            switch (opcao) {
                case 1:
                    cadastrarConteudoManual();
                    break;
                case 2:
                    cadastrarUsuarioManual();
                    break;
                case 3:
                    criarPlaylist();
                    break;
                case 4:
                    buscarConteudoPorId();
                    break;
                case 5:
                    buscarConteudoPorTitulo();
                    break;
                case 6:
                    reproduzirConteudo();
                    break;
                case 7:
                    listarAcervo();
                    break;
                case 8:
                    listarUsuarios();
                    break;
                case 9:
                    seguirUsuario();
                    break;
                case 10:
                    deixarDeSeguirUsuario();
                    break;
                case 11:
                    listarUsuariosSeguidos();
                    break;
                case 12:
                    trocarPlanoUsuario();
                    break;
                case 13:
                    exibirPlanoUsuario();
                    break;
                case 14:
                    cadastrarAnuncioManual();
                    break;
                case 15:
                    adicionarConteudoAPlaylist();
                    break;
                case 16:
                    tocarPlaylist();
                    break;
                case 17:
                    listarEpisodiosPorApresentador();
                    break;
                case 18:
                    exibirReceitaMensal();
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

    private static void cadastrarConteudoManual() {
        Writer.escreverNovaLinha("== Iniciado cadastro de novo conteúdo ==");

        Writer.escreverNovaLinha("Escolha o tipo de conteúdo:");
        Writer.escreverNovaLinha("1 - Música");
        Writer.escreverNovaLinha("2 - Podcast");
        int tipoConteudo = scannerHelper.lerInt("Escolha uma opção: ", 1, 2);



        String titulo = scannerHelper.lerLinha("Título: ");
        String artista_autor = scannerHelper.lerLinha(tipoConteudo == 1 ? "Artista: " : "Autor: ");
        int duracao = scannerHelper.lerInt(tipoConteudo == 1 ? "Duração em segundos: " : "Duração em minutos: ", 0);

        try {
            Conteudo conteudo;
            if (tipoConteudo == 1) {
                conteudo = new Musica(titulo, artista_autor, duracao);
            } else {
                int numero_episodio = scannerHelper.lerInt("Número do episódio: ");
                conteudo = new Podcast(titulo, artista_autor, duracao, numero_episodio);
            }

            if (cadastrarConteudoNaPlataforma(conteudo)) {
                Writer.escreverNovaLinha("Conteúdo cadastrada com sucesso e com id " + conteudo.getId());
            } else {
                Writer.escreverNovaLinha("Não foi possível cadastrar o conteúdo. Acervo cheio ou dados inválidos.");
            }
        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Erro ao cadastrar conteúdo: " + e.getMessage());
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

            cadastrarConteudosNaPlaylist(playlist);
            playlists.add(playlist);

            Writer.escreverNovaLinha("Playlist " + playlist.getNome() + " de " + playlist.getDono().getNome() + " criada com " + playlist.getQuantidade() + " conteúdos.");
            Writer.escreverNovaLinha("Conteúdos na playlist:");
            for (int i = 0; i < playlist.getQuantidade(); i++) {
                Conteudo conteudo = playlist.getNaPosicao(i);
                if (conteudo != null && conteudo instanceof Musica) {
                    Musica musica = (Musica) conteudo;
                    Writer.escreverNovaLinha("ID" + musica.getId() + " - " + musica.getTitulo() + " de " + musica.getArtista() + ", " + musica.getDuracaoFormatada());
                } else if (conteudo != null && conteudo instanceof Podcast) {
                    Podcast podcast = (Podcast) conteudo;
                    Writer.escreverNovaLinha("ID" + podcast.getId() + " - " + podcast.getTitulo() + " de " + podcast.getApresentador() + ", episódio " + podcast.getNumeroEpisodio() + ", " + podcast.getDuracaoFormatada());
                } else if (conteudo != null) {
                    Writer.escreverNovaLinha("ID" + conteudo.getId() + " - " + conteudo.getTitulo() + ", com tempo de " + conteudo.getDuracaoFormatada());
                }
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

        if(plataforma.getTotalConteudos() == 0){
            throw new IllegalStateException("Nenhum conteúdo cadastrado na plataforma. A criação de playlists exige pelo menos um conteúdo.");
        }
    }

    private static void cadastrarConteudosNaPlaylist(Playlist playlist) {
        Writer.escreverNovaLinha("== Adicionando conteúdos à playlist ==");

        listarAcervo();

        Writer.escreverNovaLinha("Digite os títulos dos conteúdos para adicionar na playlist ou 'fim' para finalizar essa etapa:");

        while (true) {
            try {
                String titulo = scannerHelper.lerLinha("Título do conteúdo: ");
                if (titulo.equalsIgnoreCase("fim")) {
                    break;
                }

                Conteudo conteudo = buscarConteudoNaPlataformaPorTitulo(titulo);

                if (conteudo == null) {
                    Writer.escreverErro("Conteúdo não encontrado.");
                    return;
                }         

                playlist.adicionar(conteudo);
                Writer.escreverNovaLinha("Conteúdo adicionado: " + conteudo.getTitulo());
            } catch (IllegalArgumentException e) {
                Writer.escreverErro("Algum valor informado estava inválido! Detalhamento: " + e.getMessage());
            }
            catch (Exception e){
                Writer.escreverErro("Erro! Stack em: " + e.getMessage());
            }
        }
        Writer.escreverNovaLinha("Finalizando processo de adição de conteúdos.");
    }

    private static void buscarConteudoPorId() {
        try{
            int id = scannerHelper.lerInt("Digite o ID do conteúdo: ", 0);
            Conteudo conteudo = buscarConteudoNaPlataformaPorId(id);

            if (conteudo == null) {
                Writer.escreverErro("Conteúdo não encontrado.");
                return;
            }

            Writer.escreverNovaLinha("(id" + conteudo.getId() + ") Conteúdo encontrado: " + conteudo.getTitulo() + " com duração de " + conteudo.getDuracaoFormatada());
        }
        catch (Exception e){
            Writer.escreverErro("Erro! Stack em: " + e.getMessage());
        }
    }

    private static void buscarConteudoPorTitulo() {
        try{
            String titulo = scannerHelper.lerLinha("Digite o título do conteúdo: ");
            Conteudo conteudo = buscarConteudoNaPlataformaPorTitulo(titulo);

            if (conteudo == null) {
                Writer.escreverErro("Conteúdo não encontrado.");
                return;
            }

            Writer.escreverNovaLinha("(id" + conteudo.getId() + ") Conteúdo encontrado: " + conteudo.getTitulo() + " com duração de " + conteudo.getDuracaoFormatada());
        }
        catch (Exception e){
            Writer.escreverErro("Erro! Stack em: " + e.getMessage());
        }
    }

    private static void reproduzirConteudo() {

        listarAcervo();

        try{
            String titulo = scannerHelper.lerLinha("Digite o título do conteúdo: ");
            Conteudo conteudo = buscarConteudoNaPlataformaPorTitulo(titulo);

            if (conteudo == null) {
                Writer.escreverErro("Conteúdo não encontrado.");
                return;
            }

            conteudo.reproduzir();
            Writer.escreverNovaLinha("Conteúdo reproduzido: " + conteudo.getTitulo() + " com duração de " + conteudo.getDuracaoFormatada());
            Writer.escreverNovaLinha("Total de reproduções: " + conteudo.getReproducoes());
        }
        catch (Exception e){
            Writer.escreverErro("Erro! Stack em: " + e.getMessage());
        }
        finally {
            Writer.escreverNovaLinha("Operação de reprodução finalizada.");
        }
    }

    private static void listarAcervo() {
        Writer.escreverNovaLinha("== Início da listagem de conteúdos do acervo ==");
        for(Conteudo conteudo : plataforma.getConteudos()){
            if(conteudo != null){
                Writer.escreverNovaLinha("(id" + conteudo.getId() + ") " + conteudo.getTitulo() + " com duração de " + conteudo.getDuracaoFormatada());
            }
        }
        Writer.escreverNovaLinha("== Fim da listagem com " + plataforma.getTotalConteudos() + " conteúdos ==");
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

    private static void seguirUsuario() {
        try {
            Writer.escreverNovaLinha("== Seguir usuário ==");
            listarUsuarios();
            Usuario usuario = buscarUsuarioNaPlataformaPorId("ID do usuário que seguirá: ");
            Usuario outro = buscarUsuarioNaPlataformaPorId("ID do usuário a seguir: ");

            if (usuario.getSeguindo().contains(outro)) {
                Writer.escreverNovaLinha(usuario.getNome() + " já seguia " + outro.getNome() + ".");
                return;
            }

            usuario.seguir(outro);
            Writer.escreverNovaLinha(usuario.getNome() + " agora segue " + outro.getNome() + ".");

        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Erro ao seguir usuário: " + e.getMessage());
        } catch (Exception e) {
            Writer.escreverErro("Erro inesperado: " + e.getMessage());
        } finally {
            Writer.escreverNovaLinha("Finalização do processo de seguir usuário.");
        }
    }

    private static void deixarDeSeguirUsuario() {
        try {
            Writer.escreverNovaLinha("== Deixar de seguir usuário ==");
            Writer.escreverNovaLinha("Usuário disponíveis para seleção:");
            listarUsuarios();
            Usuario usuario = buscarUsuarioNaPlataformaPorId("ID do usuário que deixará de seguir: ");

            if(usuario.getQuantidadeSeguindo() == 0){
                Writer.escreverNovaLinha(usuario.getNome() + " não segue nenhum usuário.");
                return;
            }

            Writer.escreverNovaLinha("Usuários que " + usuario.getNome() + " está seguindo:");
            for (Usuario seguido : usuario.getSeguindo()) {
                Writer.escreverNovaLinha("(id" + seguido.getId() + ") " + seguido.getNome() + " com email " + seguido.getEmail());
            }
            Usuario outro = buscarUsuarioNaPlataformaPorId("ID do usuário a deixar de seguir: ");

            if (!usuario.getSeguindo().contains(outro)) {
                Writer.escreverNovaLinha(usuario.getNome() + " não seguia " + outro.getNome() + ".");
                return;
            }

            usuario.deixarDeSeguir(outro);
            Writer.escreverNovaLinha(usuario.getNome() + " deixou de seguir " + outro.getNome() + ".");

        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Erro ao deixar de seguir usuário: " + e.getMessage());
        } catch (Exception e) {
            Writer.escreverErro("Erro inesperado: " + e.getMessage());
        } finally {
            Writer.escreverNovaLinha("Finalização do processo de deixar de seguir usuário.");
        }
    }

    private static void listarUsuariosSeguidos() {
        try {
            Writer.escreverNovaLinha("== Usuários seguidos ==");
            Writer.escreverNovaLinha("Usuário disponíveis para seleção:");
            listarUsuarios();
            Usuario usuario = buscarUsuarioNaPlataformaPorId("ID do usuário: ");

            if (usuario.getQuantidadeSeguindo() == 0) {
                Writer.escreverNovaLinha(usuario.getNome() + " não segue nenhum usuário.");
                return;
            }

            for (Usuario seguido : usuario.getSeguindo()) {
                Writer.escreverNovaLinha("(id" + seguido.getId() + ") " + seguido.getNome() + " com email " + seguido.getEmail());
            }
        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Erro ao listar usuários seguidos: " + e.getMessage());
        } catch (Exception e) {
            Writer.escreverErro("Erro inesperado: " + e.getMessage());
        } finally {
            Writer.escreverNovaLinha("Finalização da listagem de usuários seguidos.");
        }
    }

    private static void trocarPlanoUsuario() {
        try {
            if (plataforma.getTotalUsuarios() == 0) {
                Writer.escreverNovaLinha("Cadastre um usuário antes de trocar o plano.");
                return;
            }

            Writer.escreverNovaLinha("== Trocar plano de usuário ==");
            listarUsuarios();
            Usuario usuario = buscarUsuarioNaPlataformaPorId("ID do usuário: ");

            var planoAtualUsuario = usuario.getPlano();
            var planosPlataforma = new ArrayList<String>(Arrays.asList("Gratuito", "Individual", "Família"));

            // fiz desse jeito (abaixo) pois não podia usar dicionários (map/hashmap)
            int contador = 1;
            int codigoPlanoGratuito = 0;
            int codigoPlanoIndividual = 0;
            for (var plano : planosPlataforma){
                if (plano.equals(planoAtualUsuario.getNome())) continue;
                Writer.escreverNovaLinha(contador + " - " + plano);
                if (plano.equals("Gratuito")) { codigoPlanoGratuito = contador ; }
                else if (plano.equals("Individual")) { codigoPlanoIndividual = contador ; }
                contador++;
            }

            int opcaoPlano = scannerHelper.lerInt("Escolha o plano: ", 1, 2);

            Plano novoPlano;
            if (opcaoPlano == codigoPlanoGratuito) {
                novoPlano = new PlanoGratuito();
            } else if (opcaoPlano == codigoPlanoIndividual) {
                novoPlano = new PlanoIndividual(lerPrecoPositivo("Preço mensal do plano individual: R$ "));
            } else {
                double preco = lerPrecoPositivo("Preço mensal do plano família: R$ ");
                int membros = scannerHelper.lerInt("Quantidade de membros (1 a 6): ", 1, 6);
                novoPlano = new PlanoFamilia(preco, membros);
            }

            usuario.assinar(novoPlano);
            Writer.escreverNovaLinha("Plano atualizado para " + usuario.getNome() + ": " + novoPlano.resumo());
        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Não foi possível trocar o plano: " + e.getMessage());
        } catch (Exception e) {
            Writer.escreverErro("Erro ao trocar plano: " + e.getMessage());
        } finally {
            Writer.escreverNovaLinha("Finalização da troca de plano.");
        }
    }

    private static double lerPrecoPositivo(String mensagem) {
        while (true) {
            String entrada = scannerHelper.lerLinha(mensagem).trim().replace(',', '.');
            try {
                double preco = Double.parseDouble(entrada);
                if (!Double.isFinite(preco) || preco <= 0) {
                    throw new IllegalArgumentException("Informe um preço positivo e finito.");
                }
                return preco;
            } catch (IllegalArgumentException e) {
                Writer.escreverErro("Preço inválido. Informe um número positivo, por exemplo 19,90.");
            }
        }
    }

    private static void exibirPlanoUsuario() {
        try {
            if (plataforma.getTotalUsuarios() == 0) {
                Writer.escreverNovaLinha("Ainda não há usuários cadastrados.");
                return;
            }

            Writer.escreverNovaLinha("== Plano atual do usuário ==");
            listarUsuarios();
            Usuario usuario = buscarUsuarioNaPlataformaPorId("ID do usuário: ");
            Plano plano = usuario.getPlano();

            Writer.escreverNovaLinha(usuario.getNome() + " - " + plano.resumo());
            Writer.escreverNovaLinha("Tem anúncios: " + (plano.temAnuncios() ? "sim" : "não"));
        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Não foi possível exibir o plano: " + e.getMessage());
        } catch (Exception e) {
            Writer.escreverErro("Erro ao exibir plano: " + e.getMessage());
        } finally {
            Writer.escreverNovaLinha("Finalização da consulta de plano.");
        }
    }

    private static Usuario buscarUsuarioNaPlataformaPorId(String mensagem) {
        int id = scannerHelper.lerInt(mensagem, 0);
        Usuario usuario = plataforma.buscarUsuario(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário com id " + id + " não encontrado.");
        }
        return usuario;
    }

    private static boolean cadastrarConteudoNaPlataforma(Conteudo conteudo) throws IllegalArgumentException {
        return plataforma.cadastrarConteudo(conteudo);
    }

    private static boolean cadastrarUsuarioNaPlataforma(Usuario usuario) throws IllegalArgumentException {
        return plataforma.cadastrarUsuario(usuario);
    }

    private static Conteudo buscarConteudoNaPlataformaPorId(int id) {
        return plataforma.buscarConteudoPorId(id);
    }

    private static Conteudo buscarConteudoNaPlataformaPorTitulo(String titulo) {
        return plataforma.buscarConteudo(titulo);
    }

    private static void cadastrarAnuncioManual() {
        Writer.escreverNovaLinha("== Início do cadastro de anúncio ==");
        String anunciante = scannerHelper.lerLinha("Anunciante: ");
        String titulo = scannerHelper.lerLinha("Título do anúncio: ");
        int duracaoSegundos = scannerHelper.lerInt("Duração em segundos: ", 0);
        int valorPorMilImpressoes = scannerHelper.lerInt("Valor por mil impressões (em reais): ", 0);

        try {
            Anuncio anuncio = new Anuncio(anunciante, titulo, duracaoSegundos, valorPorMilImpressoes);
            plataforma.cadastrarAnuncio(anuncio);
            Writer.escreverNovaLinha("Anúncio cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            Writer.escreverErro("Erro ao cadastrar anúncio: " + e.getMessage());
        } catch (Exception e) {
            Writer.escreverErro("Erro! Stack em: " + e.getMessage());
        }
    }

    private static void adicionarConteudoAPlaylist() {
        Writer.escreverNovaLinha("== Início do processo de adição de conteúdo a uma playlist ==");

        if (playlists.isEmpty()) {
            Writer.escreverNovaLinha("Não há playlists cadastradas. Crie uma playlist antes de adicionar conteúdos.");
            return;
        }

        Writer.escreverNovaLinha("Playlists disponíveis:");
        for (int i = 0; i < playlists.size(); i++) {
            Playlist playlist = playlists.get(i);
            Writer.escreverNovaLinha((i + 1) + " - " + playlist.getNome() + " (Dono: " + playlist.getDono().getNome() + ")");
        }

        int indicePlaylist = scannerHelper.lerInt("Escolha o número da playlist: ", 1, playlists.size()) - 1;
        Playlist playlistSelecionada = playlists.get(indicePlaylist);

        cadastrarConteudosNaPlaylist(playlistSelecionada);
    }

    private static void tocarPlaylist() {
        Writer.escreverNovaLinha("== Início do processo de tocar playlist para usuário ==");

        if (playlists.isEmpty()) {
            Writer.escreverNovaLinha("Não há playlists cadastradas. Crie uma playlist antes de tocar.");
            return;
        }
        else if (plataforma.getTotalUsuarios() == 0) {
            Writer.escreverNovaLinha("Não há usuários cadastrados. Cadastre um usuário antes de tocar uma playlist.");
            return;
        }

        Writer.escreverNovaLinha("Playlists disponíveis:");
        for (int i = 0; i < playlists.size(); i++) {
            Playlist playlist = playlists.get(i);
            Writer.escreverNovaLinha((i + 1) + " - " + playlist.getNome() + " (Dono: " + playlist.getDono().getNome() + ")");
        }

        try{
            int indicePlaylist = scannerHelper.lerInt("Escolha o número da playlist: ", 1, playlists.size()) - 1;
            Playlist playlistSelecionada = playlists.get(indicePlaylist);

            listarUsuarios();
            Usuario usuario = buscarUsuarioNaPlataformaPorId("ID do usuário que irá ouvir a playlist: ");

            plataforma.tocarPlaylist(usuario, playlistSelecionada);
        }
        catch (Exception e){
            Writer.escreverErro("Erro! Stack em: " + e.getMessage());
        }
        finally {
            Writer.escreverNovaLinha("== Finalizando processo de tocar playlist. ==");
        }
    }

    private static void listarEpisodiosPorApresentador() {
        Writer.escreverNovaLinha("== Início do processo de listagem de episódios por apresentador ==");

        var apresentadores = plataforma.listarApresentadoresDePodcast();

        if (apresentadores.isEmpty()) {
            Writer.escreverNovaLinha("Não há apresentadores de podcast cadastrados na plataforma.");
            return;
        }

        for (String apresentador : plataforma.listarApresentadoresDePodcast()) {
            Writer.escreverNovaLinha("- " + apresentador);
        }
        String apresentador = scannerHelper.lerLinha("Digite o nome do apresentador: ");
        ArrayList<Podcast> episodios = plataforma.listarEpisodiosDe(apresentador);

        if (episodios.isEmpty()) {
            Writer.escreverNovaLinha("Não há episódios de podcast cadastrados para o apresentador " + apresentador + ".");
            return;
        }

        for (Podcast episodio : episodios) {
            Writer.escreverNovaLinha(episodio.toString());
        }
        Writer.escreverNovaLinha("== Finalizando processo de listagem de episódios. ==");
    }

    private static void exibirReceitaMensal() {
        Writer.escreverNovaLinha("== Receita mensal da plataforma ==");
        double receitaMensal = plataforma.calcularReceitaMensal();
        Writer.escreverNovaLinha("Receita mensal: R$ " + String.format("%.2f", receitaMensal));
        Writer.escreverNovaLinha("== Finalizando exibição de receita mensal. ==");
    }
}