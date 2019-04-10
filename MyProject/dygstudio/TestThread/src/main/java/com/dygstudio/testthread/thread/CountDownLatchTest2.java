package com.dygstudio.testthread.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/4/10-9:59
 * @Description:
 */
public class CountDownLatchTest2 {
    final static int NTHREADS = 3;
    public static void test(){
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(NTHREADS);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    report("entered run()");
                    startSignal.await();
                    report("doing work");
                    Thread.sleep((int)(Math.random()*1000));
                    doneSignal.countDown();
                }catch (InterruptedException ie){
                    System.err.println(ie);
                }
            }
            void report(String s){
                System.out.println(System.currentTimeMillis()+":"+Thread.currentThread()+":"+s);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        for(int i=0;i<NTHREADS;i++){
            executor.execute(r);
        }
        try {
            System.out.println("Main thread doing something");
            Thread.sleep(1000);
            startSignal.countDown();
            System.out.println("Main thread doing something else");
            doneSignal.await();
            executor.shutdownNow();
        }catch (InterruptedException ie){
            System.err.println(ie);
        }
    }
}
