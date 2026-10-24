package A1;
import A1.implementation.Senha;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Total senhas emitidas: " + Senha.getTotalEmitidas());

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
            System.err.println("Erro! Mensagem: " + e.getMessage());
        }

        var senha4 = new Senha("Caixa");
        System.out.println(senha4.toString());

        System.out.println("Total senhas emitidas: " + Senha.getTotalEmitidas());

    }
}
