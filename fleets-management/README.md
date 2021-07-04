# Fleets Management API

API Rest desenvolvida em Java usando Spring Boot Versão 2.5, JDK_8, Maven como controlador de dependências e H2 Database como banco de dados.
Esta API Rest é responsável pelo cadastro e manipulação de veículos e também pelo cálculo de previsão de gastos de combustível destes veículos, considerando a média de consumo de combustível destes veículos dentro da cidade e em rodovias.

A aplicação roda na porta 8080 e pode ser acessada através deste endereço (após iniciada):

`http://[DOMAIN]:8080/`

Após iniciar a aplicação, ao acessar o link acima trocando a palavra `[DOMAIN]` pelo dominio em que a aplicação esta rodando, deve ser possível obter o seguinte retorno:

`It's running.`

Este retorno indica que a API esta rodando corretamente. :)

**Importante**

- **Todos os endpoints são autenticados via Bearer JWT (token de autenticação). Usar `login`com valor `admin` e senha `password` com valor `w#XJrPcC` para gerar o token. O token deve ser gerado usando a request responsável por gerar o token de acesso informada na tabela logo abaixo.**
- **Todos as requests devem possuir no `header` a chave `Authentication` e como valor o token gerado na requisição `http://[DOMAIN]:8080/auth`. O valor na chave `Authentication`  deve conter a palavra `Bearer ` seguida de um `espaço em branco` e concatenada com o `token` gerado. Exemplo: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJSZXN0IEFQSSBGbGVldHMgTWFuYWdlbWVudCIsInN1YiI6IjExNSIsImlhdCI6MTYyNTQzMzQwMywiZXhwIjoxNjI1NTE5ODAzfQ.ai2bUUxLVZITWyuOCxbyENzIPZLUOGrWvwM1bn2ii4E`**
- **O corpo de todas as requisições via POST devem ser enviadas em formato `JSON` com a chave `Content-Type` igual a `application/json` no `header` da requisição.** 

## Endpoints:
| URI                                                 |    Método     |  Body                                              |  Finalidade                                        |
|-----------------------------------------------------|:-------------:|:---------------------------------------------------|:---------------------------------------------------|
| http://[DOMAIN]:8080/                               |  GET          |                                                    | Verificar se a API está rodando.                   |
| http://[DOMAIN]:8080/auth                           |  POST         |{ "login":"admin","password": "w#XJrPcC" }          | Realizar a autenticação, retorna o token de acesso |
| http://[DOMAIN]:8080/vehicle/list                   |  GET          |                                                    | Retorna uma lista paginada dos veículos            |
| http://[DOMAIN]:8080/vehicle                        |  POST         |{"brand" : "Marca","model" : "Modelo","name" : "Veiculo x","averageHighwayConsumption" : "14.6","averageCityConsumption" : "12.8","fabricationDate" : "12/12/2000"}                                                    | Cadastra um novo veículo           |
| http://[DOMAIN]:8080/vehicle/{ID}                   |  PUT          |{"name": "Veiculo alterado","model": "Modelo alterado", "brand": "Marca alterada","fabricationDate": "01/01/2021","averageCityConsumption": 25.3,"averageHighwayConsumption": 18.3}                                                    | Atualiza os dados do veículo informado pelo `{ID}`.            |
| http://[DOMAIN]:8080/vehicle/{ID}                   |  GET          |                                                    | Retorna os dados do veículo informado pelo `{ID}`            |
| http://[DOMAIN]:8080/vehicle/{ID}                   |  DELETE       |                                                    | Deleta o registro do veículo informado pelo `{ID}`            |
| http://[DOMAIN]:8080/spending-prevision             |  POST         |  {"gasPrice" : 10.5,	"highwayQuantity": 100.0,"cityQuantity" : 100}                                                  | Retorna uma lista de previsão de gastos de combustível ranqueada pelo valor total previsto.             |

## Instalação 

 - Necessário instalação do Java JDK_8 e Maven.

## Testes de integração

Para rodar os testes de integração, execute o comando abaixo em um prompt de comando no diretório da aplicação:

``mvn clean test``


### Compilação do fonte

Rode o comando Maven abaixo abrindo um prompt de comando no diretório da aplicação:

``mvn clean install``

Observação: Este comando irá compilar o código fonte da API e gerar o executável `jar` chamado `fleets-management-0.0.1-SNAPSHOT.jar` na pasta `target`. Caso o comando `mvn` não seja reconhecido pelo prompt de comando o `Maven` deve ser configurado nas variáveis de ambiente.

### Executar a API

Navegue até o diretório `target`, abra um prompt de comando e rode o comando `java -jar fleets-management-0.0.1-SNAPSHOT.jar` para iniciar a aplicação.


**Importante**
- A API roda na porta `8080`, portanto, certifique que nenhum outro serviço esteja rodando nesta porta antes de iniciar a aplicação.

PRONTO!! A aplicação já deverá estar de pé e rodando.

Para certificar que a API esteja funcionando corretamente abra um navegador de internet e navegue neste endereço `http://{DOMAIN]:8080` que deverá retornar `It's running.` indicando que a API esta rodando corretamente.


