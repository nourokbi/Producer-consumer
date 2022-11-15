// import java.util.Queue;

public class Producer extends Thread {
    private int n;
    private BufferQueue queue;
    private boolean run = true;

    Producer(BufferQueue queue, int n) {
        this.n = n;
        this.queue = queue;
    };

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void produce() throws InterruptedException {
        if (n < 2) {
            System.out.println("No prime numbers found!");
            stopProducing();
            queue.notifyEmpty();
            return;
        }
        while (run) {
            // if (queue.isFull()) {
            //     queue.waitFull();
            // }
            if (!run) {
                break;
            }
            for (int i = 2; i <= n; i++) {
                if (isPrime(i)) {
                    queue.add(i);
                    queue.notifyEmpty();
                    Thread.sleep(4);
                }
                if (i == n) {
                    queue.notifyEmpty();
                    stopProducing();
                }
            }
        }
    }

    public void stopProducing() throws InterruptedException {
        run = false;
        System.out.println("Production ended....");
        // queue.notifyFull();
    }

    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= number / 2; i++) {
            if ((number % i) == 0)
                return false;
        }
        return true;
    }
}
