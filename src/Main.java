import java.io.File;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BufferQueue queue = new BufferQueue(15);
        int n = 100000;
        File myFile = new File("Hello.txt");

        Producer p = new Producer(queue, n);
        Consumer c = new Consumer(queue, n, myFile);
        long start = System.currentTimeMillis();
        p.start();
        c.start();
        p.join();
        c.join();
        long end = System.currentTimeMillis();
        System.out.println("Time Elapsed: " + (end - start) + "ms");
        System.out.println("Counter of Prime numbers: " + p.getPrimeCount());
        System.out.println("Max Prime Value: " + c.getMaxValue());
    }
}