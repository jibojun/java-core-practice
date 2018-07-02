package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhamtomReferenceTest {
    public static void main(String[] args) {
        TestObject obj=new TestObject(3,"test3");
        ReferenceQueue rq=new ReferenceQueue();
        PhantomReference pr=new PhantomReference(obj,rq);
        //always null
        System.out.println(pr.get());
        System.gc();
        //if object is ready to be collected, reference will be added to reference queue, can use this method to determine whether this object is collected
        System.out.println(pr.isEnqueued());
    }
}
