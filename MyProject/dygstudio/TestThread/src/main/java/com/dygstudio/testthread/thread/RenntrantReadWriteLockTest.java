package com.dygstudio.testthread.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/20-11:48
 * @Description:
 */
public class RenntrantReadWriteLockTest {
    double result= 0;
    private ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();
    private Lock readLock = rw1.readLock();
    private Lock writeLock = rw1.writeLock();

    public double getTotalBalance(){
        readLock.lock();
        try {
            return result;
        }finally {
            readLock.unlock();
        }
    }

    public void transfer(){
        writeLock.lock();
        try {
            result=1;
        }finally {
            writeLock.unlock();
        }
    }
}
