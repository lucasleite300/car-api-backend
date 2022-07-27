# CAR-API

Aplicação (API REST) contendo 3 endpoint (_listCar_, _createCar_ e _logs_) consumindo uma api externa

- _API REST EXTERNA_ : http://api-test.bhut.com.br:3000

## Regras de negócio da api

 - api/listCar: 
   - Deve retornar na api implementada os dados da api externa;
 - api/createCar: 
   - Deve criar um registro na api externa usando a api implementada;
   - Crie uma tabela em banco nosql para armazenar os log com os campos mínimos de: id,
   data_hora e car_id;
   - Salvar na tabela log as chamadas efetuadas da api;
   - Postar a informação do carro criado para uma fila;
   - Consumir esse registro da fila e enviar um webhook avisando que um novo carro foi
   cadastrado;
 - api/logs:
   - Deve consultar todos os registros salvos na tabela log;

## Tecnologias 

- JAVA
- Spring Boot
- MongoDB
- Swagger
- WebHook(Implementada na propria API)

## Como rodar a API

 - Rodar um banco MongoDB (localmente ou com o auxílio do docker) contendo os seguintes dados:
    + username = adm
    + password = adm123
    + port = 27017
    + database = teste
    + collection = logs
 - Rodar o _CarApplication_ com auxilio da IDE ou pelo terminal com o comando "mvn spring-boot:run";
 - Para acessar os endpoints é necessário do auxílio do Postman, algum framework semelhante ou apenas abrir o link do swagger que está configurado na aplicação (http://localhost:8080/swagger-ui.html).
 - A rota POST (_api/createCar_) segue as mesmas regras aplicadas na api externa, e com isso deve-se tomar cuidado no envio do JSON que segue o mesmo padrão do exemplo no arquivo do projeto "_exemplo_json_POST.txt_".  

## Créditos

- [Lucas Lima Leite](https://github.com/lucasleite300)
