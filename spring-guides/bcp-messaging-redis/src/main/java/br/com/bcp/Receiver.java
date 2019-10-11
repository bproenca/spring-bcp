package br.com.bcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/*
In any messaging-based application, there are message publishers and messaging receivers. To create the message receiver.

The Receiver is a simple POJO that defines a method for receiving messages.
As you’ll see when you register the Receiver as a message listener,
you can name the message-handling method whatever you want.

For demonstration purposes, it is autowired by its constructor with a countdown latch.
That way, it can signal when it has received a message
 */
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
	this.latch = latch;
    }

    public void receiveMessageBcp(String message) {
	LOGGER.info("Received <" + message + ">");
	latch.countDown();
    }
}