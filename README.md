# Sonora - Projeto de Programação Orientada a Objetos

## Visão geral
Sonora é um aplicativo simples para gerenciar músicas, usuários e playlists, desenvolvido em Java. O projeto evoluiu ao longo do tempo e atualmente usa coleções (`ArrayList`) para armazenar usuários, músicas e playlists, com validações e tratamento de exceções para evitar estados inválidos.

Principais funcionalidades atuais:
- cadastro de músicas no acervo;
- cadastro de usuários;
- criação de playlists vinculadas a um dono;
- busca por música por ID ou título;
- reprodução de músicas e contagem de reproduções;
- listagem de acervo e usuários;
- seguir / deixar de seguir outros usuários e listar usuários seguidos;
- validação de parâmetros em construtores e métodos (lançamento de `IllegalArgumentException` / `IndexOutOfBoundsException` quando aplicável).

## Mudanças recentes (resumo)
- Refatoração para `ArrayList` em lugar de arrays fixos (facilitou manipulação e testes).
- Implementação da mecânica de seguir usuários (`Usuario.seguir`, `deixarDeSeguir`, `getSeguindo`).
- Adição de testes unitários em `tests/` que cobrem construtores e operações principais.
- Atualização do diagrama de classes (`diagrama_classes_fase03.png`).

## Estrutura do projeto
- `src/` - código-fonte Java
	- `App.java` - menu interativo e fluxo principal
	- `helpers/` - utilitários de leitura/escrita (`ScannerHelper`, `Writer`)
	- `model/` - modelos de domínio (`Musica`, `Usuario`, `Playlist`, `Plataforma`)
- `tests/` - testes unitários (arquivos de teste Java)
- `docs/` - diagramas e documentação auxiliar

## Comportamentos importantes
- Validações nos construtores de `Musica` e `Usuario` impedem criação com dados inválidos (lançam `IllegalArgumentException`).
- `Playlist` valida nome e dono, lança `IndexOutOfBoundsException` ao acessar posições inválidas com `getNaPosicao`.
- `Plataforma` usa `ArrayList` para armazenar músicas e usuários e provê métodos de busca que retornam `null` quando não há resultado.
- O `App` trata entradas do usuário com `try/catch` e usa `finally` quando necessário para garantir limpeza previsível.

## Roteiro de demonstração (sugestão atualizada)
1. Tentar cadastrar uma música com título vazio e verificar que o `App` trata a `IllegalArgumentException` e continua executando.
2. Tentar cadastrar uma música com duração zero ou negativa e observar tratamento.
3. Tentar cadastrar um usuário com e-mail sem `@` e observar tratamento.
4. Tentar acessar uma posição inválida em uma playlist e observar `IndexOutOfBoundsException` tratada.
5. Cadastrar músicas e usuários, criar playlist e adicionar músicas.
6. Demonstrar seguir e deixar de seguir usuários (opções no menu: 9, 10 e 11).
7. Mostrar que buscas por ID/título que não retornam resultado devolvem `null` e são tratadas no `App`.
8. No menu, digitar entradas inválidas (ex.: letras onde se esperam números) e verificar que o programa lida com `NumberFormatException` e continua.
9. Demonstrar blocos `try/catch/finally` em operações que podem gerar exceções.

## Executando o projeto
No terminal (Linux/macOS) dentro da pasta do projeto:

```bash
javac -d bin $(find src -name "*.java")
java -cp bin App
```

No Windows PowerShell:

```powershell
javac -d bin (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp bin App
```

## Testes
Os testes unitários estão na pasta `tests/`. Eles cobrem construtores e operações principais sobre `Musica`, `Usuario`, `Playlist` e `Plataforma`.

## Diagrama
O diagrama de classes atualizado está em `diagrama_classes_fase03.png`.

## Status
Projeto em desenvolvimento. As mudanças recentes incluem refatoração para coleções dinâmicas (`ArrayList`), implementação do mecanismo de seguir usuários, e adição/ajuste de testes e diagramas.
