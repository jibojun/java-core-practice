package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        //fair
        Semaphore semaphore=new Semaphore(3,true);
        ExecutorService tp=Executors.newFixedThreadPool(50);

        for(int i=0;i<100;i++){
            tp.submit(new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"got permit");
                    System.out.println("current available permits number is:"+semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"released permit");
                    System.out.println("current available permits number is:"+semaphore.availablePermits());
                }
            }));
        }
    }
}
