package aquecimento.A2;

import java.util.ArrayList;

import aquecimento.A2.contracts.Legendavel;
import aquecimento.A2.contracts.Midia;
import aquecimento.A2.implementation.Documentario;
import aquecimento.A2.implementation.Filme;
import aquecimento.A2.implementation.Serie;

public class App {
    public static void main(String[] args) {

        // ITEM a) ALTERANDO f E s PARA O TIPO ESTÁTICO MIDIA.
        // O ganho ocorre pelo uso do Polimorfismo. Ou seja, diferentes subclasses são tratadas da mesma forma (forma essa que é a superclasse Midia).
        Midia f = new Filme("Interestelar", 20.0); 
        Midia s = new Serie("Dark", 15.0, 3); 
        Midia d = new Documentario("Planeta Terra", 15);
        ArrayList<Midia> catalogo = new ArrayList<>(); 

        catalogo.add(f); 
        catalogo.add(s); 
        catalogo.add(d); 
 
        double totalLegenda = 0; 
        for (Midia m : catalogo) {
            // ITEM b) e c) ALTERANDO DE Serie PARA Legendavel.
            // Aqui precisa ser instanceof Legendavel e não apenas da subclasse Serie. Da forma anterior, o custo da legenda do Documentario não era contabilizado.
            if (m instanceof Legendavel) {         
                totalLegenda += ((Legendavel) m).calcularCustoLegenda();
            }
        }
        System.out.println("Total legendado: " + totalLegenda);
    }
}
