import java.io.*;

public class Consumer extends Thread {
    private BufferQueue queue;
    private boolean running = true;
    private int maxValue = 0;
    private int maxPrimeValue;
    private int n;
    File outputFile;

    Consumer(BufferQueue queue, int n, File outputFile) {
        this.queue = queue;
        this.n = n;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consume() throws InterruptedException, IOException {
        if (n < 2) {
            stopConsuming();
        }
        outputFile.delete();
        FileWriter writer = new FileWriter(outputFile, true);
        while (running) {
            // Thread.sleep(5);
            if (queue.isEmpty()) {
                queue.waitEmpty();
            }
            if (!running) {
                break;
            }
            int currentValue = 0;
            if (!queue.isEmpty()) {
                currentValue = queue.remove();
            }
            if (currentValue <= maxValue) {
                maxPrimeValue = maxValue;
                stopConsuming();
            }
            maxValue = currentValue;
            if (currentValue != 0) {
                writer.write(currentValue + "\n");
            }
            queue.notifyFull();
        }
        writer.close();
    }

    public int getMaxValue() {
        return maxPrimeValue;
    }

    public void stopConsuming() throws InterruptedException {
        running = false;
        System.out.println("Consumer ended....");
        // System.out.println("Max Prime Value: " + maxValue);
        queue.notifyEmpty();
    }
}
