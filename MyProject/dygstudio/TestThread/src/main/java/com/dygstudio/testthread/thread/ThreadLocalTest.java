package com.dygstudio.testthread.thread;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/15-16:01
 * @Description:
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };
    private static ThreadLocal<String> localString = new ThreadLocal<String>(){
        public String initialValue(){
            return "String:  ";
        }
    };
    public ThreadLocal<Integer> getThreadLocal(){
        return seqNum;
    }
    public ThreadLocal<String> getStringThreadLocal(){
        return localString;
    }
    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }
    public String getNextString(Integer i){
        localString.set(localString.get()+i);
        return localString.get();
    }
    public static class TestClient extends Thread{
        private  ThreadLocalTest sn;
        public TestClient(ThreadLocalTest sn){
            this.sn = sn;
        }
        public void run(){
            for(int i=0;i<3;i++){
                System.out.println("thread["+Thread.currentThread().getName()+"] --> sn["+sn.getNextNum()+"]");
                System.out.println("thread["+Thread.currentThread().getName()+"] --> sn["+sn.getNextString(i)+"]");
            }
            sn.getThreadLocal().remove();
            sn.getStringThreadLocal().remove();
        }
    }
}
