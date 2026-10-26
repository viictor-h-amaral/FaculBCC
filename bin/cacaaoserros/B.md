## **B1. Limite**

### Código 🎱

```java
if (itens.size() > 5) {     
    throw new IllegalStateException("Orçamento lotado"); 
} 
itens.add(item); 
```

### Erro ❌

> O erro existe pois o comparador entre `itens.size()` e `5` é `>`, enquanto deveria ser `>=`.

### Caso de teste 🧪

- **Cenário:** há exatamemente 5 itens em `itens`.
- **Ação:** Tentar adicionar um sexto item na lista.
- **Resultado real:** Validação de `itens.size() > 5` retorna `false`, pois `itens.size() == 5`, não lançando exceção.
- **Resultado esperado:** Lançar exceção, pois o máximo de itens em `itens` é `5`.

### Código corrigido ✅

```java
if (itens.size() >= 5) {     
    throw new IllegalStateException("Orçamento lotado"); 
} 
itens.add(item); 
```

## **B2. Tipo da exceção**

### Código 🎱

```java
if (itens.contains(item)) {     
    throw new IllegalArgumentException("Item já incluído no orçamento"); 
} 
```

### Erro ❌

> O erro existe pois a exceção lançada é de `Argumento inválido`, enquanto deveria ser `Estado inválido`. Para um argumento `null`, a exceção deveria ser de argumento inválido: `IllegalArgumentException`

### Caso de teste 🧪

- **Cenário:** a lista `itens` contém o item `abc`.
- **Ação:** Tentar adicionar novamente o objeto `abc`.
- **Resultado real:** Validação de `itens.contains(item)` retorna `true` e lança a exceção `IllegalArgumentException("Item já incluído no orçamento")`.
- **Resultado esperado:** Validação de `itens.contains(item)` retorna `true` e lança a exceção `IllegalStateException("Item já incluído no orçamento")`.

### Código corrigido ✅

```java
if (itens.contains(item)) {     
    throw new IllegalStateException("Item já incluído no orçamento"); 
} 
```

## **B3. Ordem das validações**

### Código 🎱

```java
if (item == null) throw new IllegalArgumentException("Item inválido"); 
if (itens.size() == 5) throw new IllegalStateException("Orçamento lotado"); 
if (itens.contains(item)) throw new IllegalStateException("Item já incluído no orçamento");
```

### Erro ❌

> O erro existe pois a order de validação deveria ser: `null`, `item repetido` e `lista cheia`.

### Caso de teste 🧪

- **Cenário:** a lista `itens` está cheia e um de seus itens é o item `abc`.
- **Ação:** Tentar adicionar novamente o objeto `abc`.
- **Resultado real:** Validação de `itens.size() == 5` retorna `true` e lança a exceção `IllegalStateException("Orçamento lotado")` sem nem validar `itens.contains(item)`.
- **Resultado esperado:** Validação de `itens.contains(item)` vem antes de `itens.size() == 5` e retorna `true`, lançando a exceção `IllegalStateException("Item já incluído no orçamento")`.

### Código corrigido ✅

```java
if (item == null) throw new IllegalArgumentException("Item inválido"); 
else if (itens.contains(item)) throw new IllegalStateException("Item já incluído no orçamento");
else if (itens.size() >= 5) throw new IllegalStateException("Orçamento lotado"); 
```

## **B4. Remoção**

### Código 🎱

```java
public void removerItem(ItemSetup item) {     
    for (ItemSetup i : itens) {         
        if (i != item) {             
            throw new IllegalStateException("Item não encontrado no orçamento");         
        }     
    }     
    itens.remove(item); 
} 
```

### Erro ❌

> O erro existe pois ao tentar remover qualquer item que não seja o primeiro, `removerItem` irá lançar a exceção `IllegalStateException("Item não encontrado no orçamento")`.  Também está errado no caso de não haver itens na Lista. Nesse caso, o foreach não é percorrido e `itens.remove(item)` é executado sem mesmo `itens` possuir elementos.

### Caso de teste 🧪

- **Cenário 1:** a lista `itens` está vazia.
- **Ação 1:** Tentar remover um objeto `x`.
- **Resultado real 1:** Nada ocorre. Nenhum erro é lançado e o usuário não é informado.
- **Resultado esperado 1:** Lançar uma exceção `IllegalStateException` dizendo que tal item não existe na lista.

- **Cenário 2:** a lista `itens` contém os elementos `item1` e `item2`, nos indexes `0` e `1`, respectivamente.
- **Ação 2:** Tentar remover o `item2`.
- **Resultado real 2:** É lançada a exceção `IllegalStateException("Item não encontrado no orçamento")`.
- **Resultado esperado 2:** O item `item2` é removido corretamente.

### Código corrigido ✅

```java
public void removerItem(ItemSetup item) {     
    if (!itens.contains(item)) {         
        throw new IllegalStateException("Item não encontrado no orçamento");            
    }     
    itens.remove(item); 
} 
```

## **B5. Contador**

### Código 🎱

```java
public ItemSetup(String descricao, double precoBase) {     
    totalItens++;     
    codigo = totalItens;     
    if (precoBase <= 0) {         
        throw new IllegalArgumentException("Preço base inválido");     
    }     
    this.descricao = descricao;     
    this.precoBase = precoBase; 
}
```

### Erro ❌

> O erro existe pois o contador `totalItens` é incrementado antes da validação de `precoBase <= 0`. Com isso, ao tentar criar um item com preco base inválido, o objeto não é criado, mas o contador é incrementado.

### Caso de teste 🧪

- **Cenário:** três itens são criados em sequência: `item1` válido, `itemX` inválido (não é criado), `item2` válido.
- **Ação:** Consultar seus códigos.
- **Resultado real:** `item1` tem o código `1` e `item2` tem o código 3, apesar de ter sido o segundo item a ser criado.
- **Resultado esperado:** `item2` deveria ter o código `2`, e não `item3`.

### Código corrigido ✅

```java
public ItemSetup(String descricao, double precoBase) {     
    if (precoBase <= 0) {         
        throw new IllegalArgumentException("Preço base inválido");     
    }    
    this.precoBase = precoBase; 
    totalItens++;     
    codigo = totalItens;
    this.descricao = descricao;     
}
```

## **B6. Base do cálculo**

### Código 🎱

```java
@Override 
public double calcularGarantiaEstendida() {     
    return getPrecoBase() * quantidade * 0.15; 
}
```

### Erro ❌

> O erro está em não reaproveitar o cálculo de calcularPreco(). O retorno de calcularPreco() já incluí descontos e regra de `quantidade * preco`.

### Caso de teste 🧪

- **Cenário:** Três monitores de R$ 1200,00 cada.
- **Ação:** Executar calcularGarantiaEstendida().
- **Resultado real:** retorna o valor de `R$ 1200 * 3 * 0.15 = R$ 540,00`.
- **Resultado esperado:** retornar o valor de `( R$ 1200  * 3 * 0.90) * 0.15 = R$ 486,00`.

### Código corrigido ✅

```java
@Override 
public double calcularGarantiaEstendida() {     
    return calcularPreco() * 0.15; 
}
```

## **B7. toString sem @Override**

### Código 🎱

```java
public String tostring() {     
    return getCodigo() + " - " + getDescricao() + " - R$ " + calcularPreco(); 
}
```

### Erro ❌

> O primeiro erro está no nome do método: to***s***tring. O segundo problema está em não utilizar @Override.

### Caso de teste 🧪

- **Cenário:** Criar um `ItemSetup` com `codigo = 1`, `descricao = "monitor"` e `precoBase = R$ 1200,00`.
- **Ação:** chamar o método `toString` 
- **Resultado real:** Aparecerá algo como `caminho_arquivo.ItemSetup.sub_classe@14da3...`, pois o toString() original não foi sobrescrito.
- **Resultado esperado:** Deveria aparecer `1 - monitor - R$ 1200,00`

> ⚠️ OBS.: ao tentar compilar o método de assinatura to**s**tring com o @Override, o compilador lança o erro: `The method tostring() of type ItemSetup must override or implement a supertype method`

### Código corrigido ✅

```java
@Override 
public String toString(){
    return codigo + " - " + descricao + " - " + String.format("R$ %.2f", calcularPreco());
}
```

## **B8. main**

### Código 🎱

```java
Peca placa = new Peca("Placa de vídeo", 2500.0, 1); 
orcamento.adicionarItem(placa); 
System.out.println("Solicitante: " + orcamento.getSolicitante()); 
System.out.println(orcamento.getItens()); 
orcamento.adicionarItem(placa); 
```

### Erros ❌

```java
Peca placa = new Peca("Placa de vídeo", 2500.0, 1); // ❌ deveria ser do tipo estático ItemSetup
orcamento.adicionarItem(placa); 
System.out.println("Solicitante: " + orcamento.getSolicitante()); // ❌ para exibir o nome do solicitante, deveria ser orcamento.getSolicitante().getNome()
System.out.println(orcamento.getItens()); // ❌ para percorrer cada item de orcamento, deveria criar um loop, um foreach por exemplo.
orcamento.adicionarItem(placa); // ❌ vai lanãr uma exceção pois o item placa já foi adicionado em orcamento. Como não tem tratamento, vai "querar" a aplicação.
```

### Código corrigido ✅

```java
ItemSetup placa = new Peca("Placa de vídeo", 2500.0, 1);
orcamento.adicionarItem(placa); 
System.out.println("Solicitante: " + orcamento.getSolicitante().getNome()); 

for (var item : orcamento.getItens()){
    System.out.println(item.toString());
}

try{
    orcamento.adicionarItem(placa);
} catch (Exception e){
    System.err.println("Erro! Mensagem: " + e.getMessage());
}
```