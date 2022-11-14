import java.io.*;

public class Consumer extends Thread {
    private BufferQueue queue;
    private boolean run = true;
    private int maxValue;

    Consumer(BufferQueue queue) {
        this.queue = queue;
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
        File myFile = new File("test.txt");
        myFile.delete();
        while (run) {
            if (queue.isEmpty()) {
                queue.waitEmpty();
            }
            if (!run) {
                break;
            }
            int value = 0;
            if (!queue.isEmpty()){
                value = queue.remove();
            }
            if (value <= maxValue) {
                stopConsuming();
                break;
            }
            maxValue = value;
            FileWriter write = null;
            try {
                write = new FileWriter(myFile, true);
                write.write(value + "\n");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            write.close();
            queue.notifyFull();
        }
    }

    public void stopConsuming() throws InterruptedException {
        run = false;
        System.out.println("Consumer ended....");
        System.out.println("Max Prime Value: " + maxValue);
        queue.notifyEmpty();
    }
}
