public class Main {
    public static void main(String[] args) throws InterruptedException {
        BufferQueue queue = new BufferQueue(5);

        Producer p = new Producer(queue, 500);
        Consumer c = new Consumer(queue);
        long start = System.currentTimeMillis();
        p.start();
        c.start();
        p.join();
        c.join();
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }
}