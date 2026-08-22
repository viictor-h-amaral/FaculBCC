package questao_2;

public class Produto {

    public Produto(){}

    public Produto(String nomeParametro, double precoParametro, int estoqueParametro){
        try {
            setPreco(precoParametro);
            setNome(nomeParametro);
            repor(estoqueParametro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String nome;

    public void setNome(String value){
        nome = value;
    }

    public String getNome(){
        return nome;
    }

    private double preco;

    public void setPreco(double value) throws Exception{
        if(value <= 0){
            throw new Exception("Não é possível o valor de um produto ser menor ou igual a zero! Informado: " + value);
        }
        preco = value;
    }

    public double getPreco(){
        return preco;
    }

    private int estoque;

    public int getEstoque(){
        return estoque;
    }

    public void vender(int quantidade) throws Exception {
        if(estoque - quantidade < 0){
            throw new Exception("Não há unidades disponíveis no estoque para completar a venda! Informado: " + quantidade);
        }
        estoque -= quantidade;
    }

    public void repor(int quantidade) {
        estoque += quantidade;
    }
}
