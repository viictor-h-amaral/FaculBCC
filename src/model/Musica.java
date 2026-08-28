package model;
public class Musica {
    
    private static void ValidarParametrosMusica(String titulo, String artista, int duracaoSegundos) throws IllegalArgumentException {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Ops. Título inválido! O título não deve ser vazio.");
        }
        else if (artista == null || artista.isEmpty()) {
            throw new IllegalArgumentException("Ops. Artista inválido! O artista não deve ser vazio.");
        }
        else if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Ops. Duração inválida! A duração deve não deve ser negativa.");
        }
    }

    private static int proximoId = 1;

    public Musica(String titulo, String artista, int duracaoSegundos) throws IllegalArgumentException {
        ValidarParametrosMusica(titulo, artista, duracaoSegundos);

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
