package model;
import contracts.Conteudo;

public class Podcast extends Conteudo {
    private String apresentador;
    private int numeroEpisodio;

    public int getDuracaoMinutos() {
        return Math.round(getDuracaoSegundos() / 60.0f);
    }

    public Podcast(String titulo, String apresentador, int duracaoMinutos, int numeroEpisodio) {
        super(titulo, (duracaoMinutos * 60));
        setApresentador(apresentador);
        setNumeroEpisodio(numeroEpisodio);
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
    public String getCreditos() {
        return "episódio " + numeroEpisodio + " - " + apresentador;
    }

    @Override 
    public String toString() { 
        return super.toString() + " apresentado pelo(a)"  
            + " ilustre " + apresentador + " (episódio " + numeroEpisodio + ")";
    }
}
