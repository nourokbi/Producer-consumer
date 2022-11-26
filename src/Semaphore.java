class Semaphore {
    protected int value = 0;

    protected Semaphore() {
        value = 0;
    }

    protected Semaphore(int initial) {
        value = initial;
    }

    public synchronized void Wait() {
        value--;
        if (value < 0)
            try { wait(); } catch (InterruptedException e) { }
    }

    public synchronized void Notify() {
        value++;
        if (value <= 0) notify();
    }
}
