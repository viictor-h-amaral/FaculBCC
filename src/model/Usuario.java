package model;

public class Usuario {

    private static void validarParametrosUsuario(String nome, String email) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido! O nome não deve ser vazio.");
        }
        else if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email inválido! O email não deve ser vazio.");
        }
        else if (!email.contains("@")){
            throw new IllegalArgumentException("Email inválido! Caracter '@' não encontrado no email.");
        }
    }

    private static int proximoId = 1;

    public Usuario(String nome, String email) throws IllegalArgumentException {
        validarParametrosUsuario(nome, email);

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
