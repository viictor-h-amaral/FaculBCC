package helpers;

public class Writer {

    public static void escreverNovaLinha(String mensagem){
        System.out.println(mensagem);
    }

    public static void escreverErro(String mensagem){
        System.err.println("Ops .. algo deu errado :(. \n" + mensagem);
    }

}
