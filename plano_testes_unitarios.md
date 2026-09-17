# **PLANO DE TESTES**


## Produtos.remover()
| caso | entrada | cenário | resultado esperado |
| :-- | :-- | :--: | --: | 
| 1 | qnt = -1 | remover qnt negativa | Exceção IllegalArgumentException |
| 2 | qnt > quantidade | remover mais itens do que existem | Exceção IllegalStateException |
| 3 | qnt válida | remover quantidade válida do estoque | remover corretamente a quantidade do estoque | 

## Produtos()
| caso | entrada | cenário | resultado esperado |
| :-- | :-- | :--: | --: | 
| 1 | quantidade < 0 | criar produto com quantidade negaiva | Exceção IllegalArgumentException |
| 2 | 3 parâmetros válidos | criar produto válido | Cria objeto com os parâmetros de input |
| 3 | vários objetos válidos | criar vários produtos válidos | incrementa totalProdutos corretamente |