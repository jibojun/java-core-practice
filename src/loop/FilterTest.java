package loop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/5_5:07 PM
 */
public class FilterTest {
    public static void main(String[] args) {
        List<String> list = prepareList();
        Optional optional1 = list.stream().filter(s -> s.isEmpty()).findFirst();
        if (optional1.isPresent()) {
            System.out.println("found empty string");
        } else {
            System.out.println("not found empty string");
        }
        List<String> resultList = list.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println(resultList);
        list.stream().filter(s -> !s.isEmpty()).forEach(s -> System.out.println(s));

    }

    private static List<String> prepareList() {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("");
        list.add("test3");
        return list;
    }

}
