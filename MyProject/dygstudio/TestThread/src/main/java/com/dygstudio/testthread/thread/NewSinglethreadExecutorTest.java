package com.dygstudio.testthread.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: diyaguang
 * @date: 2019/03/25 11:51 AM
 * @description: com.dygstudio.testthread.thread
 */
public class NewSinglethreadExecutorTest {
    public static void test(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for(int i=0;i<10;i++){
            final int no=i;
            Runnable runnable = new Runnable() {
                public void run() {
                    try{
                        System.out.println("into"+no);
                        Thread.sleep(1000L);
                        System.out.println("end"+no);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }
        executor.shutdown();
        System.out.println("Thread Main End !");
    }
}
