package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftReferenceTest {
    public static void main(String[] args) {
        TestObject obj=new TestObject(1, "test1");
        ReferenceQueue rq=new ReferenceQueue();
        SoftReference sr=new SoftReference(obj,rq);
        System.out.println(sr.get());
        System.gc();
        //if object is ready to be collected, reference will be added to reference queue, can use this method to determine whether this object is collected
        System.out.println(sr.isEnqueued());
    }
}
