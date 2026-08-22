package questao_4;
public class Pessoa {

    public String nome;

    public double altura;

    public double peso;

    public Pessoa(String nomeParametro, double alturaParametro, double pesoParametro) {
        this.nome = nomeParametro;
        this.altura = alturaParametro;
        this.peso = pesoParametro;
    }

    public double calcularIMC() {
        double imc = peso / (altura * altura);
        return imc;
    }

    public String retornarDadosPessoa(){
        return "Nome: " + nome + "\n Peso: " + peso + "\n Altura: " + altura + "\n IMC: " + String.format("%.2f", calcularIMC());
    }
}
