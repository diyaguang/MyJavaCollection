/**
 * @ClassName TeceptionHandlerMain
 * @Description TODO
 * @Author newsu
 * @Date 2018/12/25 11:11
 * @PACKAGE_NAME PACKAGE_NAME
 **/

public class TeceptionHandlerMain {
    public  static void test(){
        ThreadB task = new ThreadB();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandlerThreadB());
        thread.start();
    }
}
