package model;
public class Musica {
    
    private static void validarParametrosMusica(String titulo, String artista, int duracaoSegundos) throws IllegalArgumentException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido! O título não deve ser vazio.");
        }
        else if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido! O artista não deve ser vazio.");
        }
        else if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração inválida! A duração não deve ser negativa ou zero.");
        }
    }

    private static int proximoId = 1;

    public Musica(String titulo, String artista, int duracaoSegundos) throws IllegalArgumentException {
        validarParametrosMusica(titulo, artista, duracaoSegundos);

        this.id = proximoId;
        Musica.proximoId++;

        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.reproducoes = 0;
    }

    private int id;
    public int getId() {
        return id;
    }

    private String titulo;
    public String getTitulo() {
        return titulo;
    }

    private String artista;
    public String getArtista() {
        return artista;
    }

    private int duracaoSegundos;
    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    private int reproducoes = 0;
    public int getReproducoes() {
        return reproducoes;
    }

    public void reproduzir() {
        reproducoes++;
    }

    public String getDuracaoFormatada(){
        int minutos = duracaoSegundos / 60;
        int segundosResto = duracaoSegundos % 60;
        return String.format("%02d:%02d", minutos, segundosResto); //padronização de dois digitos
    }

}
