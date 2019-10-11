## Testind Java Spring Boot Application running on Docker

Build image:  
`mvn install dockerfile:build`

Push (first login to DockerHub):  
`mvn dockerfile:push`

Run:  
`docker run -p 8080:8080 -t bproenca/bcp-spring-boot-docker`

DockerHub:  
https://cloud.docker.com/repository/docker/bproenca/bcp-spring-boot-docker

### Deploy the stack

`docker swarm init --advertise-addr=<ip>`  
`docker stack deploy -c docker-compose.yml my-sprint-boot-docker-app`  
`docker stack ps my-sprint-boot-docker-app`  
