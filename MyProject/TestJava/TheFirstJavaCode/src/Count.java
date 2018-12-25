/**
 * @ClassName Count
 * @Description TODO
 * @Author newsu
 * @Date 2018/12/25 11:38
 * @PACKAGE_NAME PACKAGE_NAME
 **/

public class Count {
    public int num=0;
    public synchronized void add(){
        try{
            Thread.sleep(51);
        }catch(InterruptedException e){
            //
        }
        num+=1;
        System.out.println(Thread.currentThread().getName()+"-"+num);
    }

    public synchronized void methodA(){
        try {
            Thread.sleep(51);
        }catch(InterruptedException e){
            //
        }
        num+=1;
        System.out.println(Thread.currentThread().getName()+"-"+num);
    }

    public void methodB(){
        synchronized (this){
            try{
                Thread.sleep(51);
            }catch (InterruptedException e){
                //
            }
            num+=1;
            System.out.println(Thread.currentThread().getName()+"-"+num);
        }
    }
}
