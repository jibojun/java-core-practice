package generic;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2017/7/18_1:07 AM
 */
public class GenericReflectTest {
    public static void main(String[] args) {
        Set<Long> set=new HashSet();
        set.add(1L);
        set.add(2L);
        //error when compile
//        set.add("ccc");
        try {
            //reflect, get method and add string
            Class cls = Class.forName("java.util.HashSet");
            Method method=cls.getMethod("add",Object.class);
            method.invoke(set,"CCC");
            System.out.println(set);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
