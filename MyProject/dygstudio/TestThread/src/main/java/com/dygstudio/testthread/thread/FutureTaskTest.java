package com.dygstudio.testthread.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/26-10:07
 * @Description:
 */
public class FutureTaskTest {
    public static void test() throws InterruptedException, ExecutionException{
        SonTask task1 = new SonTask("Thread Son1");
        FutureTask<String> f1 = new FutureTask<String>(task1);
        new Thread(f1).start();
        System.out.println(f1.get());
        FutureTask<Integer> f2 = new FutureTask<Integer>(new MyRun(),22);
        new Thread(f2).start();
        System.out.println("result_"+f2.get());
    }
}

class SonTask implements Callable<String>{
    private String name="";
    SonTask(String name){
        this.name = name;
    }
    @Override
    public String call() throws Exception{
        Thread.sleep(1000L);
        System.out.println(name+"任务计算完成");
        return "result_11";
    }
}

class MyRun implements Runnable{

    @Override
    public void run(){
        try {
            Thread.sleep(1000L);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("特定线程2完成");
    }
}
