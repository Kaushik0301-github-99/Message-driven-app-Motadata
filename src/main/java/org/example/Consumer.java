package org.example;

import java.util.Random;

public class Consumer implements Runnable{
    private final MessageQueue queue;
    private int successCount = 0;
    private int errorCount = 0;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            try {
                Message message = queue.consume();
                System.out.println("Consumed: " + message.getContent());
                if (random.nextBoolean()) {
                    successCount++;
                } else {
                    throw new RuntimeException("Error processing message: " + message.getContent());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                errorCount++;
            }
        }
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getErrorCount() {
        return errorCount;
    }
    protected void incrementErrorCount() {
        errorCount++;
    }
}
