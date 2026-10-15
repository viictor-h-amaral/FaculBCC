import implementations.ItensCardapio.Prato;
import implementations.ItensCardapio.Sobremesa;

import java.util.ArrayList;
import java.util.Scanner;

import contracts.abstractions.ItemCardapio;
import exceptions.SaldoInsuficienteException;
import implementations.AssinaturaClube;
import implementations.CentralCobranca;
import implementations.Cliente;
import implementations.Pedido;
import implementations.ItensCardapio.Bebida;

public class App {

    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        var cardapio = new ArrayList<ItemCardapio>();

        ItemCardapio prato = new Prato("Feijoada", 25.0, 'G');
        ItemCardapio sobremesa = new Sobremesa("Pudim", 10.0, 150);
        ItemCardapio bebida = new Bebida("Chopp", 8.0, 500, true);

        cardapio.add(prato);
        cardapio.add(sobremesa);
        cardapio.add(bebida);

        for (ItemCardapio item : cardapio) {
            imprimir(item.toString());
        }

        Cliente ana = new Cliente("Ana", "ana@email.com", 20);
        Cliente beto = new Cliente("Beto", "beto@email.com", 17, ana);

        imprimir(String.format("Saldo de cashback de Ana: R$ %.2f", ana.getSaldoCashback()));

        Pedido pedidoBeto = new Pedido(beto);
        while (true) {
            int quantidade = lerInt("Digite a quantidade de feijoadas para o pedido do Beto:");
            try {
                pedidoBeto.adicionarItem(prato, quantidade);
                break;
            }
            catch (IllegalArgumentException e) {
                imprimirErro(e.getMessage());
            }
        }

        try {
            pedidoBeto.adicionarItem(bebida);
        }
        catch (IllegalArgumentException e) {
            imprimirErro(e.getMessage());
        }

        Pedido pedidoAna = new Pedido(ana);
        try{
            pedidoAna.adicionarItem(bebida);
            pedidoAna.adicionarItem(sobremesa, 2);
            pedidoAna.finalizar();
        }
        catch (IllegalArgumentException | IllegalStateException e){
            imprimirErro(e.getMessage());
        }
        catch (Exception e){
            imprimirErro(e.getMessage());
        }

        try{
            pedidoAna.finalizar();
        }
        catch (Exception e){
            imprimirErro(e.getMessage());
        }

        try{
            double saldoAna = ana.getSaldoCashback();
            ana.usarCashback(saldoAna + 1);
        }
        catch (SaldoInsuficienteException e){
            imprimirErro("Exceção de Saldo Insuficiente: " + e.getMessage());
        }
        catch (Exception e){
            imprimirErro(e.getMessage());
        }
        finally{
            imprimir("Saldo de cashback da Ana: " + ana.getSaldoCashback());
        }
        

        AssinaturaClube assinaturaAna = new AssinaturaClube(ana, 120, 24);
        CentralCobranca centralCobrancas = new CentralCobranca();

        try{
            centralCobrancas.registrar(pedidoAna);
            centralCobrancas.registrar(assinaturaAna);
        }
        catch(Exception e){
            imprimir(e.getMessage());
        }

        imprimir(centralCobrancas.gerarRelatorio());
    }

    private static void imprimir(String mensagem){
        System.out.println(mensagem);
    }

    private static void imprimirErro(String erro){
        System.out.println("Erro! " + erro);
    }

    public static int lerInt(String mensagemEntrada){
        var result = 0;
        while(true){
            try{
                imprimir(mensagemEntrada);
                result = Integer.parseInt(teclado.nextLine());
                break;
            }
            catch(NumberFormatException e){
                imprimirErro("Valor inválido: digite um número ... (stack: " + e.getMessage() + ")");
            }
            catch(IllegalArgumentException e){
                imprimirErro("Valor inválido: " + e.getMessage());
            }
            catch(Exception e){
                imprimirErro("Stack: " + e.getStackTrace());
                teclado.nextLine();
            }
        }
        return result;
    }
}
