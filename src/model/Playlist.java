package model;

public class Playlist {

    private static void ValidarParametrosPlaylist(String nome, Usuario dono) throws IllegalArgumentException {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Ops. Nome inválido! O nome não deve ser vazio.");
        }
        else if (dono == null) {
            throw new IllegalArgumentException("Ops. Dono inválido! O dono não deve ser nulo.");
        }
    }

    public Playlist(String nome, Usuario dono) throws IllegalArgumentException {
        ValidarParametrosPlaylist(nome, dono);

        this.nome = nome;
        this.dono = dono;
    }

    private Usuario dono;

    public Usuario getDono() {
        return dono;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    private Musica[] musicas = new Musica[100];

    public int getQuantidade(){
        int quantidadeElementosNaoNulos = 0;
        for (Musica musica : musicas) {
            if (musica != null) {
                quantidadeElementosNaoNulos++;
            }
        }
        return quantidadeElementosNaoNulos;
    }

    public Musica getNaPosicao(int indice){
        if(indice < 0 || indice >= musicas.length){
            throw new IndexOutOfBoundsException("Ops. Índice inválido! O índice deve estar entre 0 e " + (musicas.length - 1) + ". Índice solicitado: " + indice);
        }

        return musicas[indice];
    }

    public boolean adicionar(Musica musica){

        if(musica == null)
            throw new IllegalArgumentException("Ops. Música inválida! A música não deve ser nula.");

        int index = this.proximoIndexVazio();

        if(index >= musicas.length)
            return false;

        musicas[index] = musica;
        return true;
    }

    private int proximoIndexVazio(){
        int proximoIndex = musicas.length;
        for (int i = 0; i < musicas.length; i++){
            if(musicas[i] == null){
                proximoIndex = i;
                break;
            }
        }
        return proximoIndex;           
    }

    public boolean removerNaPosicao(int indice){
        if(indice < 0 || indice >= musicas.length)
            throw new IndexOutOfBoundsException("Ops. Índice inválido! O índice deve estar entre 0 e " + (musicas.length - 1) + ". Índice solicitado: " + indice);

        if(musicas[indice] == null)
            return false;

        // reordena playlist E remove música na posição por meio de sobrescrita dos indices, indo até o penultimo
        for (int i = indice; i < musicas.length - 1; i++) {
            musicas[i] = musicas[i + 1];
        }

        //finaliza anulando a musica da ultima posição
        musicas[musicas.length - 1] = null;
        return true;
    }

    public int getDuracaoTotalSegundos(){
        int duracaoTotal = 0;
        for(Musica musica : musicas){
            if(musica != null){
                duracaoTotal += musica.getDuracaoSegundos();
            }
        }
        return duracaoTotal;
    }

    public void reproduzirTudo(){
        for (Musica musica : musicas){
            if(musica != null){
                musica.reproduzir();
            }
        }
    }
}
