package com.dygstudio.testthread.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/4/12-10:15
 * @Description:
 */
public class ConditionTest {
    public static void test(){
        Shared s = new Shared();
        new Producer(s).start();
        new Consumer(s).start();
    }
}

class Shared{
    private char c;
    private volatile boolean available;
    private final Lock lock;
    private final Condition condition;
    Shared(){
        available = false;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }
    Lock getLock(){
        return lock;
    }
    char getSharedChar(){
        lock.lock();
        try {
            System.out.println(" get available value:"+available);
            while (!available){
                try {
                    condition.await();
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
            available = false;
            condition.signal();
        }finally {
            lock.unlock();
            return c;
        }
    }
    void setSharedChar(char c){
        lock.lock();
        try {
            System.out.println("set available value:"+available);
            while (available){
                try {
                    condition.await();
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
            this.c = c;
            available = true;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
class Producer extends Thread{
    private final Lock l;
    private final Shared s;
    Producer(Shared s){
        this.s = s;
        l = s.getLock();
    }
    @Override
    public void run(){
        for(char ch='A'; ch<='Z';ch++){
            l.lock();
            s.setSharedChar(ch);
            System.out.println(ch+" produced by producer.");
            l.unlock();
        }
    }
}
class Consumer extends Thread{
    private final Lock l;
    private final Shared s;
    Consumer(Shared s){
        this.s = s;
        l = s.getLock();
    }
    @Override
    public void run(){
        char ch;
        do{
            l.lock();
            ch = s.getSharedChar();
            System.out.println(ch+" consumed by consumer.");
            l.unlock();
        }while (ch != 'Z');
    }
}