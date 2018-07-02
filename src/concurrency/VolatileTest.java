package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileTest {
    private static volatile int i=0;
    private static ExecutorService tp=Executors.newCachedThreadPool();

    static class Thread1 implements Runnable{

        @Override
        public void run() {
            int count=0;
            while(count<100) {
                i++;
                count++;
            }
        }
    }

    static class Thread2 implements Runnable{

        @Override
        public void run() {
            int count=0;
            while(count<100) {
                System.out.println("current value is:" + i);
                count++;
            }
        }
    }

    public static void main(String[] args) {
        tp.submit(new Thread1());
        tp.submit(new Thread2());
    }
}
