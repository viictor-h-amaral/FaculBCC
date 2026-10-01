package model;
import contracts.Conteudo;

public class Musica extends Conteudo {

    public Musica(String titulo, String artista, int duracaoSegundos) {
        super(titulo, duracaoSegundos);
        setArtista(artista);
        setAlbum(null);
    }

    public Musica(String titulo, String artista, String album, int duracaoSegundos) {
        super(titulo, duracaoSegundos);
        setArtista(artista);
        setAlbum(album);
    }

    private String album;
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        if (album != null && album.isBlank()) {
            throw new IllegalArgumentException("Álbum inválido! Para álbuns não nulos, informe um nome válido.");
        }
        this.album = album;
    }

    private String artista;
    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        if (artista == null || artista.isBlank()) {
            throw new IllegalArgumentException("Artista inválido! O artista não deve ser vazio.");
        }
        this.artista = artista;
    }

    @Override
    public String getCreditos() {
        return album == null ? artista : artista + " (" + album + ")";
    }

    @Override public String toString() { 
        return super.toString() + " do artista " + artista + " (" + album + ")"; 
    }
}
