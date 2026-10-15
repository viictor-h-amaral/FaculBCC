package implementations;

import exceptions.SaldoInsuficienteException;

public class Cliente {

    public Cliente(String nome, String email, int idade){

        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        else if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        else if (!email.contains("@")){
            throw new IllegalArgumentException("Email informado não estpa no formato correto (deve conter '@')");
        }

        this.nome = nome;
        this.email = email;
        setIdade(idade);
    }

    public Cliente(String nome, String email, int idade, Cliente indicadoPor){
        this(nome, email, idade);

        if (indicadoPor == null) {
            throw new IllegalArgumentException("Cliente indicado não pode ser nulo");
        }        
        this.indicadoPor = indicadoPor;
        indicadoPor.creditarCashback(10.0);
    }

    private Cliente indicadoPor;

    public Cliente getIndicadoPor(){
        return indicadoPor;
    }

    private String nome;

    public String getNome(){
        return nome;
    }

    private String email;

    public String getEmail(){
        return email;
    }

    private int idade;

    public int getIdade(){
        return idade;
    }

    public void setIdade(int value){
        if (value < 0 || value > 120) {
            throw new IllegalArgumentException("Idade não pode ser negativa ou maior que 120");
        }
        this.idade = value;
    }

    public boolean isMaiorDeIdade(){
        return this.idade >= 18;
    }

    private double saldoCashback;

    public double getSaldoCashback(){
        return saldoCashback;
    }

    public void creditarCashback(double value){
        if (value <= 0) {
            throw new IllegalArgumentException("Valor de cashback não pode ser negativo ou zero");
        }
        this.saldoCashback += value;
    }

    public void usarCashback(double value) throws SaldoInsuficienteException{
        if (value <= 0) {
            throw new IllegalArgumentException("Valor de cashback não pode ser negativo ou zero");
        }
        if (value > this.saldoCashback) {
            throw new SaldoInsuficienteException(this.getSaldoCashback(), value);
        }
        this.saldoCashback -= value;
    }

    @Override 
    public String toString(){
        return this.nome + " " + this.email;
    }

    @Override 
    public boolean equals(Object outro){
        if (outro == null || !(outro instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) outro;
        return this.email.equalsIgnoreCase(cliente.getEmail());
    }
}
