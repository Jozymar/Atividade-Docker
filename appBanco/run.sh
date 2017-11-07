docker build -t jozimar/banco ./postgres
docker run -p 5433:5432 -d --name banco jozimar/banco

mvn clean package
docker build -t jozimar/aula-2 .
docker run -p 8081:8080 -d --name appBanco --link banco:host-banco jozimar/aula-2
