import java.io.*;

public class Consumer extends Thread {
    private BufferQueue queue;
    private boolean run = true;
    private int maxValue = 0;
    private int n;
    File outFile;


    Consumer(BufferQueue queue, int n, File outFile) {
        this.queue = queue;
        this.n = n;
        this.outFile = outFile;
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
        outFile.delete();
        while (run) {
            if (queue.isEmpty()) {
                // System.out.println("watiting.....");
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
            }
            maxValue = value;
            FileWriter write = null;
            try {
                write = new FileWriter(outFile, true);
                if (value != 0) {
                    write.write(value + "\n");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            write.close();
            // queue.notifyFull();
        }
    }

    public void stopConsuming() throws InterruptedException {
        run = false;
        System.out.println("Consumer ended....");
        System.out.println("Max Prime Value: " + maxValue);
        queue.notifyEmpty();
    }
}
