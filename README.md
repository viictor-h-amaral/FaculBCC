# Sonora - Projeto de Programação Orientada a Objetos

## Visão geral
Este projeto implementa o sistema Sonora, um aplicativo simples de gerenciamento de músicas, usuários e playlists, desenvolvido em Java como parte da disciplina de Programação Orientada a Objetos.

A estrutura do sistema inclui:
- cadastro de músicas no acervo;
- cadastro de usuários;
- criação de playlists vinculadas a um dono;
- busca por música por ID ou título;
- reprodução de músicas;
- listagem de acervo e usuários;
- tratamento robusto de entradas inválidas e erros de uso.

## Seção da atividade
Projeto Sonora - Fase 02: Tratamento de exceções

O objetivo desta etapa é tornar o sistema robusto, impedindo que objetos nasçam em estado inválido, transformando parte da sinalização por valor de retorno em exceções e blindando o menu contra digitação errada.

### Regras da atividade
- continuar usando arrays fixos e não utilizar coleções do `java.util`;
- não usar herança, interfaces ou classes de exceção customizadas;
- validar construtores e impedir objetos inválidos;
- lançar `IllegalArgumentException` e `IndexOutOfBoundsException` onde o uso da classe estiver indevido;
- manter retornos `false` e `null` em casos de fluxo normal, como playlist cheia e busca sem resultado;
- tratar entradas do usuário com `try/catch` e `finally` no `App`;
- garantir que o programa continue funcionando mesmo quando o usuário digita valores inválidos.

## Fase 02 - Tratamento de exceções
A fase 02 evoluiu o projeto para garantir que o sistema não aceite objetos inválidos e que o menu consiga continuar funcionando mesmo quando o usuário digita valores errados.

### Novas implementações
- Validação de dados nos construtores de `Musica`, `Usuario` e `Playlist`.
- Uso de `IllegalArgumentException` para impedir que objetos nasçam em estado inválido.
- `IndexOutOfBoundsException` em métodos de playlist quando o índice solicitado está fora do intervalo válido.
- `adicionar` em playlist cheia continua retornando `false` como resposta válida do fluxo normal.
- `buscarMusicaPorId` e `buscarMusica` continuam retornando `null` quando não encontram resultados.
- Tratamento de entrada do menu com `try/catch`, incluindo `NumberFormatException` e `IndexOutOfBoundsException`.
- Uso de `finally` para finalizar operações de forma previsível.

### Classes principais
- `Musica`: valida título, artista e duração positiva.
- `Usuario`: valida nome e e-mail com `@`.
- `Playlist`: valida nome e dono, além de controlar posições e remoções.
- `Plataforma`: armazena músicas e usuários em arrays fixos e realiza buscas.
- `App`: menu interativo com leitura segura e tratamento de exceções.

## Roteiro obrigatório de demonstração
Prepare seu `App` para, em uma demonstração, mostrar cada um destes comportamentos. Este roteiro deve estar presente no README porque ele é a sequência que a correção costuma seguir.

1. Tentar cadastrar uma música com título vazio e mostrar que a `IllegalArgumentException` foi lançada e tratada; o programa deve avisar e continuar vivo.
2. Tentar cadastrar uma música com duração zero ou negativa e mostrar o mesmo comportamento.
3. Tentar cadastrar um usuário com e-mail sem `@` e mostrar a exceção sendo tratada.
4. Pedir `getNaPosicao` de uma posição que não existe e mostrar que agora vem `IndexOutOfBoundsException` tratada, e não `null`.
5. Encher a playlist e tentar adicionar a 101ª música, mostrando que `adicionar` ainda devolve `false` (continua sendo retorno, não exceção).
6. Buscar uma música por ID que não existe e mostrar que ainda vem `null` (também continua sendo retorno).
7. No menu, digitar uma letra onde se espera um número e mostrar que o programa avisa e pede de novo, sem cair.
8. Disparar um bloco com múltiplos `catch` e provocar duas exceções diferentes para mostrar cada `catch` agindo.
9. Mostrar o `finally` executando tanto em caso de sucesso quanto em caso de erro.

> Esta seção é importante porque ela reproduz exatamente o que a atividade exige em termos de comportamento e de evidência de funcionamento.

## Como executar
No terminal, dentro da pasta do projeto:

```bash
javac -d bin $(find src -name "*.java")
java -cp bin App
```

No Windows PowerShell:

```powershell
javac -d bin (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp bin App
```

## Observações finais
Este projeto foi desenvolvido seguindo as regras da fase 02:
- sem uso de coleções do `java.util`;
- sem herança ou interfaces;
- sem criação de classes de exceção customizadas;
- mantendo o uso de arrays fixos conforme solicitado pela atividade.

## Status
Projeto em andamento e validado conforme os requisitos da Fase 02 do Sonora.
