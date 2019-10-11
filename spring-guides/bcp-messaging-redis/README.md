## Messaging with Redis
This guide walks you through the process of using Spring Data Redis to publish and subscribe to messages sent via Redis.

[Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)


Redis is an open source, BSD-licensed, key-value data store that also comes with a messaging system.
The server is freely available at http://redis.io/download

Start:
```
src/redis-server
´´´

You can interact with Redis using the built-in client:
```
$ src/redis-cli
redis> set foo bar
OK
redis> get foo
"bar"
```
