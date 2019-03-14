import com.dygstudio.testthread.thread.ThreadA;
import com.dygstudio.testthread.thread.ThreadB;
import com.dygstudio.testthread.thread.ThreadC;
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
        System.out.println("This main thread：");
        testThreadA();
        testThreadB();
        testThreadC();
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
}
