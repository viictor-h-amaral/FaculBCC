package setup.implementation;

public class Cliente {

    public Cliente(String nome, String cpf){
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }
        else if (cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("CPF deve ser informado");
        }

        this.nome = nome;
        this.cpf = cpf;
    }

    private String nome;
    public String getNome(){
        return nome;
    }

    private String cpf;
    public String getCpf(){
        return cpf;
    }
}