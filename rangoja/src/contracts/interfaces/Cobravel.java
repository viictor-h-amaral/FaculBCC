package contracts.interfaces;

public interface Cobravel {
    double calcularValorCobrar();

    default String gerarComprovante() {
        return String.format("Valor a cobrar: R$ %.2f", this.calcularValorCobrar());
    }
}
