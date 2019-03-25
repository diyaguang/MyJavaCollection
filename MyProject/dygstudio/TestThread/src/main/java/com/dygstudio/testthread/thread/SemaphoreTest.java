package com.dygstudio.testthread.thread;

import java.util.concurrent.Semaphore;

/**
 * @author: diyaguang
 * @date: 2019/03/25 10:30 AM
 * @description: com.dygstudio.testthread.thread
 */
public class SemaphoreTest {
    public static void test(){
        final Semaphore semaphore = new Semaphore(3);
        for(int i=0;i<10;i++){
            final int no=i;
            Runnable thread = new Runnable() {
                public void run() {
                    try{
                        System.out.println("用户"+no+"连接上了:");
                        Thread.sleep(300L);
                        semaphore.acquire();
                        System.out.println("用户"+no+"开始访问后台");
                        Thread.sleep(1000L);

                        semaphore.release();
                        System.out.println("用户"+no+"访问结束.");

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            new Thread(thread).start();
        }
        System.out.println("Main thread end!");
    }
}
