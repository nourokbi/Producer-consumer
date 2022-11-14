import java.util.LinkedList;
import java.util.Queue;

public class BufferQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private Object empty = new Object();
    private Object full = new Object();
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
        return queue.size() >= bufferSize;
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public void waitEmpty() throws InterruptedException {
        synchronized (empty) {
            empty.wait();
        }
    }

    public void notifyEmpty() throws InterruptedException {
        synchronized (empty) {
            empty.notify();
        }
    }

    public void waitFull() throws InterruptedException {
        synchronized (full) {
            full.wait();
        }
    }

    public void notifyFull() throws InterruptedException {
        synchronized (full) {
            full.notify();
        }
    }
}
