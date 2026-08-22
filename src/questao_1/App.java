package questao_1;
public class App {
    public static void main(String[] args) throws Exception {

        Pessoa pessoa = new Pessoa(1.75, 78);
        System.out.println("IMC: " + String.format("%.2f", pessoa.calcularIMC()));
    }
}
