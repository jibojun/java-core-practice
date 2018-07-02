package concurrency;

import java.util.concurrent.atomic.AtomicReference;

public class ReentrantSpinLock {
    //持有资源的线程
    private AtomicReference owner =new AtomicReference<>();

    //记录进重次数
    private int rentrantCount=0;

    //锁
    public void lock(){
        Thread current = Thread.currentThread();
        if(current==owner.get()){
            rentrantCount++;
            System.out.println(current.getName() + "'s reenter count is: "+rentrantCount+" now");
            return;
        }
        //循环自旋，不释放CPU资源，直到获取锁为止
        while(!owner .compareAndSet(null, current)){
            System.out.println(current.getName()+ " is waiting for lock release");
        }
    }

    //解锁
    public void unlock (){
        Thread current = Thread.currentThread();
        if(current==owner.get()){
            if(rentrantCount!=0) {
                rentrantCount--;
                System.out.println(current.getName() + "'s reenter count is: "+rentrantCount+" now");
            }else {
                //CAS,预期值;当前线程，目标值:null,放锁
                owner.compareAndSet(current, null);
                System.out.println(current.getName() + "'s lock is released");
            }
        }

    }
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                ReentrantSpinLock l=new ReentrantSpinLock();
                try {
                    System.out.println("lock 1 time");
                    l.lock();
                    System.out.println("lock 2 times");
                    l.lock();
                }catch(Exception e){
                    System.out.println(e.toString());
                }finally {
                    System.out.println("unlock 1 time");
                    l.unlock();
                    System.out.println("unlock 2 times");
                    l.unlock();
                }
            }
        }).start();
    }
}
