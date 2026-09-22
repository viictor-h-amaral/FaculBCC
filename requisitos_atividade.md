
1

Automatic Zoom
 
 
UNIVERSIDADE REGIONAL DE BLUMENAU 
CENTRO DE CIÊNCIAS EXATAS E NATURAIS 
DEPARTAMENTO DE SISTEMAS E COMPUTAÇÃO 
PROFESSOR ANDRÉ FELIPE BÜRGER 
PROGRAMAÇÃO ORIENTADA A OBJETOS  
 
 
 
Contexto Um  detalhe  que  você  já  percebeu  usando  streaming:  a  plataforma  não  toca  só  música.  Tem  podcast,  tem 
audiolivro, tem vários tipos de conteúdo convivendo no mesmo app. No fundo, uma música e um podcast têm 
muita coisa em comum: os dois têm título, duração, um id, e os dois podem ser reproduzidos. 
Nesta fase o Sonora vai reconhecer mais de um tipo de conteúdo, e você vai modelar isso com herança. A ideia 
é ter uma superclasse Conteudo, que concentra o que é comum a qualquer conteúdo, e duas subclasses que a 
especializam: Musica e Podcast. Assim você reaproveita código e deixa cada subclasse cuidar só do que a torna 
diferente. 
Preparação Como nas fases anteriores, esta atividade é incremental. Clone o projeto da fase passada e trabalhe por cima 
dele: 
• Copie sonora-fase04 para uma nova pasta sonora-fase05. 
• Mantenha o pacote sonora e tudo que já funcionava. 
• Nada do que você já entregou pode quebrar: ao terminar a Fase 05, o projeto continua compilando e 
rodando. 
O que você vai praticar • Herança com extends e a relação "é um(a)". 
• Superclasse e subclasse, e a leitura da hierarquia de herança. 
• Membros protected e o símbolo # na UML. 
• Sobrescrita de método com @Override. 
• Reuso do método da superclasse com super.metodo(). 
• Encadeamento de construtores com super(...). 
• A classe Object e a sobrescrita de toString(). 
 
Antes de escrever qualquer código, atualize o diagrama de classes com a nova hierarquia: Musica e Podcast 
herdando de Conteudo, com a seta de especialização, os membros protegidos marcados com # e os atributos 
de cada classe no lugar certo. Use esse diagrama como plano para a implementação e envie junto com os 
códigos. 
 
Parte A: crie a superclasse Conteudo Comece pela base da hierarquia. A classe Conteudo guarda o que todo conteúdo tem, independentemente de 
ser música ou podcast. 
Requisitos da Conteudo: 
• Um id próprio, gerado por um contador estático, no mesmo padrão das outras classes. 
• titulo (texto, obrigatório, não pode ser nulo nem vazio). 
• duracaoSegundos (inteiro, deve ser maior que zero). 
 
 
UNIVERSIDADE REGIONAL DE BLUMENAU 
CENTRO DE CIÊNCIAS EXATAS E NATURAIS 
DEPARTAMENTO DE SISTEMAS E COMPUTAÇÃO 
PROFESSOR ANDRÉ FELIPE BÜRGER 
PROGRAMAÇÃO ORIENTADA A OBJETOS  
 
 
 
• Getters e setters, sempre validando as regras acima e lançando IllegalArgumentException quando 
violadas, como você já faz desde a Fase 02. 
• Um método reproduzir() que imprime na tela que o conteúdo está sendo reproduzido. 
• O acesso ao id (o setter e/ou o contador) deve ser protected, para que as subclasses consigam usar, 
mas o restante do sistema não. 
 
Esqueleto sugerido (preencha os corpos): 
public class Conteudo { 
  
    private static int contador = 0; 
    private int id;     private String titulo; 
    private int duracaoSegundos; 
  
    public Conteudo(String titulo, int duracaoSegundos) { 
        this.id = ++contador; 
        setTitulo(titulo); 
        setDuracaoSegundos(duracaoSegundos); 
    } 
  
    public int getId() { 
        return id; 
    } 
  
    protected void setId(int id) { 
        this.id = id; 
    } 
  
    // getTitulo, setTitulo (valida vazio/nulo), 
    // getDuracaoSegundos, setDuracaoSegundos (valida > 0) 
  
    public void reproduzir() { 
        System.out.println("Reproduzindo: " + toString()); 
    } 
  
    @Override 
    public String toString() { 
        return "[" + getId() + "] " + titulo 
             + " (" + duracaoSegundos + "s)"; 
    } 
} 
Parte B: especialize com Musica e Podcast Agora crie as subclasses. Cada uma herda de Conteudo e acrescenta apenas o que é específico dela. 
1.  Faça Musica extends Conteudo, adicionando artista e album. Se a Musica já existia das fases 
anteriores, faça-a passar a herdar de Conteudo e deixe nela só o que é específico (o id, o título e a 
duração agora vêm da superclasse). 
2.  Crie Podcast extends Conteudo, adicionando apresentador e numeroEpisodio (inteiro, deve ser 
maior ou igual a 1). 
