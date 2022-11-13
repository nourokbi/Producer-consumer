import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Consumer extends Thread {
    private BufferQueue queue;
    private volatile boolean run;
    private int value;

    Consumer(BufferQueue queue) {
        this.queue = queue;
        run = true;
    }

    @Override
    public void run() {
        consume();
    }

    public void consume() {
        File myFile = new File("test.txt");
        myFile.delete();
        while (run) {
            if (queue.isEmpty()) {
                try {
                    queue.concumeWait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!run) {
                break;
            }

            value = queue.remove();
            try {
                FileWriter write = new FileWriter(myFile, true);
                write.write(value + "\n");
                write.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            try {
                queue.produceNotifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopConsuming() throws InterruptedException {
        run = false;
        queue.concumeNotifyAll();
    }
}
