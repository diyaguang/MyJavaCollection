import com.dygstudio.testthread.thread.*;
import sun.util.resources.th.CalendarData_th;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/14-16:46
 * @Description:
 */
public class Main {
    public static void main(String[] args){

        try {
            //testThreadA();
            //testThreadB();
            //testThreadC();
            //testThreadInfo();
            //testThreadLocal();
            //testThreadException();
            //testCountThread();
            //testReentrantCount();
            //CountDownLatchTest.test();
            //SemaphoreTest.test();
            //CyclicBarrierTest.test();
            //NewSinglethreadExecutorTest.test();
            //FutureTaskTest.test();
            //ForkJoinTaskTest.test();
            //ForkJoinTaskTest2.test();
            //CountDownLatchTest2.test();
            //PhaserTest.test();
            //ExchangerTest.test();
            ConditionTest.test();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void testThreadA(){
        ThreadA threadA = new ThreadA();
        threadA.start();

    }
    public static void testThreadB(){
        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();
    }

    //When executing, the object of FutureTask<T>  is used to obtain the result of thread execution.
    public static void testThreadC(){
        ThreadC threadC = new ThreadC();
        FutureTask<String> faeature = new FutureTask<String>(threadC);
        new Thread(faeature).start();
        System.out.println("The method main thread begin: ");
        try {
            System.out.println("The thread result is :"+faeature.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("The method main thread end. ");
    }

    public static void testThreadInfo(){
        ThreadInfo threadInfo = new ThreadInfo();
        for(int i=0;i<5;i++){
            new Thread(threadInfo,"Thread name:("+i+")").start();
        }
        Thread threadMain = Thread.currentThread();
        System.out.println("This main thread：");
        System.out.println("return Main thread ["+threadMain.getName()+"] of the thread group at active count:"+Thread.activeCount());
        System.out.println("Return Main thread id:"+threadMain.getId());
        System.out.println("Return Main thread priority:"+threadMain.getPriority());
        System.out.println("Return Main thread state:"+threadMain.getState());
        System.out.println("Return Main thread is alive:"+threadMain.isAlive());
        System.out.println("Return Main thread is daemon:"+threadMain.isDaemon());
        System.out.println("Return Main thread of group name:"+threadMain.getThreadGroup());
    }

    public static void testThreadLocal(){
        ThreadLocalTest sn = new ThreadLocalTest();
        ThreadLocalTest.TestClient t1 = new ThreadLocalTest.TestClient(sn);
        ThreadLocalTest.TestClient t2 = new ThreadLocalTest.TestClient(sn);
        ThreadLocalTest.TestClient t3 = new ThreadLocalTest.TestClient(sn);

        t1.start();
        t2.start();
        t3.start();
    }

    public static void testThreadException(){
        ThreadExceptionTest test = new ThreadExceptionTest();
        Thread testThread = new Thread(test);
        testThread.setUncaughtExceptionHandler(new ExceptionHandlerThread());
        testThread.start();
    }

    public static void testCountThread(){
        Count count = new Count();
        for(int i=0;i<5;i++){
            CountThread countThread = new CountThread(count);
            countThread.start();
        }
        try {
            Thread.sleep(100l);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("5 people do the work, the final value:"+count.num);
    }

    public static void testReentrantCount(){
        //final ReentrantCount ct = new ReentrantCount();
        final ReentrantCount3 ct = new ReentrantCount3();
        //final ReentrantCount2 ct = new ReentrantCount2(new Object(),new Object());
        for(int i=0;i<2;i++){
            new Thread(){
                @Override
                public void run(){
                    ct.get();
                }
            }.start();
        }

        for(int i=0;i<2;i++){
            new Thread(){
                @Override
                public void run(){
                    ct.put();
                }
            }.start();
        }
    }

    public static void TestReentrantReadWriteLock(){
        final RenntrantReadWriteLockTest ct = new RenntrantReadWriteLockTest();
        for(int i=0;i<2;i++){
            new Thread(){
                public void run(){
                    //ct.getTotalBalance()
                }
            }.start();
        }

    }
}
