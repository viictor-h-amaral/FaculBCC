package model;

public class Usuario {

    private static int proximoId = 1;

    public Usuario(String nome, String email) {
        this.id = proximoId;
        Usuario.proximoId++;

        this.nome = nome;
        this.email = email;
    }

    private int id;

    public int getId() {
        return id;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    private String email;

    public String getEmail() {
        return email;
    }

}
