package model;

import java.util.ArrayList;

public class Plataforma {

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    private ArrayList<Musica> musicas = new ArrayList<>();

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

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

    public int getTotalMusicas() {
        return musicas.size();
    }

    public boolean cadastrarMusica(Musica musica) throws IllegalArgumentException {
        if(musica == null) 
            throw new IllegalArgumentException("Música inválida! A música não deve ser nula.");

        musicas.add(musica);
        return true;
    }

    public boolean cadastrarMusicas(ArrayList<Musica> musicas) throws IllegalArgumentException {
        for (Musica musica : musicas) {
            cadastrarMusica(musica);
        }
        return true;
    }

    public Musica buscarMusicaPorId(int id){
        Musica musicaProcurada = null;

        for (Musica musica : musicas) {
            if (musica.getId() == id) {
                musicaProcurada = musica;
                break;
            }
        }

        return musicaProcurada;
    }

    public Musica buscarMusica(String titulo){
        Musica musicaProcurada = null;

        for (Musica musica : musicas) {
            if (musica.getTitulo().equalsIgnoreCase(titulo)) {
                musicaProcurada = musica;
                break;
            }
        }

        return musicaProcurada;
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
}
