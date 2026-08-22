package model;
public class Musica {
    
    private static int proximoId = 1;

    public Musica(String titulo, String artista, int duracaoSegundos) {
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
