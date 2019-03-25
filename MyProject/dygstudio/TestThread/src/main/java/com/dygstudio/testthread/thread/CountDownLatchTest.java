package com.dygstudio.testthread.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author: diyaguang
 * @date: 2019/03/25 9:58 AM
 * @description: com.dygstudio.testthread.thread
 */
public class CountDownLatchTest {
    public static void test(){
        try {
            CountDownLatch latch = new CountDownLatch(3);
            Worker worker1 = new Worker("Jack 程序员1",latch);
            Worker worker2 = new Worker("Rose 程序员2",latch);
            Worker worker3 = new Worker("Json 程序员3",latch);
            worker1.start();
            worker2.start();
            worker3.start();
            latch.await();
            System.out.println("Main thread end!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
    static class Worker extends Thread{
        private String workerName;
        private CountDownLatch latch;
        public Worker(String workerName,CountDownLatch latch){
            this.workerName = workerName;
            this.latch = latch;
        }
        @Override
        public void run(){
            try{
                System.out.println("Worker:"+workerName+" is begin.");
                Thread.sleep(1000L);
                System.out.println("Worker:"+workerName+" is end.");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}
