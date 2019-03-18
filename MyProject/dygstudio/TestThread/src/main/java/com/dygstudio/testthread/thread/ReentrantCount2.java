package com.dygstudio.testthread.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/18-17:30
 * @Description:
 */
public class ReentrantCount2 {
    Object lockObject1;
    Object lockObject2;

    public ReentrantCount2(Object obj1,Object obj2){
        this.lockObject1 = obj1;
        this.lockObject2 = obj2;
    }
    public void get(){

        try {
            synchronized (lockObject1) {
                System.out.println(Thread.currentThread().getName() + " get begin");
                Thread.sleep(1000L);
                System.out.println(Thread.currentThread().getName() + " get end");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void put(){

        try {
            synchronized (lockObject2) {
                System.out.println(Thread.currentThread().getName() + " put begin");
                Thread.sleep(1000L);
                System.out.println(Thread.currentThread().getName() + " put end");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
