public class Produto {

    public static int totalProdutos = 0;

    private int codigo;
    public int getCodigo(){ return this.codigo; }

    private String nome;
    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome; }

    private int quantidade;
    public int getQuantidade(){ return this.quantidade; }

    public void adicionar(int qnt){
        if(qnt <= 0){
            throw new IllegalArgumentException("Quantidade de entrada inválida");
        }
        this.quantidade += qnt;
    }

    public void remover(int qnt){
        if(qnt <= 0){
            throw new IllegalArgumentException("Quantidade de saída inválida");
        }
        else if(qnt > this.quantidade){
            throw new IllegalStateException("Estoque insuficiente");
        }
        this.quantidade -= qnt;
    }

    Produto(int codigo, String nome){
        Produto.validarCriacaoProduto(0);

        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = 0;
    }
    
    Produto(int codigo, String nome, int quantidade){
        Produto.validarCriacaoProduto(quantidade);

        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;

        Produto.totalProdutos += quantidade;
    }

    //futuramente pode ser adicionado aqui novas validações
    private static void validarCriacaoProduto(int quantidade){
        if(quantidade < 0){
            throw new IllegalArgumentException("Quantidade inicial inválida");
        }
    }
}
