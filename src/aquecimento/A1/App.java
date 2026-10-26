package aquecimento.A1;
import aquecimento.A1.implementation.Senha;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Emitidas: " + Senha.getTotalEmitidas());

        var senha1 = new Senha("Caixa");
        System.out.println(senha1.toString());
        var senha2 = new Senha("Gerência");
        System.out.println(senha2.toString());
        var senha3 = new Senha("Caixa");
        System.out.println(senha3.toString());

        try {
            @SuppressWarnings("unused")
            var senhaErrada = new Senha("");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }

        var senha4 = new Senha("Caixa");
        System.out.println(senha4.toString());

        System.out.println("Emitidas: " + Senha.getTotalEmitidas());

    }
}
