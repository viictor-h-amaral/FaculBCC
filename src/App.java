public class App {
    public static void main(String[] args) throws Exception {
        var celular = new Produto(123, "Motorola G15");
        var tablet = new Produto(125, "IPad Pro Max Ultra Master", 20);
        tablet.setNome("IPad 15 Pro Max Ultra Master");

        celular.adicionar(10);
        tablet.adicionar(2);

        tablet.remover(22);
        celular.remover(9);


        try {
            System.out.println("== Seção 1 de erros: tentando criar produto com -1 quantidade em estoque ==");
            var produtoInvalido = new Produto(123, "inválido", -1);
        } catch (IllegalArgumentException e) {
            System.err.println("Ops .. valor informado inválido: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ops .. ocorreu um erro: " + e.getMessage());
        }
        finally{
            System.out.println(" == Finalizando seção 1 de erros == ");
            System.out.println();
        }

        try {
            System.out.println("== Seção 2 de erros: tentando adicionar -50 tablets no estoque ==");
            tablet.adicionar(-50);
        } catch (IllegalArgumentException e) {
            System.err.println("Ops .. valor informado inválido: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ops .. ocorreu um erro: " + e.getMessage());
        }
        finally{
            System.out.println(" == Finalizando seção 2 de erros == ");
            System.out.println();
        }

        System.out.println("=== Status tablet ===");
        System.out.println("Código: " + tablet.getCodigo());
        System.out.println("Nome: " + tablet.getNome());
        System.out.println("Quantidade em estoque: " + tablet.getQuantidade());

        System.out.println();

        System.out.println("=== Status cellular ===");
        System.out.println("Código: " + celular.getCodigo());
        System.out.println("Nome: " + celular.getNome());
        System.out.println("Quantidade em estoque: " + celular.getQuantidade());

        System.out.println();

        System.out.println("=== Total de produtos criados ===");
        System.out.println(Produto.totalProdutos);
    }
}
