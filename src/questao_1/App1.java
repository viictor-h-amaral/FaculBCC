package questao_1;

import java.util.List;
import java.util.Scanner;

public class App1 {
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

        for (int i = pessoas.size() - 1; i > -1; i--) {
            Pessoa pessoa = pessoas.get(i);
            int posicao = i + 1;
            System.out.printf("=== Pessoa %d === \n %s \n", posicao, pessoa.retornarDadosPessoa());
        }

        scanner.close();
    }

    private static Pessoa criarPessoa(Scanner scanner, int numeroPessoa) {
        Pessoa pessoa = new Pessoa();

        obterNomePessoa(scanner, pessoa, numeroPessoa);
        obterAlturaPessoa(scanner, pessoa, numeroPessoa);
        obterPesoPessoa(scanner, pessoa, numeroPessoa);

        return pessoa;
    }

    private static void obterNomePessoa(Scanner scanner, Pessoa pessoa, int numeroPessoa) {
        
        while(true) {
            try{
                System.out.printf("Digite o nome da pessoa %d:", numeroPessoa);
                String entradaNome = scanner.nextLine();

                pessoa.setNome(entradaNome);
                break;
            }
            catch (Exception e) {
                System.out.println("Valor informado é inválido! Exceção lançada: " + e.getMessage() + "\nPor favor, tente novamente.");
            }
        }
    }

    private static void obterAlturaPessoa(Scanner scanner, Pessoa pessoa, int numeroPessoa) {
        double altura = 0;

        while(true) {
            try{
                System.out.printf("Digite a altura da pessoa %d (em metros):", numeroPessoa);
                String entradaAltura = scanner.nextLine().replace(",", ".");
                altura = Double.parseDouble(entradaAltura);

                pessoa.setAltura(altura);
                break;
            }
            catch (Exception e) {
                System.out.println("Valor informado é inválido! Exceção lançada: " + e.getMessage() + "\nPor favor, tente novamente.");
            }
        }
    }

    private static void obterPesoPessoa(Scanner scanner, Pessoa pessoa, int numeroPessoa) {
        double peso = 0;

        while(true) {
            try{
                System.out.printf("Digite o peso da pessoa %d (em kg):", numeroPessoa);
                String entradaPeso = scanner.nextLine().replace(",", ".");;
                peso = Double.parseDouble(entradaPeso);

                pessoa.setPeso(peso);
                break;
            }
            catch (Exception e) {
                System.out.println("Valor informado é inválido! Exceção lançada: " + e.getMessage() + "\nPor favor, tente novamente.");
            }
        }
    }
}
