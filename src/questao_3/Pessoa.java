package questao_3;
public class Pessoa {

    public double altura;

    public double peso;

    public Pessoa(double alturaParametro, double pesoParametro) {
        this.altura = alturaParametro;
        this.peso = pesoParametro;
    }

    public double calcularIMC() {
        double imc = peso / (altura * altura);
        return imc;
    }
}
