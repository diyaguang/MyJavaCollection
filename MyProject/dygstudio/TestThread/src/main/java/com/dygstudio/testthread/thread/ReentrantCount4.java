package com.dygstudio.testthread.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019-04-12-17:49
 * @Description:
 */
public class ReentrantCount4 {
    public static void test(){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        final ReentrantLock lock = new ReentrantLock();

        class Worker implements Runnable{
            private final String name;
            Worker(String name){
                this.name = name;
            }

            @Override
            public void run(){
                lock.lock();
                try {
                    if(lock.isHeldByCurrentThread())
                        System.out.printf("Thread %s entered critical section. %n",name);
                    System.out.printf("Thread %s performing work. %n",name);
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                    System.out.printf("Thread %s finished working.%n",name);
                }finally {
                    lock.unlock();
                }
            }
        }
        executor.execute(new Worker("ThdA"));
        executor.execute(new Worker("ThdB"));
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        executor.shutdownNow();
    }
}
