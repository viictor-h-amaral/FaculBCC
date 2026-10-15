# Plano de testes — RangoJá

Os testes usam JUnit Jupiter, `@BeforeEach` para preparar objetos independentes por teste e tolerância `0.001` para comparações de `double`.

## Cliente

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| C01 | Rejeitar idade abaixo do mínimo | Cliente com idade `-1` | `IllegalArgumentException` |
| C02 | Rejeitar e-mail sem `@` | `ana.furb.br` | `IllegalArgumentException` |
| C03 | Creditar indicação | Beto indicado por Ana | Ana recebe R$ 10,00 e Beto guarda Ana como indicador |
| C04 | Rejeitar uso acima do saldo | Saldo R$ 10,00; uso R$ 15,00 | `SaldoInsuficienteException`, saldo permanece R$ 10,00 |
| C05 | Comparar e-mails ignorando caixa | `ana@furb.br` e `ANA@FURB.BR` | `equals` verdadeiro |
| C06 | Aceitar idades-limite | Idades `0` e `120` | Clientes criados com as idades informadas |
| C07 | Debitar saldo disponível | Creditar e usar R$ 4,00 | Saldo final R$ 0,00 |
| C08 | Exibir identificação | Ana com e-mail `ana@furb.br` | `Ana ana@furb.br` |

## Prato

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| P01 | Rejeitar tamanho inválido | Tamanho `X` | `IllegalArgumentException` |
| P02 | Aplicar preço M e desconto | Base R$ 30,00, tamanho M, desconto 10% | R$ 35,10 |
| P03 | Rejeitar desconto acima do limite | Desconto 120% | `IllegalArgumentException` |
| P04 | Calcular tamanho P | Base R$ 30,00, tamanho P | R$ 30,00 |
| P05 | Calcular tamanho G | Base R$ 30,00, tamanho G | R$ 48,00 |
| P06 | Aceitar desconto de 0% e 100% | Prato M de base R$ 30,00 | R$ 39,00 e R$ 0,00 |

## Bebida

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| B01 | Calcular bebida alcoólica | Chopp de R$ 12,00, 500 ml | R$ 15,00 |
| B02 | Não cobrar acréscimo em bebida não alcoólica | Refri de R$ 6,00, 350 ml | R$ 6,00 |
| B03 | Rejeitar volume zero | Volume `0` ml | `IllegalArgumentException` |

## Sobremesa

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| S01 | Calcular preço proporcional | Pudim a R$ 8,00 por 100 g, com peso de 150 g | R$ 12,00 |
| S02 | Rejeitar peso zero | Peso `0` g | `IllegalArgumentException` |
| S03 | Exibir peso com unidade | Pudim de R$ 8,00/100 g, peso 150 g | Texto contém `150 g` ou `150g` |

## Pedido

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| O01 | Calcular subtotal, taxa e total | Xis M R$ 30,00 + refri R$ 6,00 + pudim R$ 8,00 por 100g (150g) | subtotal: R$ 57,00; taxa: R$ 7,00; total: R$ 64,00 |
| O02 | Dar frete grátis | Dois Xis G de R$ 40,00 | Taxa: R$ 0,00; total: R$ 128,00 |
| O03 | Impedir bebida alcoólica para menor | Cliente de 17 anos e Chopp | `IllegalArgumentException` |
| O04 | Rejeitar quantidade zero | Quantidade `0` | `IllegalArgumentException` |
| O05 | Rejeitar finalização sem itens | Pedido vazio | `IllegalStateException` |
| O06 | Creditar cashback ao finalizar | Pedido com subtotal R$ 57,00 | Cashback aumenta R$ 2,85 e pedido finaliza |
| O07 | Numerar pedidos em sequência | Criar dois pedidos sucessivos | Diferença entre números igual a 1 |
| O08 | Rejeitar inclusão após finalização | Finalizar e tentar adicionar item | `IllegalStateException` |
| O09 | Rejeitar item nulo | Adicionar `null` | `IllegalArgumentException` |

## CentralCobranca

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| CC01 | Somar pedido e assinatura | Pedido de R$ 64,00 e assinatura de R$ 19,90 por 12 meses | Total R$ 266,98 |
| CC02 | Rejeitar cobrança nula | `registrar(null)` | `IllegalArgumentException` |
| CC03 | Registrar pedido ainda não finalizado | Pedido válido não finalizado | Cobrança registrada |