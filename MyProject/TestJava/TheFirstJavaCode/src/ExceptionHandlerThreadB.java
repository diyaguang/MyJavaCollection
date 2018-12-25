import  java.lang.Thread.UncaughtExceptionHandler;
import  java.lang.Thread.UncaughtExceptionHandler;
/**
 * @ClassName ExceptionHandlerThreadB
 * @Description TODO
 * @Author newsu
 * @Date 2018/12/25 10:58
 * @PACKAGE_NAME PACKAGE_NAME
 **/

public class ExceptionHandlerThreadB  implements UncaughtExceptionHandler {
    public void uncaughtException(Thread t,Throwable e){
        System.out.printf("An exctption has been captured\n");
        System.out.printf("Thread: %s\n",t.getId());
        System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
        System.out.printf("Stack Trace: \n");
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s\n",t.getState());
    }
}
