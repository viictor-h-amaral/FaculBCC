package model;

public class Plataforma {

    private Usuario[] usuarios = new Usuario[500];

    private Musica[] musicas = new Musica[500];

    public Musica[] getMusicas() {
        return musicas;
    }

    public int getTotalUsuarios() {
        int quantidadeUsuarios = 0;
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] != null) quantidadeUsuarios++;
        }
        return quantidadeUsuarios;
    }

    public boolean cadastrarUsuario(Usuario usuario){
        if(usuario == null) return false;

        int proximoIndexDisponivel = proximoIndexUsuariosVazio();
        if(proximoIndexDisponivel >= usuarios.length) return false;

        usuarios[proximoIndexDisponivel] = usuario;
        return true;
    }

    private int proximoIndexUsuariosVazio(){
        int proximoIndex = usuarios.length;
        for (int i = 0; i < usuarios.length; i++){
            if(usuarios[i] == null){
                proximoIndex = i;
                break;
            }
        }
        return proximoIndex;           
    }

    public int getTotalMusicas() {
        int quantidadeMusicas = 0;
        for(int i = 0; i < musicas.length; i++){
            if(musicas[i] != null) quantidadeMusicas++;
        }
        return quantidadeMusicas;
    }

    public boolean cadastrarMusica(Musica musica){
        if(musica == null) return false;

        int proximoIndexDisponivel = proximoIndexMusicasVazio();
        if(proximoIndexDisponivel >= musicas.length) return false;

        musicas[proximoIndexDisponivel] = musica;
        return true;
    }

    private int proximoIndexMusicasVazio(){
        int proximoIndex = musicas.length;
        for (int i = 0; i < musicas.length; i++){
            if(musicas[i] == null){
                proximoIndex = i;
                break;
            }
        }
        return proximoIndex;           
    }

    public Musica buscarMusicaPorId(int id){
        Musica musicaProcurada = null;

        for(int i = 0; i < musicas.length; i++){
            if(musicas[i] != null && musicas[i].getId() == id) {
                musicaProcurada = musicas[i];
                break;
            }
        }

        return musicaProcurada;
    }

    public Musica buscarMusica(String titulo){
        Musica musicaProcurada = null;

        for(int i = 0; i < musicas.length; i++){
            if(musicas[i] != null && musicas[i].getTitulo().equalsIgnoreCase(titulo)) {
                musicaProcurada = musicas[i];
                break;
            }
        }

        return musicaProcurada;
    }

    public Usuario buscarUsuario(int id){
        Usuario usuarioProcurado = null;

        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] != null && usuarios[i].getId() == id) {
                usuarioProcurado = usuarios[i];
                break;
            }
        }

        return usuarioProcurado;
    }

    public Usuario buscarUsuario(String nome){
        Usuario usuarioProcurado = null;

        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] != null && usuarios[i].getNome().equalsIgnoreCase(nome)) {
                usuarioProcurado = usuarios[i];
                break;
            }
        }

        return usuarioProcurado;
    }


}
