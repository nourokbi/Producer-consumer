// import java.util.LinkedList;
// import java.util.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BufferQueue queue = new BufferQueue(10);

        Producer p = new Producer(queue, 4000);
        Consumer c = new Consumer(queue);
        long start = System.currentTimeMillis();
        p.start();
        c.start();
        
        p.join();
        c.join();
        
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
        c.stopConsuming();
    }
}