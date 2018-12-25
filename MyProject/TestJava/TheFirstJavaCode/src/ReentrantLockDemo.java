import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockDemo
 * @Description TODO
 * @Author newsu
 * @Date 2018/12/25 16:28
 * @PACKAGE_NAME PACKAGE_NAME
 **/

public class ReentrantLockDemo {
    public static void test(){
        final Count2 ct = new Count2();
        for(int i=0;i<2;i++){
            new Thread(() -> ct.get()).start();
        }
        for(int i=0;i<2;i++){
            new Thread(()->ct.put()).start();
        }
    }
}

class Count2{
    final ReentrantLock lock = new ReentrantLock();
    public void get(){
        //final ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"get begin");
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName()+"get end");
            lock.unlock();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void put(){
        //final ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"put begin");
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName()+"put end");
            lock.unlock();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
