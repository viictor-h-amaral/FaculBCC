package setup.implementation;

public class Entrega {

    public Entrega(String cidade, int prazoDias){

        if (cidade == null || cidade.isBlank()){
            throw new IllegalArgumentException("Cidade não pode ser vazia");
        }
        else if (prazoDias < 0){
            throw new IllegalArgumentException("Pazo de dias da entrega não pode ser negativo");
        }

        this.cidade = cidade;
        this.prazoDias = prazoDias;
    }

    private String cidade;
    public String getCidade(){
        return cidade;
    }

    private int prazoDias;
    public int getPrazoDias(){
        return prazoDias;
    }
}