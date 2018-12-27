/**
 * @author: diyaguang
 * @date: 2018/12/26 9:52 AM
 * @description: PACKAGE_NAME
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @package: PACKAGE_NAME
 * @author: diyaguang
 * @date: 2018/12/26 - 9:52 AM
 * @description:
 */
public class ReentrantReadWriteLockDemo {
    public static void test(){
        final Count3 ct = new Count3();
        for(int i=0;i<2;i++){
            new Thread(){
                public void run(){
                    ct.get();
                }
            }.start();
        }
        for(int i=0;i<2;i++){
            new Thread(){
                public void run(){
                    ct.put();
                }
            }.start();
        }
    }
}

class Count3{
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    public void get(){
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" read start.");
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName()+" read end.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }
    }
    public void put(){
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" write start.");
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName()+" wirte end.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }
    }
}
class Count4{
    private final Map<String,Object> map = new HashMap<String,Object>();
    private final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    public Object readWrite(String id){
        Object value = null;
        rwlock.readLock().lock();
        try {
            value = map.get(id);
            if(value==null){
                rwlock.readLock().unlock();
                rwlock.writeLock().lock();
                try{
                    if(value==null){
                        value="aaa";
                    }
                }finally{
                    rwlock.writeLock().unlock();
                }
                rwlock.readLock().lock();
            }
        }finally{
            rwlock.readLock().unlock();
        }
        return value;
    }
}
