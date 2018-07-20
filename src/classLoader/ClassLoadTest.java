package classLoader;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2017/7/21_2:30 AM
 */
public class ClassLoadTest {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("loadClass method start");
        Class cls1=TestClass1.class.getClassLoader().loadClass("classLoader.TestClass1");
        System.out.println("forName method start");
        Class cls2=Class.forName("classLoader.TestClass1");
    }
}
