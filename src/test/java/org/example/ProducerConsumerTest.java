package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProducerConsumerTest {
    private MessageQueue queue;
    private Producer producer;
    private Consumer consumer;

    @BeforeEach
    public void setUp() {
        queue = new MessageQueue();
        producer = new Producer(queue);
        consumer = new Consumer(queue);
    }

    @Test
    public void testSuccessfulProcessing() throws InterruptedException {
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        assertEquals(10, consumer.getSuccessCount() + consumer.getErrorCount());
        assertTrue(consumer.getSuccessCount() > 0);
        assertTrue(consumer.getErrorCount() >= 0);
    }

    @Test
    public void testFailureProcessing() throws InterruptedException {
        // Force errors by overriding random behavior in consumer
        Consumer failingConsumer = new Consumer(queue) {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Message message = queue.consume();
                        System.out.println("Consumed: " + message.getContent());
                        throw new RuntimeException("Error processing message: " + message.getContent());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        incrementErrorCount();
                    }
                }
            }
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(failingConsumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        assertEquals(0, failingConsumer.getSuccessCount());
        assertEquals(10, failingConsumer.getErrorCount());
    }
}
