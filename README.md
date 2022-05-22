# PROVA PARA DESENVOLVEDOR BACKEND JAVA -ERP

Essa prova tem  como  objetivo  avaliar três  possíveis  níveis  de  conhecimento usando  a  mesma problemática, sendo cada nível mais complexo do que o outro
# Requisitos não funcionais para exucutar o projeto

 - Banco de dados PostgreSQL
 - Java 11
 - Maven
    
# A Prova

Requisitos da PROVA NIVEL II:

- Deverá ser desenvolvido um cadastro (Create/Read/Update/Delete/List) para as seguintes entidades: produto/serviço, pedido e itens de pedido
- Todos as entidades deverão ter um ID único do tipo UUID
- No cadastro de produto/serviço deverá ter uma indicação para diferenciar um produto de um serviço
- Deverá ser possível aplicar um percentual de desconto no pedido, porém apenas para os itens que sejam produto (não serviço); o desconto será sobre o valor total dos produtos
- Somente será possível aplicar desconto no pedido se ele estiver na situação Aberto (Fechado bloqueia)
- Não deve ser possível excluir um produto/serviço se ele estiver associado a algum pedido
- Não deve ser possível adicionar um produto desativado em um pedido

# Documentação minima

Para executar o projeto via maven utilize o comando : mvn clean install  spring-boot:run

Em todas as branchs das provas haverá ducumentação com swagger ui:

http://localhost:8080/swagger-ui/index.html#/
# Observações
- Como não a diagrama de classe, então tomei a liberdade de modelar as entidades ao meu gosto 
- não foi possível conectar o banco de dados aos testes;
