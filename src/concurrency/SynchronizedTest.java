package concurrency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedTest {

    public void putResultToMap(String data,Map<String,List<String>> resultMap){
        ExecutorService threadPool=Executors.newFixedThreadPool(3);
        String[] lines=data.trim().split(",");
        for(String line:lines){
            SynchronizedTestThread thread=new SynchronizedTestThread(resultMap,line);
            threadPool.submit(thread);
        }
        try {
            threadPool.shutdown();
            threadPool.awaitTermination(Long.MAX_VALUE,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<100000;i++){
            sb.append("1-test"+i+",");
        }
        for(int i=0;i<100000;i++){
            sb.append("2-test"+i+",");
        }
        for(int i=0;i<100000;i++){
            sb.append("3-test"+i+",");
        }
        sb.deleteCharAt(sb.length()-1);

        Map<String, List<String>> resultMap=new HashMap<>();
        String data=String.valueOf(sb);

        new SynchronizedTest().putResultToMap(data,resultMap);

        for(Map.Entry<String,List<String>> entry:resultMap.entrySet()){
            System.out.println(entry.getKey()+"'s count:"+entry.getValue().size());
        }
    }
}
