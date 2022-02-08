# Procedure Authorized Web App

Web App desenvolvido em Java usando JDK_8, Servlet, JSP, JPA, JSTL, Liquibase, Maven como controlador de dependências e H2 Database como banco de dados.
Este App é responsável pelo cadastro de procedimentos médicos e controle de authorizações destes procedimentos.

A aplicação roda na porta 8080 e pode ser acessada através deste endereço (após iniciada):

`http://[DOMAIN]:8080/procedure-authorized`

## Endereços:
| URL                                                 |   Finalidade                                        |
|-----------------------------------------------------|:----------------------------------------------------------------|
| http://[DOMAIN]:8080/procedure-authorized           | Home do APP, contém um menu superior para acesso as demais funcionalidades                  |
| http://[DOMAIN]:8080/procedure-authorized/procedure           | Lista os procedimentos cadastrados, onde é possível também cadastrar novos, alterar e excluir |        
| http://[DOMAIN]:8080/procedure-authorized/authorized           | Lista as authorizações cadastradas, onde é possível também cadastrar novas e excluir                 |

## Instalação 

 - Necessário instalação do Java JDK_8, Apache Tomcat 9 e Maven.


### Compilação do fonte

Rode o comando Maven abaixo abrindo um prompt de comando no diretório da aplicação:

``mvn clean install liquibase:update``

Observação: Este comando irá compilar o código fonte do Web APP e gerar o executável `war` chamado `procedure-authorized.war` na pasta `target`. 
Caso o comando `mvn` não seja reconhecido pelo prompt de comando o `Maven` deve ser configurado nas variáveis de ambiente.
O .war deve ser posteriormente deployado no servidor de aplicação tomcat

Também é possível rodar a aplicação diretamente pelo eclipse, adicionando o servidor apache Tomcat, compilando o projeto e fazendo deploy.


**Importante**
- O web app roda na porta `8080`, portanto, certifique que nenhum outro serviço esteja rodando nesta porta antes de iniciar a aplicação.

PRONTO!! A aplicação já deverá estar rodando.

