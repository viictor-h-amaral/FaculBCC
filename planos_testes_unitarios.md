# **Planos de ação para testes unitários 🧪**

## 1. Formatação da duração da música
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Duração com minutos e segundos | Música de 125 segundos | `Deve resultar em "02:05"` | ✅ |
| Duração redonda em minutos | Música de 90 segundos | `Deve resultar em "01:30"` | ✅ |
| Menos de um minuto, com zero a esquerda | Música de 5 segundos | `Deve resultar em "00:05"` |  ✅ |
| Dois dígitos nos minutos | Música de 600 segundos | `Deve resultar em "10:00"` | ✅ |
| Valor logo abaixo de dez minutos | Música de 599 segundos | `Deve resultar em "09:59"` | ✅ |

## 2. Construtor da classe música
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Título vazio deve ser rejeitado | título "", artista "Queen", duracao 355 | `Deve lançar IllegalArgumentException` | ✅ |
| Título nulo deve ser rejeitado | título null, artista "Queen", duracao 355 | `Deve lançar IllegalArgumentExceptio` | ✅ |
| Artista vazio deve ser rejeitado | título "Bohemian Rhapsody", artista "", duração 355 | `Deve lançar IllegalArgumentException` | ✅ |
| Duração zero deve ser rejeitada | título valido, artista valido, duracao 0 | `Deve lançar IllegalArgumentException` | ✅ |
| Duração negativa deve ser rejeitada | título valido, artista valido, duração -10 | `Deve lançar IllegalArgumentException` | ✅ |
| Dados validos criam a música | título "Bohemian Rhapsody", artista "Queen", duração 355 | `Objeto criado, com id maior que zero` | ✅ |

## 3. Adicionar música à uma playlist 
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Parametro null deve ser rejeitado | parâmetro null | `Deve lançar IllegalArgumentException` | ✅ |
| Playlist deve continuar aceitando músicas após 100 itens | 100 músicas já cadastradas e uma nova música válida | `Deve retornar True e adicionar a 101ª música` | ✅ |
| Playlist com espaço deve adicionar a música | parâmetro válido, playlist livre | `Deve retornar True e deve adicionar a música` | ✅ |

## 4. Buscar música pelo index em uma playlist
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Index negativo deve ser rejeitado | parâmetro negativo | `Deve lançar IndexOutOfBoundsException` | ✅ |
| Index de posição não preenchida deve ser rejeitado | parâmetro maior ou igual à quantidade de músicas | `Deve lançar IndexOutOfBoundsException` | ✅ |
| Index fora da lista deve ser rejeitado | parâmetro maior que a quantidade de músicas | `Deve lançar IndexOutOfBoundsException` | ✅ |
| Index válido deve retornar música esperada | parâmetro válido | `Deve retornar a música esperada` | ✅ |

## 5. Remover música pelo index em uma playlist
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Index negativo deve ser rejeitado | parâmetro negativo | `Deve lançar IndexOutOfBoundsException` | ✅ |
| Index fora da lista deve ser rejeitado | parâmetro maior ou igual à quantidade de músicas | `Deve lançar IndexOutOfBoundsException` | ✅ |
| Index maior que o tamanho da lista deve ser rejeitado | parâmetro muito maior que a quantidade de músicas | `Deve lançar IndexOutOfBoundsException` | ✅ |
| Index válido deve remover música esperada | parâmetro válido | `Deve remover a música esperada` | ✅ |
| Remover música deve reordenar as outras, deixando nenhum 'buraco' | parâmetro válido | `Deve reordenar lista de músicas` | ✅ |

## 6. Buscar música por Id e pelo título na Plataforma
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| id inexistente deve retornar null não deve lançar exceção | parâmetro id inexistente | `Deve retornar null e não deve lançar exceção` | ✅ |
| título inexistente deve retornar null não deve lançar exceção | parâmetro título inexistente | `Deve retornar null e não deve lançar exceção` | ✅ |
| id existente deve retornar a música correta | parâmetro id válido | `Deve retornar o objeto correto Musica` | ✅ |
| título existente deve retornar a música correta | parâmetro título válido | `Deve retornar o objeto correto Musica` | ✅ |

## 7. Reproduzir Musica
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Reproduzir música deve incrementar contador de reproduções | objeto música qualquer | `Deve incrementar o número de reproduções da música` | ✅ |

## 8. Geração correta de IDs das músicas
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Criar nova música gera ID válido e novo (sequencial) | objeto música válido qualquer | `Deve incrementar o ID em 1 em relação ao último objeto Musica e não ter relação com IDs dos objetos Usuario` | ✅ |

## 9. Geração correta de IDs dos Usuarios
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Criar novo usuário gera ID válido e novo (sequencial) | objeto usuário válido qualquer | `Deve incrementar o ID em 1 em relação ao último objeto Usuario e não ter relação com IDs dos objetos Musica` | ✅ |

## 10. Buscar usuário por Id e pelo título na Plataforma
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| id inexistente deve retornar null não deve lançar exceção | parâmetro id inexistente | `Deve retornar null e não deve lançar exceção` | ✅ |
| título inexistente deve retornar null não deve lançar exceção | parâmetro título inexistente | `Deve retornar null e não deve lançar exceção` | ✅ |
| id existente deve retornar a música correta | parâmetro id válido | `Deve retornar o objeto correto Musica` | ✅ |
| título existente deve retornar a música correta | parâmetro título válido | `Deve retornar o objeto correto Musica` | ✅ |


## 11. Cadastrar música na plataforma
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Música nula deve ser rejeitada | parâmetro null | `Deve lançar IllegalArgumentException` | ✅ |
| Plataforma deve continuar aceitando mais de 500 músicas | 500 músicas já cadastradas e uma nova música válida | `Deve retornar True e cadastrar a música extra` | ✅ |
| Plataforma com espaço deve cadastrar a música | parâmetro válido, plataforma livre | `Deve retornar True e deve adicionar a música` | ✅ |
| ArrayList de músicas com espaço deve cadastrar todas | lista de músicas válidas | `Deve retornar True e inserir todas as músicas` | ✅ |
| ArrayList com mais de 500 músicas deve ser aceita | lista com 501 músicas válidas | `Deve retornar True e cadastrar todas as 501 músicas` | ✅ |

## 12. Cadastrar usuário na plataforma
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Usuário nulo deve ser rejeitado | parâmetro null | `Deve lançar IllegalArgumentException` | ✅ |
| Plataforma deve continuar aceitando mais de 500 usuários | 500 usuários já cadastrados e um novo usuário válido | `Deve retornar True e cadastrar o usuário extra` | ✅ |
| Plataforma com espaço deve cadastrar o usuário | parâmetro válido, plataforma livre | `Deve retornar True e deve adicionar o usuário` | ✅ |
| ArrayList de usuários com espaço deve cadastrar todos | lista de usuários válidos | `Deve retornar True e inserir todos os usuários` | ✅ |
| ArrayList com mais de 500 usuários deve ser aceita | lista com 501 usuários válidos | `Deve retornar True e cadastrar todos os 501 usuários` | ✅ |

## 13. Construtor da classe usuário
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Nome vazio deve ser rejeitado | nome " ", email "usuario@gmail.com" | `Deve lançar IllegalArgumentException` | ✅ |
| Nome nulo deve ser rejeitado | nome null, email "usuario@gmail.com" | `Deve lançar IllegalArgumentException` | ✅ |
| Email vazio deve ser rejeitado | nome "Usuario", email " " | `Deve lançar IllegalArgumentException` | ✅ |
| Email nulo deve ser rejeitado | nome "Usuario", email null | `Deve lançar IllegalArgumentException` | ✅ |
| Email sem arroba deve ser rejeitado | nome "Usuario", email "usuariogmail.com" | `Deve lançar IllegalArgumentException` | ✅ |
| Dados válidos criam o usuário | nome "Usuario", email "usuario@gmail.com" | `Objeto criado, com id maior que zero` | ✅ |

## 14. Seguir e deixar de seguir usuários
| descrição | contexto | saída esperada | feito |
|:-|:-:|:-:|-:|
| Seguir usuário repetido deve ser rejeitado | usuário já segue outro usuário e tenta segui-lo novamente | `Deve lançar IllegalArgumentException e manter quantidade em 1` | ✅ |
| Usuário não deve seguir a si mesmo | usuário tenta seguir a própria instância | `Deve lançar IllegalArgumentException e manter quantidade em 0` | ✅ |
| Seguir usuário nulo deve ser rejeitado | usuário tenta seguir `null` | `Deve lançar IllegalArgumentException` | ✅ |
| Deixar de seguir deve remover a relação | usuário segue outro e depois deixa de seguir | `Quantidade seguindo deve retornar para 0` | ✅ |
| Deixar de seguir usuário nulo deve ser rejeitado | usuário tenta deixar de seguir `null` | `Deve lançar IllegalArgumentException` | ✅ |