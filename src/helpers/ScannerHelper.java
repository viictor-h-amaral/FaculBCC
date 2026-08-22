package helpers;

import java.util.Scanner;

public class ScannerHelper {

    private Scanner scanner;

    public ScannerHelper(Scanner scanner){
        this.scanner = scanner;
    }

    public boolean validarExisteProximoInt(){
        var result = scanner.hasNextInt();
        if (!result) {
            this.passarProximaLinha();
        }
        return result;
    }

    public void passarProximaLinha(){
        scanner.nextLine();
    }

    public int lerInt(){
        var result = scanner.nextInt();
        this.passarProximaLinha();
        return result;
    }

    public int lerInt(String mensagemEntrada, String mensagemErro){
        return lerInt(mensagemEntrada, mensagemErro, Integer.MIN_VALUE);
    }

    public int lerInt(String mensagemEntrada){
        return lerInt(mensagemEntrada, "Ops .. Valor inválido! Tente novamente ... ");
    }

    public int lerInt(String mensagemEntrada, String mensagemErro, int minimo){
        return lerInt(mensagemEntrada, mensagemErro, minimo, Integer.MAX_VALUE);
    }

    public int lerInt(String mensagemEntrada, String mensagemErro, int minimo, int maximo){
        var result = 0;
        while(true){
            Writer.EscreverNovaLinha(mensagemEntrada);

            if (!this.validarExisteProximoInt()) {
                Writer.EscreverNovaLinha(mensagemErro);
                continue;
            }

            result = this.lerInt();

            if(result > maximo || result < minimo){
                Writer.EscreverNovaLinha("Valor informado deve estar no intervalo [" + minimo + "," + maximo + "]. Tente novamente ...");
                result = 0;
                continue;
            }
            break;
        }
        return result;
    }

    public String lerLinha(){
        return scanner.nextLine();
    }

    public String lerLinha(String mensagem){
        Writer.EscreverNovaLinha(mensagem);
        return scanner.nextLine();
    }

    public void fecharScanner(){
        this.scanner.close();
    }
}
