package loop;

import java.util.ArrayList;
import java.util.List;

public class ForeachTest {
    private static List<Integer> l=new ArrayList<>();


    static{
        for(int i=0;i<100000;i++){
            l.add(i);
        }
    }

    public static void main(String[] args) {
        //demo usage
        System.out.println("list values are as below:");
        for(int element : l){
            element-=10;
            System.out.print(element+" ");
            System.out.println();
        }
    }
}
