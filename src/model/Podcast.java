package model;
import contracts.Conteudo;

public class Podcast extends Conteudo {
    private String apresentador;
    private char generoApresentador;
    private int numeroEpisodio;

    public int getDuracaoMinutos() {
        return Math.round(getDuracaoSegundos() / 60.0f);
    }

    public Podcast(String titulo, String apresentador, int duracaoMinutos, int numeroEpisodio, char generoApresentador) {
        super(titulo, (duracaoMinutos * 60));
        setApresentador(apresentador);
        setNumeroEpisodio(numeroEpisodio);
        setGeneroApresentador(generoApresentador);
    }

    public String getApresentador() {
        return apresentador;
    }

    public void setApresentador(String apresentador) {
        if (apresentador == null || apresentador.isBlank()) {
            throw new IllegalArgumentException("Apresentador inválido! O apresentador não deve ser vazio.");
        }
        this.apresentador = apresentador;
    }

    public char getGeneroApresentador() {
        return generoApresentador;
    }

    public void setGeneroApresentador(char generoApresentador) {
        if (generoApresentador != 'M' && generoApresentador != 'F' && generoApresentador != 'O') {
            throw new IllegalArgumentException("Gênero do apresentador inválido! Deve ser 'M', 'F' ou 'O'.");
        }
        this.generoApresentador = generoApresentador;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        if (numeroEpisodio <= 0) {
            throw new IllegalArgumentException("Número do episódio inválido! Deve ser um valor positivo.");
        }
        this.numeroEpisodio = numeroEpisodio;
    }

    @Override 
    public String toString() { 
        return super.toString() + " apresentado pel" 
            + (generoApresentador == 'M' ? "o" : (generoApresentador == 'F' ? "a" : "x")) 
            + " ilustre " + apresentador + " (episódio " + numeroEpisodio + ")";
    }
}
