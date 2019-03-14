package com.dygstudio.testthread.thread;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/14-16:42
 * @Description:The first way to implement Thread is to make sure that only one parent class can be inherited.
 */
public class ThreadA extends Thread{
    public void run(){
        super.run();
        try {
            Thread.sleep(500L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("This is thread A");
    }
}
