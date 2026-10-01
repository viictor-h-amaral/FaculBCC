package model;

import contracts.PlanoPago;

/* 
Respondendo à questão do enunciado:
Caso sem especialização, a classe acaba se tornando a própria classe pai. Isso
é um forte indício de que, na realidade, a classe sendo criada É, estruturalmente
e conceitualmente, a mesma coisa que a classe pai. Assim, não há necessidade de 
criá-la. 
Caso assim fizéssemos, poderíamos dizer que a classe PlanoPago é, na realidade, a
PlanoPagoBase (individual).
*/
public class PlanoIndividual extends PlanoPago {
    public PlanoIndividual(double precoMensal) {
        super("Individual", 1, precoMensal);
    }

    @Override
    public double calcularMensalidade() {
        return getPrecoMensal();
    }
}