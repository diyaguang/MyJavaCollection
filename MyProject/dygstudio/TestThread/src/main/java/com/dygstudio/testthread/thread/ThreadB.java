package com.dygstudio.testthread.thread;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/14-16:58
 * @Description: The second way to create a thread, which inherits Runnable, is somewhat different at startup
 */
public class ThreadB implements Runnable {
    public void run(){
        try {
            Thread.sleep(500L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("This is thread B" );
    }
}
