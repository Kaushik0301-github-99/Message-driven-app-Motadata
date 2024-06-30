package org.example;

import java.util.Random;

public class Producer implements Runnable{
    private final MessageQueue queue;

    public Producer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Message message = new Message("Message " + i);
            queue.produce(message);
            System.out.println("Produced: " + message.getContent());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
