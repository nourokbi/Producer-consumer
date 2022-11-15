import java.io.File;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BufferQueue queue = new BufferQueue(1);
        int n = 30000;
        File myFile = new File("Hello.txt");

        Producer p = new Producer(queue, n);
        Consumer c = new Consumer(queue, n, myFile);
        long start = System.currentTimeMillis();
        p.start();
        c.start();
        p.join();
        c.join();
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }
}