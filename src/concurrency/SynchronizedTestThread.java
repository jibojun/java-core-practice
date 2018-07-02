package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SynchronizedTestThread extends Thread {
    private Map<String, List<String>> resultMap;
    private String line;
    private SynchronizedTest instance;

    public SynchronizedTestThread(Map<String, List<String>> resultMap, String line){
        this.resultMap=resultMap;
        this.line=line;
        this.instance=instance;
    }

    @Override
    public void run() {
        synchronized (this) {
            String[] keyAndValue = line.trim().split("-");
            String key = keyAndValue[0];
            String value = keyAndValue[1];
            if (!resultMap.containsKey(key)) {
                List<String> list = new ArrayList<>();
                list.add(value);
                resultMap.put(key, list);
            } else {
                resultMap.get(key).add(value);
            }
        }
    }
}
