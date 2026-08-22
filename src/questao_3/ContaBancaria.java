package questao_3;

public class ContaBancaria {
    private String numero;
    private String titular;
    private double saldo = 0;

    public String getNumero(){
        return numero;
    }

    public void setNumero(String value){
        numero = value;
    }

    public String getTitular(){
        return titular;
    }

    public void setTitular(String value){
        titular = value;
    }

    public double getSaldo(){
        return saldo;
    }

    public void depositar(double valor) throws Exception{
        if(valor <= 0){
            throw new Exception("Não é possível depositar valor menores ou iguais a 0. Informado: " + valor);
        }
        saldo += valor;
        System.out.println("Depósito de R$ " + valor + " feito na conta " + this.getNumero() + ". Novo saldo: R$ " + saldo);
    }

    public void sacar(double valor) throws Exception{

        if(valor <= 0){
            throw new Exception("Não é possível o saque de valor menor ou igual a zero. Informado: " + valor);
        }

        if(saldo - valor < 0){
            throw new Exception("Saldo insuficiente haha! Saldo: "+ saldo + ". Tentou sacar: " + valor);
        }
        saldo -= valor;
        System.out.println("Saque de R$ " + valor + " feito na conta " + this.getNumero() + ". Novo saldo: R$ " + saldo);
    }

    public void transferir(ContaBancaria contaDestino, double valor){
        try{
            System.out.println("Iniciada transferencia entre contas " + this.getNumero() + " -> " + contaDestino.getNumero());
            sacar(valor);
            contaDestino.depositar(valor);
        }
        catch (Exception e){
            System.out.println("Erro ao executar transferencia entre contas. Conta saque: " + this.numero + ". Conta destino: " + contaDestino.numero);
        }
    }

    public ContaBancaria(){ }

    public String retornarDadosConta(){
        return "Dados bancários da conta " + this.getNumero() + ":\n Titular: "+ this.getTitular() + " - Saldo: " + this.getSaldo();
    }
}
