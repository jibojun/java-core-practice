package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    public static void main(String[] args) {
        ExecutorService threadPool=Executors.newCachedThreadPool();
        CyclicBarrier barrier=new CyclicBarrier(6);
        long start=System.currentTimeMillis();
        for(int i=0;i<5;i++){
            threadPool.submit(new Thread(() -> {
                System.out.println("thread reached barrier");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("thread recovered");
            }));
        }
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        System.out.println("execution time is:"+(end-start)+"ms");
    }
}
