package concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch cdl=new CountDownLatch(2);
        new Thread(() -> {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 1 finished");
        }).start();
        new Thread(() -> {
            System.out.println("thread 2 counted down");
            cdl.countDown();
        }).start();
        new Thread(() -> {
            System.out.println("current count is:"+cdl.getCount());
            System.out.println("thread 3 counted down");
            cdl.countDown();
        }).start();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread finished");
    }
}
