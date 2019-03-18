package com.dygstudio.testthread.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/18-17:03
 * @Description:
 */
public class ReentrantCount {
    public void get(){
        final ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" get begin");
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName()+" get end");
            lock.unlock();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void put(){
        final  ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" put begin");
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName()+" put end");
            lock.unlock();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
