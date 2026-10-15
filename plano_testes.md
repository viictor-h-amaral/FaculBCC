# Plano de testes — RangoJá

Os testes usam JUnit Jupiter, `@BeforeEach` para preparar objetos independentes por teste e tolerância `0.001` para comparações de `double`.

## ItemCardapio

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| I01 | Rejeitar nome nulo ou vazio | Nome `null` ou `""` | `IllegalArgumentException` |
| I02 | Rejeitar preço base zero | Preço base `0` | `IllegalArgumentException` |
| I03 | Alterar preço base para valor positivo | Preço base novo R$ 35,00 | Getter e cálculo refletem R$ 35,00 |
| I04 | Não alterar preço base para zero | Setter com valor `0` | `IllegalArgumentException`; valor anterior preservado |

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
| B04 | Exibir volume e indicação alcoólica | Chopp de R$ 12,00, 500 ml | Texto contém `(500 ml) [alcoólica]` |

## Sobremesa

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| S01 | Calcular preço proporcional | Pudim a R$ 8,00/100 g, peso 150 g | R$ 12,00 |
| S02 | Rejeitar peso zero | Peso `0` g | `IllegalArgumentException` |
| S03 | Exibir peso com unidade | Pudim de R$ 8,00/100 g, peso 150 g | Texto contém `(150 g)` |

## Pedido

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| O01 | Calcular subtotal, taxa e total | Xis M R$ 30,00 + refri R$ 6,00 + pudim R$ 8,00/100 g (150 g) | R$ 57,00; R$ 7,00; R$ 64,00 |
| O02 | Dar frete grátis | Dois Xis G de R$ 40,00 | Taxa R$ 0,00; total R$ 128,00 |
| O03 | Impedir bebida alcoólica para menor | Cliente de 17 anos e Chopp | `IllegalArgumentException` |
| O04 | Rejeitar quantidade zero | Quantidade `0` | `IllegalArgumentException` |
| O05 | Rejeitar finalização sem itens | Pedido vazio | `IllegalStateException` |
| O06 | Creditar cashback ao finalizar | Pedido com subtotal R$ 57,00 | Cashback aumenta R$ 2,85 e pedido finaliza |
| O07 | Numerar pedidos em sequência | Criar dois pedidos sucessivos | Diferença entre números igual a 1 |
| O08 | Rejeitar inclusão após finalização | Finalizar e tentar adicionar item | `IllegalStateException` |
| O09 | Rejeitar item nulo | Adicionar `null` | `IllegalArgumentException` |

## AssinaturaClube

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| A01 | Aplicar desconto anual | R$ 19,90 por 12 meses | R$ 202,98 |
| A02 | Não aplicar desconto antes de 12 meses | R$ 19,90 por 11 meses | R$ 218,90 |
| A03 | Rejeitar cliente nulo | Cliente `null` | `IllegalArgumentException` |
| A04 | Rejeitar valor mensal zero | Valor mensal `0` | `IllegalArgumentException` |
| A05 | Rejeitar quantidade de meses zero | Meses `0` | `IllegalArgumentException` |
| A06 | Gerar comprovante pelo contrato | Assinatura de R$ 10,00 por mês durante 1 mês | `Valor a cobrar: R$ 10.00` |

## CentralCobranca

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| CC01 | Somar pedido e assinatura | Pedido de R$ 64,00 e assinatura de R$ 19,90 por 12 meses | Total R$ 266,98 |
| CC02 | Rejeitar cobrança nula | `registrar(null)` | `IllegalArgumentException` |
| CC03 | Registrar pedido ainda não finalizado | Pedido válido não finalizado | Cobrança registrada |
| CC04 | Gerar relatório com comprovantes e total | Pedido de R$ 64,00 e assinatura de R$ 10,00 | Duas linhas de cobrança e total R$ 74,00 |

## SaldoInsuficienteException

| Caso | Descrição | Entrada | Saída esperada |
|---|---|---|---|
| E01 | Confirmar que a exceção é verificável | Instância da exceção | É subclasse de `Exception` |
| E02 | Incluir valores na mensagem | Saldo R$ 4,00, solicitação R$ 10,00 | Mensagem informa saldo e valor solicitado |