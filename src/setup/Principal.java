package setup;

import setup.contracts.ItemSetup;
import setup.implementation.Cliente;
import setup.implementation.Orcamento;
import setup.implementation.ItensSetup.Montagem;
import setup.implementation.ItensSetup.Peca;
import setup.implementation.ItensSetup.Software;

public class Principal {
    public static void main(String[] args) {
        System.out.println("========== BASE ==========");
        System.out.println("Itens criados: " + ItemSetup.getTotalItens());
        
        Orcamento orcamento = new Orcamento(new Cliente("Marina Souza", "123.456.789-00"), "Blumenau", 7);

        ItemSetup placaVideo = new Peca("Placa de vídeo", 2500.0, 1);
        ItemSetup monitor = new Peca("Monitor 27 polegadas", 1200.0, 3);
        ItemSetup montagemECabeamento = new Montagem("Montagem e cabeamento", 80.0, 2);
        
        orcamento.adicionarItem(placaVideo); 
        orcamento.adicionarItem(monitor); 
        orcamento.adicionarItem(montagemECabeamento); 
        
        System.out.println("Solicitante: " + orcamento.getSolicitante().getNome()); 
        System.out.println("Entrega: " + orcamento.getCidadeEntrega() + ", em " + orcamento.getPrazoEntrega() + " dias"); 

        System.out.println("Itens do orçamento: ");
        for (var item : orcamento.getItens()){
            System.out.println(item.toString());
        }

        System.out.println("Valor total: " + String.format("R$ %.2f", orcamento.calcularValorTotal()));
        System.out.println("Garantia estendida: " + String.format("R$ %.2f", orcamento.calcularTotalGarantia()));

        try{
            orcamento.adicionarItem(placaVideo);
        } catch (Exception e){
            System.err.println("Erro: " + e.getMessage());
        }

        try{
            @SuppressWarnings("unused")
            ItemSetup montagemX = new Montagem("Montagem e cabeamento", 80.0, 0);
        } catch (Exception e){
            System.err.println("Erro: " + e.getMessage());
        }

        // O total de itens criados vai ser 4 pois o construtor de Montagem primeiro chama super (que incrementa totalItens), e somente
        // depois, de volta no construtor de Montagem, que é feita a validação horas <= 0.
        System.out.println("Itens criados: " + ItemSetup.getTotalItens());

        System.out.println("========== BONUS ==========");

        ItemSetup sistemaOperacional = new Software("Sistema operacional", 300.0, 2);
        orcamento.adicionarItem(sistemaOperacional); 
        System.out.println("Valor total: " + String.format("R$ %.2f", orcamento.calcularValorTotal()));
        System.out.println("Garantia estendida: " + String.format("R$ %.2f", orcamento.calcularTotalGarantia()));
        System.out.println("Itens criados: " + ItemSetup.getTotalItens()); 
    }
}
