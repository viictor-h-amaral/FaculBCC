# **Planos de ação para testes unitários 🧪**

## 1. Formatação da duração da música
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Duração com minutos e segundos | Música de 125 segundos | `Deve resultar em "02:05"` | ⏳ |
| Duração redonda em minutos | Música de 90 segundos | `Deve resultar em "01:30"` | ⏳ |
| Menos de um minuto, com zero a esquerda | Música de 5 segundos | `Deve resultar em "00:05"` |  ⏳ |
| Dois dígitos nos minutos | Música de 600 segundos | `Deve resultar em "10:00"` | ⏳ |
| Valor logo abaixo de dez minutos | Música de 599 segundos | `Deve resultar em "09:59"` | ⏳ |

## 2. Construtor da classe música
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Título vazio deve ser rejeitado | título "", artista "Queen", duracao 355 | `Deve lançar IllegalArgumentException` | ⏳ |
| Título nulo deve ser rejeitado | título null, artista "Queen", duracao 355 | `Deve lançar IllegalArgumentExceptio` | ⏳ |
| Artista vazio deve ser rejeitado | título "Bohemian Rhapsody", artista "", duração 355 | `Deve lançar IllegalArgumentException` | ⏳ |
| Duração zero deve ser rejeitada | título valido, artista valido, duracao 0 | `Deve lançar IllegalArgumentException` | ⏳ |
| Duração negativa deve ser rejeitada | título valido, artista valido, duração -10 | `Deve lançar IllegalArgumentException` | ⏳ |
| Dados validos criam a música | título "Bohemian Rhapsody", artista "Queen", duração 355 | `Objeto criado, com id maior que zero` | ⏳ |

## 3. Adicionar música à uma playlist 
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Parametro null deve ser rejeitado | parâmetro null | `Deve lançar IllegalArgumentException` | ⏳ |
| Playlist cheia não deve adicionar a música | parâmetro válido, playlist cheia | `Deve retornar False e não pode adicionar a música` | ⏳ |
| Playlist com espaço deve adicionar a música | parâmetro válido, playlist livre | `Deve retornar True e deve adicionar a música` | ⏳ |

## 4. Buscar música pelo index em uma playlist
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Index negativo deve ser rejetado | parâmetro negativo | `Deve lançar IllegalArgumentException` | ⏳ |
| Index zero deve ser rejetado | parâmetro zerado | `Deve lançar IllegalArgumentException` | ⏳ |
| Index de posição não preenchida deve ser rejetado | parâmetro entre `qnt musicas - 1` e 100 | `Deve lançar IllegalArgumentException` | ⏳ |
| Index maior que tamanho do array deve ser rejetado | parâmetro maior que 100 | `Deve lançar IllegalArgumentException` | ⏳ |
| Index válido deve retornar música esperada | parâmetro válido | `Deve retornar a música esperada` | ⏳ |

## 5. Remover música pelo index em uma playlist
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Index negativo deve ser rejetado | parâmetro negativo | `Deve lançar IllegalArgumentException` | ⏳ |
| Index zero deve ser rejetado | parâmetro zerado | `Deve lançar IllegalArgumentException` | ⏳ |
| Index de posição não preenchida (null) deve ser rejetado | parâmetro entre `qnt musicas - 1` e 100 | `Deve lançar IllegalArgumentException` | ⏳ |
| Index maior que tamanho do array deve ser rejetado | parâmetro maior ou igual a 100 | `Deve lançar IllegalArgumentException` | ⏳ |
| Index válido deve remover música esperada | parâmetro válido | `Deve remover a música esperada` | ⏳ |
| Index válido deve reordenar outras músicas, deixando nenhum 'buraco' | parâmetro válido | `Deve reordenar lista de músicas` | ⏳ |

## 6. Buscar música por Id e pelo título na Plataforma
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| id inexistente deve retornar null não deve lançar exceção | parâmetro id inexistente | `Deve retornar null e não deve lançar exceção` | ⏳ |
| título inexistente deve retornar null não deve lançar exceção | parâmetro título inexistente | `Deve retornar null e não deve lançar exceção` | ⏳ |
| id existente deve retornar a música correta | parâmetro id válido | `Deve retornar o objeto correto Musica` | ⏳ |
| título existente deve retornar a música correta | parâmetro título válido | `Deve retornar o objeto correto Musica` | ⏳ |

## 7. Reproduzir Musica
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Reproduzir música deve incrementar contador de reproduções | objeto música qualquer | `Deve incrementar o número de reproduções da música` | ⏳ |

## 8. Geração correta de IDs das músicas
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Criar nova música gera ID válido e novo (sequencial) | objeto música válido qualquer | `Deve incrementar o ID em 1 em relação ao último objeto Musica e não ter relação com IDs dos objetos Usuario` | ⏳ |

## 9. Geração correta de IDs dos Usuarios
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Criar novo usuário gera ID válido e novo (sequencial) | objeto usuário válido qualquer | `Deve incrementar o ID em 1 em relação ao último objeto Usuario e não ter relação com IDs dos objetos Musica` | ⏳ |