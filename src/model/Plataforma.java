package model;

public class Plataforma {

    private Usuario[] usuarios = new Usuario[500];

    private Musica[] musicas = new Musica[500];

    public Musica[] getMusicas() {
        return musicas;
    }

    public Usuario[] getUsuarios(){
        return usuarios;
    }

    public int getTotalUsuarios() {
        int quantidadeUsuarios = 0;
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] != null) quantidadeUsuarios++;
            else break;
        }
        return quantidadeUsuarios;
    }

    public boolean cadastrarUsuario(Usuario usuario) throws IllegalArgumentException {
        if(usuario == null) 
            throw new IllegalArgumentException("Usuário inválido! O usuário não deve ser nulo.");

        int proximoIndexDisponivel = proximoIndexUsuariosVazio();
        if(proximoIndexDisponivel >= usuarios.length) 
            return false;

        usuarios[proximoIndexDisponivel] = usuario;
        return true;
    }

    public boolean cadastrarUsuarios(Usuario[] usuarios) throws IllegalArgumentException {
        if (this.getTotalUsuarios() + usuarios.length > this.usuarios.length)
            return false;

        for(var usuario : usuarios){
            cadastrarUsuario(usuario);
        }
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

    public boolean cadastrarMusica(Musica musica) throws IllegalArgumentException {
        if(musica == null) 
            throw new IllegalArgumentException("Música inválida! A música não deve ser nula.");

        int proximoIndexDisponivel = proximoIndexMusicasVazio();
        if(proximoIndexDisponivel >= musicas.length) 
            return false;

        musicas[proximoIndexDisponivel] = musica;
        return true;
    }

    public boolean cadastrarMusicas(Musica[] musicas) throws IllegalArgumentException {
        if (this.getTotalMusicas() + musicas.length > this.musicas.length)
            return false;

        for(var musica : musicas){
            cadastrarMusica(musica);
        }
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

        for(int i = 0; i < getTotalMusicas(); i++){
            if(musicas[i] != null && musicas[i].getId() == id) {
                musicaProcurada = musicas[i];
                break;
            }
        }

        return musicaProcurada;
    }

    public Musica buscarMusica(String titulo){
        Musica musicaProcurada = null;

        for(int i = 0; i < getTotalMusicas(); i++){
            if(musicas[i] != null && musicas[i].getTitulo().equalsIgnoreCase(titulo)) {
                musicaProcurada = musicas[i];
                break;
            }
        }

        return musicaProcurada;
    }

    public Usuario buscarUsuario(int id){
        Usuario usuarioProcurado = null;

        for(int i = 0; i < getTotalUsuarios(); i++){
            if(usuarios[i] != null && usuarios[i].getId() == id) {
                usuarioProcurado = usuarios[i];
                break;
            }
        }

        return usuarioProcurado;
    }

    public Usuario buscarUsuario(String nome){
        Usuario usuarioProcurado = null;

        for(int i = 0; i < getTotalUsuarios(); i++){
            if(usuarios[i] != null && usuarios[i].getNome().equalsIgnoreCase(nome)) {
                usuarioProcurado = usuarios[i];
                break;
            }
        }

        return usuarioProcurado;
    }
}
