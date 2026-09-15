package model;

import java.util.ArrayList;

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
    public static int getProximoId() {
        return proximoId;
    }

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

    private ArrayList<Usuario> seguindo = new ArrayList<>();

    public void seguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Usuário inválido! O usuário não deve ser nulo.");
        }
        else if(outro == this){
            throw new IllegalArgumentException("Usuário inválido! O usuário não pode seguir a si mesmo.");
        }
        else if (seguindo.contains(outro)) {
            throw new IllegalArgumentException("Usuário inválido! O usuário já está seguindo o outro usuário.");
        }
        seguindo.add(outro);
    }

    public void deixarDeSeguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Usuário inválido! O usuário não deve ser nulo.");
        }
        seguindo.remove(outro);
    }

    public int getQuantidadeSeguindo() {
        return seguindo.size();
    }

    public ArrayList<Usuario> getSeguindo() {
        return seguindo;
    }

}
