import java.util.LinkedList;
// import java.util.Objects;
import java.util.Queue;

public class BufferQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private Object produce = new Object();
    private Object consume = new Object();
    private int bufferSize;

    BufferQueue(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void add(int n) {
        synchronized (queue) {
            queue.add(n);
        }
    }

    public int remove() {
        synchronized (queue) {
            return queue.remove();
        }
    }

    public boolean isFull() {
        if (queue.size() > bufferSize) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (queue.size() == 0) {
            return true;
        }
        return false;
    }

    public void produceWait() throws InterruptedException {
        synchronized (produce) {
            produce.wait();
        }
    }

    public void produceNotifyAll() throws InterruptedException {
        synchronized (produce) {
            produce.notifyAll();
        }
    }

    public void produceNotify() throws InterruptedException {
        synchronized (produce) {
            produce.notify();
        }
    }

    public void concumeWait() throws InterruptedException {
        synchronized (consume) {
            consume.wait();
        }
    }

    public void concumeNotifyAll() throws InterruptedException {
        synchronized (consume) {
            consume.notifyAll();
        }
    }

    public void concumeNotify() throws InterruptedException {
        synchronized (consume) {
            consume.notify();
        }
    }

}
