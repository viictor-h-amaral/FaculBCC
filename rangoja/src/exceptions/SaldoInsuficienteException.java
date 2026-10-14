package exceptions;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(double saldo, double valorSolicitado) {
        super("Saldo existente ( R$" + saldo + ") é insuficiente para o valor solicitado ( R$ " + valorSolicitado + ")");
    }
}
