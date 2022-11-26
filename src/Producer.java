public class Producer extends Thread {
    private int n;
    private BufferQueue queue;
    private boolean running = true;
    private int counterOfPrimes = 0;

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
        }
        while (running) {
            if (queue.isFull()) {
                queue.waitFull();
            }
            if (!running) {
                break;
            }
            for (int i = 2; i <= n; i++) {
                if (isPrime(i)) {
                    queue.add(i);
                    counterOfPrimes++;
                    queue.notifyEmpty();
                    Thread.sleep(5);
                }
                if (i == n) {
                    queue.notifyEmpty();
                    // System.out.println("Counter of Prime numbers: "+getPrimeCount());
                    stopProducing();
                }
            }
        }
    }

    public void stopProducing() throws InterruptedException {
        running = false;
        System.out.println("Production ended....");
        queue.notifyFull();
    }

    public int getPrimeCount() {
        return counterOfPrimes;
    }

    public boolean isPrime(int number) {
        if (number <= 1)
            return false;

        for (int i = 2; i <= number / 2; i++) {
            if ((number % i) == 0)
                return false;
        }
        return true;
    }
}
