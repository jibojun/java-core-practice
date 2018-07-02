package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) {
        TestObject obj=new TestObject(2,"test2");
        ReferenceQueue rq=new ReferenceQueue();
        WeakReference wr=new WeakReference(obj,rq);
        System.out.println(wr.get());
        System.gc();
        //if object is ready to be collected, reference will be added to reference queue, can use this method to determine whether this object is collected
        System.out.println(wr.isEnqueued());
    }
}
