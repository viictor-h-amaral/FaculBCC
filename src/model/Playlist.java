package model;

import java.util.ArrayList;

public class Playlist {

    private static void validarParametrosPlaylist(String nome, Usuario dono) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido! O nome não deve ser vazio.");
        }
        else if (dono == null) {
            throw new IllegalArgumentException("Dono inválido! O dono não deve ser nulo.");
        }
    }

    public Playlist(String nome, Usuario dono) throws IllegalArgumentException {
        validarParametrosPlaylist(nome, dono);

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

    private ArrayList<Musica> musicas = new ArrayList<>();

    public ArrayList<Musica> getArrayMusicas(){ 
        return musicas; 
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

    public int getQuantidade(){
        return musicas.size();
    }

    public Musica getNaPosicao(int indice){
        if (indice < 0 || indice >= musicas.size()) {
            throw new IndexOutOfBoundsException("Índice inválido! O índice deve estar entre 0 e " + (getQuantidade() - 1) + ". Índice solicitado: " + indice);
        }

        return musicas.get(indice);
    }

    public boolean adicionar(Musica musica){

        if(musica == null)
            throw new IllegalArgumentException("Música inválida! A música não deve ser nula.");

        musicas.add(musica);
        return true;
    }

    public boolean removerNaPosicao(int indice){
        if (indice < 0 || indice >= musicas.size())
            throw new IndexOutOfBoundsException("Índice inválido! O índice deve estar entre 0 e " + (getQuantidade() - 1) + ". Índice solicitado: " + indice);
        musicas.remove(indice);
        return true;
    }

    public int getDuracaoTotalSegundos(){
        int duracaoTotal = 0;
        for (Musica musica : musicas) {
            duracaoTotal += musica.getDuracaoSegundos();
        }
        return duracaoTotal;
    }

    public void reproduzirTudo(){
        for (Musica musica : musicas) {
            musica.reproduzir();
        }
    }
}
