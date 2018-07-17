package generic;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2017/1/18_12:37 AM
 */
public class GenericTest {
    public static void main(String[] args) {
        System.out.println(getResult(1).getResponse().getClass().getName());
        System.out.println(getResult(2).getResponse().getClass().getName());
        System.out.println(getResult(100).getResponse().getClass().getName());
    }

    private static Result getResult(int i){
        switch (i){
            case 1:
                return new Result(1,"TEST");
            case 2:
                return new Result(2,new ArrayList());
            default:
                return new Result(3,new HashSet());
        }
    }
}
