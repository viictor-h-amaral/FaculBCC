package questao_2;
import java.util.Scanner;

import questao_1.Pessoa;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        double altura = 0;
        double peso = 0;

        while(true) {
            try{
                System.out.printf("Digite sua altura (em metros):");
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
                System.out.printf("Digite seu peso (em kg):");
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

        scanner.close();

        Pessoa pessoa = new Pessoa(altura, peso);
        double imc = pessoa.calcularIMC();
        System.out.printf("Seu IMC é: %.2f\n", imc);
    }
}
