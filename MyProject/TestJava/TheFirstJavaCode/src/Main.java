import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        //Main main = new Main();
        //main.testMapView();
        //BitSetTest.testBitSet();

        //UnsynchBankTest.test();

        //ThreadMain.test();
        //TeceptionHandlerMain.test();
        //SynchronizedMain.test();
        //ReentrantLockDemo.test();
        ReentrantReadWriteLockDemo.test();
    }

    HashMap<String,String> staffMap;

    {
        staffMap = new HashMap<>();
    }

    public void testMapView(){
        staffMap.put("diyg","1");
        staffMap.put("dygstudio","2");
        staffMap.put("diyaguang","3");

        Set<String> nameKey = staffMap.keySet();

        System.out.println("Source :");
        for (Map.Entry<String,String> entry : staffMap.entrySet()) {
            System.out.println("key="+entry.getKey()+" and value="+entry.getValue());
        }
        System.out.println("keySet :");
        nameKey.forEach(name-> System.out.println(name));

        nameKey.remove("diyg");

        System.out.println("removed Source :");
        for (Map.Entry<String,String> entry : staffMap.entrySet()) {
            System.out.println("key="+entry.getKey()+" and value="+entry.getValue());
        }

        System.out.println("removed keySet :");
        nameKey.forEach(name-> System.out.println(name));

        /*
        for(String key:staffMap.keySet()){
            System.out.println("key="+key+" and value="+staffMap.get(key));
        }

        Iterator<Map.Entry<String,String>> it =staffMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry = it.next();
            System.out.println("key="+entry.getKey()+" and value="+entry.getValue());
        }

        for(String v : staffMap.values()){
            System.out.println("value="+v);
        }
         */
    }
}
