package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    private static AtomicInteger i=new AtomicInteger(0);
    private static ExecutorService tp=Executors.newCachedThreadPool();

    static class Thread1 implements Runnable{

        @Override
        public void run() {
            int count=0;
            while(count<100) {
                i.incrementAndGet();
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
        tp.submit(new VolatileTest.Thread1());
        tp.submit(new VolatileTest.Thread2());
    }

}
