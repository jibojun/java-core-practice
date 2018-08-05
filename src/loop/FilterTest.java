package loop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/5_5:07 PM
 */
public class FilterTest {
    public static void main(String[] args) {
        List<String> list=prepareList();
        Optional optional=list.stream().filter(s -> s.isEmpty()).findFirst();
        if(optional.isPresent()){
            System.out.println("found");
        }else{
            System.out.println("not found");
        }
    }

    private static List<String> prepareList(){
        List<String> list=new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("");
        list.add("test3");
        return list;
    }

}
