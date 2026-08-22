package questao_3;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int numeroPessoas = 3;
        List<Pessoa> pessoas = new java.util.ArrayList<>();

        for (int i = 0; i < numeroPessoas; i++) {
            System.out.printf("Pessoa: " + (i + 1) + "\n");
            Pessoa pessoa = criarPessoa(scanner, i + 1);
            System.out.printf("IMC: %.2f\n", pessoa.calcularIMC());
            pessoas.add(pessoa);
        }

        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa pessoa = pessoas.get(i);
            int posicao = i + 1;
            System.out.printf("Pessoa %d - IMC: %.2f\n", posicao, pessoa.calcularIMC());
        }

        scanner.close();
    }

    public static Pessoa criarPessoa(Scanner scanner, int numeroPessoa) {
        double altura = 0;
        double peso = 0;

        while(true) {
            try{
                System.out.printf("Digite a altura da pessoa %d (em metros):", numeroPessoa);
                String entradaAltura = scanner.nextLine().replace(",", ".");
                altura = Double.parseDouble(entradaAltura);

                if (altura <= 0 || altura > 3) {
                    String mensagemDeInvalidez = altura <= 0 ?
                        "A altura deve ser um valor positivo." : 
                        "A altura deve ser menor que 3 metros.";
                    throw new IllegalArgumentException(mensagemDeInvalidez);
                }

                break;
            }
            catch (Exception e) {
                System.out.println("Valor informado é inválido! Exceção lançada: " + e.getMessage() + "\nPor favor, tente novamente.");
            }
        }

        while(true) {
            try{
                System.out.printf("Digite o peso da pessoa %d (em kg):", numeroPessoa);
                String entradaPeso = scanner.nextLine().replace(",", ".");;
                peso = Double.parseDouble(entradaPeso);

                if (peso <= 0) {
                    throw new IllegalArgumentException("O peso deve ser um valor positivo.");
                }

                break;
            }
            catch (Exception e) {
                System.out.println("Valor informado é inválido! Exceção lançada: " + e.getMessage() + "\nPor favor, tente novamente.");
            }
        }

        Pessoa pessoa = new Pessoa(altura, peso);
        return pessoa;
    }
}
