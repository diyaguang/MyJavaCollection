
/**
 * @package: PACKAGE_NAME
 * @author: diyaguang
 * @date: 2018/12/24 - 4:37 PM
 * @description:
 */
public class ThreadMain {
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue() {
            return 0;
        }
    };
    public ThreadLocal<Integer> getThreadLocal(){
        return seqNum;
    }
    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }
    public static void test(){
        ThreadMain sn = new ThreadMain();

        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);

        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread{
        private ThreadMain sn;
        public TestClient(ThreadMain sn){
            this.sn = sn;
        }
        public void run(){
            for(int i=0;i<3;i++){
                System.out.println("thread["+Thread.currentThread().getName()+"] --> sn["+sn.getNextNum()+"]");
            }
            sn.getThreadLocal().remove();
        }
    }
}
