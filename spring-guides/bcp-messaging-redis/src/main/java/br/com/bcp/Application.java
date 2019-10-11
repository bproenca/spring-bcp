package br.com.bcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/*
Register the listener and send a message

Spring Data Redis provides all the components you need to send and receive messages with Redis. Specifically, you need to configure:
- A connection factory
- A message listener container
- A Redis template
You’ll use the Redis template to send messages and you will register the Receiver with the message listener
container so that it will receive messages. The connection factory drives both the template and the message
listener container, enabling them to connect to the Redis server.

This example uses Spring Boot’s default RedisConnectionFactory, an instance of JedisConnectionFactory which is based on the Jedis Redis library. The connection factory is injected into both the message listener container and the Redis template.
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /*
    The main() method kicks everything off by creating a Spring application context. The application
    context then starts the message listener container, and the message listener container bean starts
    listening for messages. The main() method then retrieves the StringRedisTemplate bean from the
    application context and uses it to send a "Hello from Redis!" message on the "chat" topic.
    Finally, it closes the Spring application context and the application ends.
     */
    public static void main(String[] args) throws InterruptedException {
	ApplicationContext ctx = SpringApplication.run(Application.class, args);

	StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
	CountDownLatch latch = ctx.getBean(CountDownLatch.class);

	LOGGER.info("Sending message...");
	template.convertAndSend("chat", "Hello from Redis 123!");

	latch.await();

	System.exit(0);
    }

    /*
    The bean defined in the listenerAdapter method is registered as a message listener in the
    message listener container defined in container and will listen for messages on the "chat" topic.
    Because the Receiver class is a POJO, it needs to be wrapped in a message listener adapter that
    implements the MessageListener interface required by addMessageListener(). The message listener
    adapter is also configured to call the receiveMessage() method on Receiver when a message arrives.

    The connection factory and message listener container beans are all you need to listen for messages.
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
		    MessageListenerAdapter listenerAdapter) {

	RedisMessageListenerContainer container = new RedisMessageListenerContainer();
	container.setConnectionFactory(connectionFactory);
	container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

	return container;
    }

    @Bean
    CountDownLatch latch() {
	return new CountDownLatch(1);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
	return new MessageListenerAdapter(receiver, "receiveMessageBcp");
    }

    @Bean
    Receiver receiver(CountDownLatch latch) {
	return new Receiver(latch);
    }

    /*
    o send a message you also need a Redis template. Here, it is a bean configured as a StringRedisTemplate,
    an implementation of RedisTemplate that is focused on the common use of Redis where both keys and values are `String`s.
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
	return new StringRedisTemplate(connectionFactory);
    }
}