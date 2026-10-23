package implementations;

public class Endereco {
    private String rua;
    public String getRua(){
        return rua;
    }

    private int numero;
    public int getNumero(){
        return numero;
    }

    public Endereco(String rua, int numero){
        this.rua = rua;
        this.numero = numero;
    }
}
