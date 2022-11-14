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
        if (n > 3) {
            queue.add(2);
            queue.add(3);
        }
        while (run) {
            if (queue.isFull()) {
                queue.waitFull();
            }
            if (!run) {
                break;
            }
            for (int i = 5; i < n; i += 2) {
                if (isPrime(i)) {
                    queue.add(i);
                    queue.notifyEmpty();
                    Thread.sleep(1);
                }
                if (i == n - 1) {
                    System.out.println("Production ended....");
                    queue.notifyEmpty();
                    stopProducing();
                }
            }
        }
    }

    public void stopProducing() throws InterruptedException {
        run = false;
        queue.notifyFull();
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
