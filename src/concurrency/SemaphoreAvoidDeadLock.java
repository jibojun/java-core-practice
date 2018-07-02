package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreAvoidDeadLock {
    private static ExecutorService tp=Executors.newCachedThreadPool();
    private static Semaphore semaphore1=new Semaphore(1);
    private static Semaphore semaphore2=new Semaphore(1);

    static class Thread1 implements Runnable{

        @Override
        public void run() {
            try {
                while (true) {
                    if (semaphore1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName()+":Lock 1 locked");
                        if(semaphore2.tryAcquire(1, TimeUnit.SECONDS)){
                            System.out.println(Thread.currentThread().getName()+":lock 2 locked");
                        }else{
                            System.out.println(Thread.currentThread().getName()+":lock 2 failed to lock");
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName()+":lock 1 failed to lock");
                    }
                    semaphore1.release();
                    semaphore2.release();
                    Thread.sleep(1000);
                }
            } catch (Exception e){

            }
        }
    }

    static class Thread2 implements Runnable{

        @Override
        public void run() {
            try {
                while (true) {
                    if (semaphore2.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName()+":lock 2 locked");
                        if(semaphore1.tryAcquire(1, TimeUnit.SECONDS)){
                            System.out.println(Thread.currentThread().getName()+":lock 1 locked");
                        }else{
                            System.out.println(Thread.currentThread().getName()+":lock 2 failed to lock");
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName()+":lock 1 failed to lock");
                    }
                    semaphore1.release();
                    semaphore2.release();
                    Thread.sleep(1000);
                }
            } catch (Exception e){

            }
        }
    }

    public static void main(String[] args) {
        tp.submit(new Thread1());
        tp.submit(new Thread2());
    }
}
