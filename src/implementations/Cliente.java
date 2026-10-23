package implementations;

public class Cliente {

    public Cliente(String nome, String telefone){
        this.nome = nome;
        this.telefone = telefone;
    }

    private String nome;
    public String getNome(){
        return nome;
    }

    private String telefone;
    public String getTelefone(){
        return telefone;
    }
}
