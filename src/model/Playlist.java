package model;

import java.util.ArrayList;

import contracts.Conteudo;

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

    private ArrayList<Conteudo> conteudos = new ArrayList<>();

    public ArrayList<Conteudo> getArrayConteudos(){ 
        return conteudos; 
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

    public int getQuantidade(){
        return conteudos.size();
    }

    public Conteudo getNaPosicao(int indice){
        if (indice < 0 || indice >= conteudos.size()) {
            throw new IndexOutOfBoundsException("Índice inválido! O índice deve estar entre 0 e " + (getQuantidade() - 1) + ". Índice solicitado: " + indice);
        }

        return conteudos.get(indice);
    }

    public boolean adicionar(Conteudo conteudo){
        if(conteudo == null)
            throw new IllegalArgumentException("Conteúdo inválido! O conteúdo não deve ser nulo.");

        if (conteudos.contains(conteudo)) {
            throw new IllegalArgumentException("Conteúdo inválido! O conteúdo já está presente na playlist.");
        }

        conteudos.add(conteudo);
        return true;
    }

    public boolean removerNaPosicao(int indice){
        if (indice < 0 || indice >= conteudos.size())
            throw new IndexOutOfBoundsException("Índice inválido! O índice deve estar entre 0 e " + (getQuantidade() - 1) + ". Índice solicitado: " + indice);
        conteudos.remove(indice);
        return true;
    }

    public int getDuracaoTotalSegundos(){
        int duracaoTotal = 0;
        for (Conteudo conteudo : conteudos) {
            duracaoTotal += conteudo.getDuracaoSegundos();
        }
        return duracaoTotal;
    }

    public void reproduzirTudo(){
        for (Conteudo conteudo : conteudos) {
            conteudo.reproduzir();
        }
    }
}
