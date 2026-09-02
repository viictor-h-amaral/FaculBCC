# Planos de ação para classe Música:


## Formatação da duração da música
| descrição | contexto | saída esperada |
|-:|-:|-:|
| Duração com minutos e segundos | Música de 125 segundos | Deve resultar em "02:05" |
| Duração redonda em minutos | Música de 90 segundos | Deve resultar em "01:30" |
| Menos de um minuto, com zero a esquerda | Música de 5 segundos | Deve resultar em "00:05" | 
| Dois dígitos nos minutos | Música de 600 segundos | Deve resultar em "10:00" |
| Valor logo abaixo de dez minutos | Música de 599 segundos | Deve resultar em "09:59" |