import java.io.File;

import javax.swing.JLabel;

public class Runner {
    private int N;
    private int bufferSize;
    private String fileName;

    JLabel largestPrime;
    JLabel numberOfPrimes;
    JLabel timeElapsed;

    Runner(int n, int bufferSize, String fileName, JLabel largestPrime, JLabel numberOfPrimes, JLabel timeElapsed) {
        this.N = n;
        this.bufferSize = bufferSize;
        this.fileName = fileName;

        this.largestPrime = largestPrime;
        this.numberOfPrimes = numberOfPrimes;
        this.timeElapsed = timeElapsed;
    }

    void run() throws InterruptedException {
        BufferQueue queue = new BufferQueue(bufferSize);
        File myFile = new File(fileName);

        Producer producer = new Producer(queue, N);
        Consumer consumer = new Consumer(queue, N, myFile);
        System.out.println("--------------------------------");
        System.out.println("--------Proccess Started--------\n");
        long start = System.currentTimeMillis();
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        long end = System.currentTimeMillis();
        // System.out.println("Max Prime Value: " + consumer.getMaxValue());
        largestPrime.setText("" + consumer.getMaxValue());
        // System.out.println("Counter of Prime numbers: " + producer.getPrimeCount());
        numberOfPrimes.setText("" + producer.getPrimeCount());
        // System.out.println("Time Elapsed: " + (end - start) + "ms");
        timeElapsed.setText((end - start) + " ms");
        System.out.println("\n--------Proccess Ended----------");
    }
}
