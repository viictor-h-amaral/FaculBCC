package model;
import java.util.ArrayList;
import contracts.Conteudo;
import helpers.Writer;

public class Plataforma {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Conteudo> conteudos = new ArrayList<>();
    private ArrayList<Anuncio> anuncios = new ArrayList<>();

    public ArrayList<Usuario> getUsuarios(){
        return usuarios;
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    public boolean cadastrarUsuario(Usuario usuario) throws IllegalArgumentException {
        if(usuario == null) 
            throw new IllegalArgumentException("Usuário inválido! O usuário não deve ser nulo.");

        usuarios.add(usuario);
        return true;
    }

    public boolean cadastrarUsuarios(ArrayList<Usuario> usuarios) throws IllegalArgumentException {
        for (Usuario usuario : usuarios) {
            cadastrarUsuario(usuario);
        }
        return true;
    }

    public Usuario buscarUsuario(int id){
        Usuario usuarioProcurado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuarioProcurado = usuario;
                break;
            }
        }

        return usuarioProcurado;
    }

    public Usuario buscarUsuario(String nome){
        Usuario usuarioProcurado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                usuarioProcurado = usuario;
                break;
            }
        }

        return usuarioProcurado;
    }

    public double calcularReceitaMensal() {
        double receitaTotal = 0.0;

        for (Usuario usuario : usuarios) {
            receitaTotal += usuario.getPlano().calcularMensalidade();
        }

        return receitaTotal;
    }
    
    public ArrayList<Conteudo> getConteudos() {
        return conteudos;
    }

    public int getTotalConteudos() {
        return conteudos.size();
    }

    public boolean cadastrarConteudo(Conteudo conteudo) throws IllegalArgumentException {
        if(conteudo == null) 
            throw new IllegalArgumentException("Conteúdo inválido! O conteúdo não deve ser nulo.");

        conteudos.add(conteudo);
        return true;
    }

    public boolean cadastrarConteudos(ArrayList<Conteudo> conteudos) throws IllegalArgumentException {
        for (Conteudo conteudo : conteudos) {
            cadastrarConteudo(conteudo);
        }
        return true;
    }

    public Conteudo buscarConteudoPorId(int id){
        Conteudo conteudoProcurado = null;

        for (Conteudo conteudo : conteudos) {
            if (conteudo.getId() == id) {
                conteudoProcurado = conteudo;
                break;
            }
        }

        return conteudoProcurado;
    }

    public Conteudo buscarConteudo(String titulo){
        Conteudo conteudoProcurado = null;

        for (Conteudo conteudo : conteudos) {
            if (conteudo.getTitulo().equalsIgnoreCase(titulo)) {
                conteudoProcurado = conteudo;
                break;
            }
        }

        return conteudoProcurado;
    }

    public void listarEpisodiosDe(String nomeApresentador){
        for (Conteudo conteudo : conteudos) {
            if (!(conteudo instanceof Podcast)){
                continue;
            }
            Podcast podcast = (Podcast) conteudo;
            if (podcast.getApresentador().equalsIgnoreCase(nomeApresentador)) {
                Writer.escreverNovaLinha(podcast.toString());
            }
        }
    }

    public boolean cadastrarAnuncio(Anuncio anuncio) throws IllegalArgumentException {
        if(anuncio == null) 
            throw new IllegalArgumentException("Anúncio inválido! O anúncio não deve ser nulo.");

        anuncios.add(anuncio);
        return true;
    }

    public boolean cadastrarAnuncios(ArrayList<Anuncio> anuncios) throws IllegalArgumentException {
        for (Anuncio anuncio : anuncios) {
            cadastrarAnuncio(anuncio);
        }
        return true;
    }

    public Anuncio buscarAnuncio(String titulo){
        Anuncio anuncioProcurado = null;

        for (Anuncio anuncio : anuncios) {
            if (anuncio.getTitulo().equalsIgnoreCase(titulo)) {
                anuncioProcurado = anuncio;
                break;
            }
        }

        return anuncioProcurado;
    }
}
