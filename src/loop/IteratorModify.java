package loop;

import java.util.*;

public class IteratorModify {
    private static List<String> l=new LinkedList();


    static{
        for(int i=0;i<100000;i++){
            l.add("lv"+i);
        }
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator i1=l.iterator();
                System.out.println("list values are as below:");
                while(i1.hasNext()) {
                    String tmp=(String) i1.next();
                    tmp+=" modify";
                    System.out.print(tmp + " ");
                    System.out.println();
                }
            }
        }).start();
    }
}
