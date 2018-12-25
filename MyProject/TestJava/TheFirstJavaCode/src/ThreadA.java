/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author newsu
 * @Date 2018/12/25 11:43
 * @PACKAGE_NAME PACKAGE_NAME
 **/

public class ThreadA extends Thread {
    private Count count;
    public ThreadA(Count count){
        this.count = count;
    }
    public void run(){
        count.add();
    }
}
