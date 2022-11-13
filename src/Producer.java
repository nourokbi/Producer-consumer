// import java.util.Queue;

public class Producer extends Thread {
    int n;
    BufferQueue queue;

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
        for (int i = 5; i < n; i += 2) {
            if (isPrime(i)) {
                while (queue.isFull()) {
                    queue.produceWait();
                }
                queue.add(i);
                queue.concumeNotifyAll();
            }
        }
    }

    public void stopProducing() {
        try {
            queue.produceNotifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
