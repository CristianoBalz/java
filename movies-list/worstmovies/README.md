# WorstList

API Rest desenvolvida em Java usando Spring Boot Versão 2.5 e JDK_8 e Maven como controlador de dependências.
Esta API lê um arquivo CSV que contém uma lista de filmes indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards e insere os dados desta lista em uma base de dados H2 em memória ao iniciar a aplicação.

A API possui um endpoint que retorna o produtor dos filmes com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido.

E outro endpoint paginado que lista os filmes.

## Testes de integração

Para rodar os testes de integração, execute o comando abaixo em um prompt de comando no diretório da aplicação:

``mvn clean test``

## Instalação

 - Necessário instalação do Java JDK_8 e Maven.

### Compilação do fonte

Rode o comando Maven abaixo abrindo um prompt de comando no diretório da aplicação:

``mvn clean install``

Observação: Este comando irá compilar o código fonte da API e gerar o executável `jar` chamado `worstmovies-0.0.1-SNAPSHOT.jar` na pasta `target`. Caso o comando `mvn` não seja reconhecido pelo prompt de comando o `Maven` deve ser configurado nas variáveis de ambiente.

### Arquivo csv

Junto ao executável jar deve existir o arquivo `movielist.csv` com a listagem dos filmes. Este arquivo deve seguir o padrão abaixo:

``
year;title;studios;producers;winner   
1980;Can't Stop the Music;Associated Film Distribution;Allan Carr;yes    
1980;Cruising;Lorimar Productions, United Artists;Jerry Weintraub;    
...
``

Importante: sem este arquivo a aplicação não poderá ser iniciada.

### Executar a API

Navegue até o diretório `target`, abra um prompt de comando e rode o comando `java -jar worstmovies-0.0.1-SNAPSHOT.jar` para iniciar a aplicação.

Importante: certifique-se de que exista um arquivo chamado `movielist.csv` junto com o arquivo `jar` antes de iniciar a aplicação. A API roda na porta `8080`, portanto, certifique que nenhum outro serviço esteja rodando nesta porta antes de iniciar a aplicação.

PRONTO!! A aplicação já deverá estar de pé e rodando.

Para certificar que a API esteja funcionando corretamente abra um navegador de internet e navegue neste endereço `http://localhost:8080` que deverá retornar `It's running.` indicando que a API esta rodando corretamente.

## Usage

- Endpoint que retorna o produtor dos filmes com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido

URL: `http://{DOMAIN}:8080/producers/prize-range`

Exemplo de retorno:

``{"min":[{"producer":"Joel Silver","interval":1,"previousWin":1990,"followingWin":1991}],"max":[{"producer":"Matthew Vaughn","interval":13,"previousWin":2002,"followingWin":2015}]}``

- Endpoint que retorna a listagem paginada dos filmes que estão carregados no banco em memória.

URL: `http://{DOMAIN}:8080/movies/list?size=2`

Exemplo de retorno:
``{"content":[{"year":2019,"title":"The Fanatic","studios":"Quiver Distribution","producers":"Daniel Grodnik, Oscar Generale, and Bill Kenwright"},{"year":2019,"title":"Cats","studios":"Universal Pictures","producers":"Debra Hayward, Tim Bevan, Eric Fellner, and Tom Hooper"}],"pageable":{"sort":{"unsorted":false,"sorted":true,"empty":false},"offset":0,"pageNumber":0,"pageSize":2,"unpaged":false,"paged":true},"totalElements":206,"totalPages":103,"last":false,"size":2,"number":0,"sort":{"unsorted":false,"sorted":true,"empty":false},"numberOfElements":2,"first":true,"empty":false}``
