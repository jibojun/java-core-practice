package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolAwaitTerminationTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService tp=Executors.newFixedThreadPool(5);
        long start=System.currentTimeMillis();
        for(int i=0;i<15;i++){
            tp.submit(new Thread(() -> System.out.println("a new thread is running")));
        }
        tp.shutdown();
        if(tp.awaitTermination(1,TimeUnit.SECONDS)){
            System.out.println("thread pool is closed");
        }else{
            System.out.println("thread pool hasn't been closed yet");
        }
        long end=System.currentTimeMillis();
        System.out.println("execution time is:"+(end-start)+"ms");
    }
}
