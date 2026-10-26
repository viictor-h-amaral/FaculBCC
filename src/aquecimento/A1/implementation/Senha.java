package aquecimento.A1.implementation;

public class Senha {

    // sem static, dá erro no método static int getTotalEmitidas(), já que ele referencia um atributo de instância, totalEmitidas.
    private static int totalEmitidas = 0;

    // ao remover o static, deu erro no Main, uma vez que o método getTotalEmitidas() é de instância e não da classe Senha
    public static int getTotalEmitidas(){
        return totalEmitidas;
    }

    private int numero;
    public int getNumero(){
        return numero;
    }

    private String setor;
    public String getSetor(){
        return setor;
    }

    public Senha(String setor){
        if (setor == null || setor.isBlank()){
            throw new IllegalArgumentException("Setor obrigatório");
        }

        this.setor = setor;
        // aqui o contador é incrementado
        // garante o item c) pois está depois da validação de setor
        totalEmitidas++;
        this.numero = getTotalEmitidas();
    }

    @Override 
    public String toString(){
        return this.getNumero() + " - " + this.getSetor();
    }
}
