package questao_2;

public class App2 {
    public static void main(String[] args) throws Exception {
        Produto produto1 = new Produto("Guarda-chuva", 20, 100);

        try {
            int qntVendas = 15;
            produto1.vender(qntVendas);
            System.out.println("Vendido: " + qntVendas + ". Novo estoque:" + produto1.getEstoque());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            produto1.vender(86);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int qntReposicao = 200;
            produto1.repor(qntReposicao);
            System.out.println("Reposto: " + qntReposicao + ". Novo estoque:" + produto1.getEstoque());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            produto1.setPreco(35);
            System.out.println("Novo preço: " + produto1.getPreco());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            produto1.setPreco(-12);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            produto1.setPreco(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
