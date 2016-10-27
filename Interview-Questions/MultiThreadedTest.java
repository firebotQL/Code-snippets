import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedTest {

    public static Integer counter = new Integer(0);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 1000; i++) {
            Runnable worker = new WorkerThread();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("counter = " + counter);
    }

    public static void increment() {
        synchronized (counter) {
            counter++;
        }
    }

    public static class WorkerThread implements Runnable {
        @Override
        public void run() {
            MultiThreadedTest.increment();
        }
    }
}
