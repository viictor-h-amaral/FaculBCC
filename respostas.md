# **RESPOSTAS**


1. Por que proximoNumero é static e numero não é? 
2. Por que calcularPrecoComDesconto é final e calcularPreco é abstract? Um mesmo método poderia 
ser as duas coisas? 
3. Se a navegação entre Pedido e Cliente fosse bidirecional, o que mudaria na classe Cliente? 
4. Por que usarCashback precisa de throws e adicionarItem não precisa, mesmo os dois lançando 
exceções

## **1.** Por que proximoNumero é static e numero não é? 

> Pois proximoNumero é um contador global da Classe, usado na criação de cada objeto. Já numero é o identificador individual da instância, sendo originado de proximoNumero.

## **2.** Por que calcularPrecoComDesconto é final e calcularPreco é abstract? Um mesmo método poderia ser as duas coisas? 

> Da prmieira pergunta, calcularPrecoComDesconto é final pois a regra de desconto não varia, é comum para todos itens. Já o calcularPreco é abstract pois seu contexto de cálculo muda conforme o item do cardápio.
> Da segunda pergunta, a resposta é não. Nenhum método pode ser abstract (implementação individual por classe filha) e final (implementação única na classe mãe, sem override) simultâneamente.

## **3.** Se a navegação entre Pedido e Cliente fosse bidirecional, o que mudaria na classe Cliente? 

> A classe Ciente teria que ter uma lista de Pedidos, por exemplo: ArrayList<Pedido> pedidos.

## **4.** Por que usarCashback precisa de throws e adicionarItem não precisa, mesmo os dois lançando exceções

> Pois a exceção que usarCashback lança é SaldoInsuficienteException, que herda de Exception e é, portanto, uma exceção checked. Já adicionarItem lança exceções unchecked: IllegalArgumentException e IllegalStateException herdadas de RuntimeException.