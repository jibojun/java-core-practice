package loop;


import java.util.*;

public class IteratorDemo {
    private static List<String> l=new LinkedList();
    private static Map<Integer,String> m=new LinkedHashMap();
    private static Set<String> s=new TreeSet();


    static{
        l.add("lv1");
        l.add("lv2");
        l.add("lv3");

        m.put(1,"name");
        m.put(2,"address");
        m.put(3,"number");

        s.add("sv1");
        s.add("sv2");
        s.add("sv3");
    }

    public static void main(String[] args) {
        Iterator i1=l.iterator();
        System.out.println("list values are as below:");
        while(i1.hasNext()) {
            System.out.print(i1.next() + " ");
            System.out.println();
        }

        Iterator i2=m.entrySet().iterator();
        System.out.println("map keys,values are as below:");
        while(i2.hasNext()) {
            Map.Entry e=(Map.Entry)i2.next();
            Object key=e.getKey();
            Object value=e.getValue();
            System.out.print("{"+key + ","+value+"}");
            System.out.println();
        }

        Iterator i3=s.iterator();
        System.out.println("set values are as below:");
        while(i3.hasNext()) {
            System.out.print(i3.next() + " ");
            System.out.println();
        }

    }
}
