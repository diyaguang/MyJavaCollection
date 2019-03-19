package com.dygstudio.testthread.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/19-9:56
 * @Description:
 */
public class ReentrantCount3 {
    final ReentrantLock lockGet = new ReentrantLock();
    final ReentrantLock lockPut = new ReentrantLock();

    public void get() {
        try {
            lockGet.lock();
            System.out.println(Thread.currentThread().getName() + " get begin");
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName() + " get end");
            lockGet.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put() {
        try {
            lockPut.lock();
            System.out.println(Thread.currentThread().getName() + " put begin");
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName() + " put end");
            lockPut.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
