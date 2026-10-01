package contracts;

public abstract class Conteudo {
    private static int contador = 0; 
    public static int getProximoId() {
        return contador + 1;
    }

    protected int id; 
    protected String titulo; 
    protected int duracaoSegundos; 
    private int reproducoes;
  
    public Conteudo(String titulo, int duracaoSegundos) { 
        this.id = ++contador; 
        setTitulo(titulo); 
        setDuracaoSegundos(duracaoSegundos); 
    } 
  
    public int getId() { 
        return id; 
    } 
  
    protected void setId(int id) { 
        this.id = id; 
    } 
  
    public String getTitulo() { 
        return titulo; 
    }

    public void setTitulo(String titulo) { 
        if (titulo == null || titulo.isBlank()) { 
            throw new IllegalArgumentException("Título inválido! O título não deve ser vazio."); 
        } 
        this.titulo = titulo; 
    }

    public int getDuracaoSegundos() { 
        return duracaoSegundos; 
    }

    public int getReproducoes() {
        return reproducoes;
    }

    public void setDuracaoSegundos(int duracaoSegundos) { 
        if (duracaoSegundos <= 0) { 
            throw new IllegalArgumentException("Duração inválida! A duração deve ser um valor positivo."); 
        } 
        this.duracaoSegundos = duracaoSegundos; 
    }

    public abstract String getCreditos();

    public final void reproduzir() {
        reproducoes++;
        System.out.println("Reproduzindo: " + titulo + " - " + getCreditos());
    } 
  
    public String getDuracaoFormatada(){
        int minutos = getDuracaoSegundos() / 60;
        int segundosResto = getDuracaoSegundos() % 60;
        return String.format("%02d:%02d", minutos, segundosResto); //padronização de dois digitos
    }

    @Override 
    public String toString() { 
        return "[id" + getId() + "] " + titulo 
             + " (" + getDuracaoFormatada() + ")"; 
    }
}
