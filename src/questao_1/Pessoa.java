package questao_1;

public class Pessoa {

    private String nome;

    public String getNome(){
        return nome;
    }

    public void setNome(String value){
        nome = value;
    }

    private double altura;

    public void setAltura(double value){
        if (value <= 0 || value >= 3) {
            String mensagemDeInvalidez = value <= 0 ?
                "A altura deve ser um valor positivo." : 
                "A altura deve ser menor que 3 metros.";
            throw new IllegalArgumentException(mensagemDeInvalidez);
        }

        altura = value;
    }

    public double getAltura(){
        return altura;
    }

    private double peso;

    public void setPeso(double value){
        if (value <= 0) {
            throw new IllegalArgumentException("O peso deve ser um valor positivo.");
        }
        peso = value;
    }

    public double getPeso(){
        return peso;
    }

    public Pessoa(String nomeParametro, double alturaParametro, double pesoParametro) {
        setNome(nomeParametro);
        setAltura(alturaParametro);
        setPeso(pesoParametro);
    }

    public Pessoa(){ }

    public double calcularIMC() {
        double imc = peso / (altura * altura);
        return imc;
    }

    public String retornarDadosPessoa(){
        return "Nome: " + nome + "\n Peso: " + peso + "\n Altura: " + altura + "\n IMC: " + String.format("%.2f", calcularIMC());
    }
}
