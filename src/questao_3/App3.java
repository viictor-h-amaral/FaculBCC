package questao_3;

import java.util.Scanner;

public class App3 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite os dados da conta 1:");
        ContaBancaria conta1 = obterContaBancaria(scanner);
        System.out.println("Digite os dados da conta 2:");
        ContaBancaria conta2 = obterContaBancaria(scanner);

        conta1.depositar(1000);
        conta1.depositar(700);

        conta2.depositar(5000);

        conta2.sacar(3000);

        conta2.transferir(conta1, 1800);

        System.out.println(conta1.retornarDadosConta());
        System.out.println(conta2.retornarDadosConta());

        scanner.close();
    }

    private static ContaBancaria obterContaBancaria(Scanner scanner){
        ContaBancaria conta = new ContaBancaria();
        obterTitularConta(scanner, conta);
        obterNumeroConta(scanner, conta);

        return conta;
    }

    private static void obterTitularConta(Scanner scanner, ContaBancaria conta){
        while(true) {
            try{
                System.out.printf("Digite o titular da conta: ");
                String titularEntrada = scanner.nextLine();

                conta.setTitular(titularEntrada);
                break;
            }
            catch (Exception e) {
                System.out.println("Valor informado é inválido! Exceção lançada: " + e.getMessage() + "\nPor favor, tente novamente.");
            }
        }
    }

    private static void obterNumeroConta(Scanner scanner, ContaBancaria conta){
        while(true) {
            try{
                System.out.printf("Digite o número da conta: ");
                String numeroEntrada = scanner.nextLine();

                conta.setNumero(numeroEntrada);
                break;
            }
            catch (Exception e) {
                System.out.println("Valor informado é inválido! Exceção lançada: " + e.getMessage() + "\nPor favor, tente novamente.");
            }
        }
    }
}
