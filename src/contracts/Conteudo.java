public class Conteudo {
    private static int contador = 0; 
    private int id; 
    private String titulo; 
    private int duracaoSegundos; 
  
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
  
    // getTitulo, setTitulo (valida vazio/nulo), 
    // getDuracaoSegundos, setDuracaoSegundos (valida > 0) 
  
    public void reproduzir() { 
        System.out.println("Reproduzindo: " + toString()); 
    } 
  
    @Override 
    public String toString() { 
        return "[" + getId() + "] " + titulo 
             + " (" + duracaoSegundos + "s)"; 
    }
}
