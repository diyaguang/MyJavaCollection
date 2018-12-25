/**
 * @ClassName SynchronizedMain
 * @Description TODO
 * @Author newsu
 * @Date 2018/12/25 11:45
 * @PACKAGE_NAME PACKAGE_NAME
 **/

public class SynchronizedMain {
    public static void test(){
        Count count = new Count();
        for(int i=0;i<5;i++){
            ThreadA task = new ThreadA(count);
            task.start();
        }
        try {
            Thread.sleep(1001);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("5 个人干完活：最后的值"+count.num);
    }
}
