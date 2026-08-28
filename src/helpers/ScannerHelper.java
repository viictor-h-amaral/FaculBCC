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

    private int lerIntInternal(){
        var result = Integer.parseInt(scanner.nextLine());
        return result;
    }

    public int lerInt(String mensagemEntrada){
        return lerInt(mensagemEntrada, Integer.MIN_VALUE);
    }

    public int lerInt(String mensagemEntrada, int minimo){
        return lerInt(mensagemEntrada, minimo, Integer.MAX_VALUE);
    }

    public int lerInt(String mensagemEntrada, int minimo, int maximo){
        var result = 0;
        while(true){
            try{
                Writer.EscreverNovaLinha(mensagemEntrada);
                result = this.lerIntInternal();

                if(result > maximo || result < minimo)
                    throw new IllegalArgumentException("Valor informado deve estar no intervalo [" + minimo + "," + maximo + "]. Tente novamente ...");
                
                break;
            }
            catch(NumberFormatException e){
                Writer.EscreverNovaLinha("Ops .. Valor inválido! Digite um número ... (stack: " + e.getMessage() + ")");
            }
            catch(IllegalArgumentException e){
                Writer.EscreverNovaLinha("Ops .. Valor inválido! " + e.getMessage());
            }
            catch(Exception e){
                Writer.EscreverNovaLinha("Ops .. Valor inválido! Tente novamente ... (stack: " + e.getMessage() + ")");
                this.passarProximaLinha();
            }
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
