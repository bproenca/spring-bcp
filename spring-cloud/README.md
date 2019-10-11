## Microservices with Spring Cloud ##

### Definition:

_"Small autonomous services that work together"_ - Sam Newman

"In short, the microservice architectural style is an approach to developing a single application as a suite of small services, each running in its own process and communication with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery. There is a bare minimum of centralized management of these services, which may be written in different programming languages and use different data storage technologies."
James Lewis and Martin Fowler

**Microservices characteristics:**

* REST
* Small well chosen deployable units
* Very well thought out boundaries
* Cloud enabled
* I'd be able to have multiple instances of each micro services (auto-scaling)

**Spring Cloud**

Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, circuit breakers, intelligent routing, micro-proxy, control bus, one-time tokens, global locks, leadership election, distributed sessions, cluster state). Coordination of distributed systems leads to boiler plate patterns, and using Spring Cloud developers can quickly stand up services and applications that implement those patterns. They will work well in any distributed environment, including the developer’s own laptop, bare metal data centres, and managed platforms such as Cloud Foundry.

**Configuration Management**

* Spring Cloud Config Server

**Auto-scaling**

* Naming Server (Eureka)
* Service registration + Service discovery
* Ribbon Load Balancing (client side load balancing)
* Distribute the load among all services (registered in the naming server)
* Feign (Easier REST Clients)

**Visibility**

* Zipkin Distributed Tracing
* Use Spring Cloud Sloat to assign ID to request across multiple components
* Zipkin distributed tracing to trace a request across multiple components 
* Netflix API Gateway
* Provide great solution for common features (e.g. logging, security, analytics) 
* Hystrix
* Fault tolerance
* If a service is down, hystrix help us with a default response


**Microservices advantages**

* New technology and process adoption (each service can be developed in a different "stack")
* Dynamic scaling
* Faster release cycles (easy to release a "micro" service)
