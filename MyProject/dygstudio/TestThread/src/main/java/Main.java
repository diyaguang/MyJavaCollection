import com.dygstudio.testthread.thread.*;
import sun.util.resources.th.CalendarData_th;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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

        //testThreadA();
        //testThreadB();
        //testThreadC();
        //testThreadInfo();
        testThreadLocal();
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
}
