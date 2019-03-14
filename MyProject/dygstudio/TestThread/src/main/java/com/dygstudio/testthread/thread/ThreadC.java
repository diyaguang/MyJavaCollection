package com.dygstudio.testthread.thread;

import java.util.concurrent.Callable;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/14-17:04
 * @Description: The third way is to inherit the Callable interface and implement the call method. And it is possible to get results from threads.
 *                        When executing, the object of FutureTask<T>  is used to obtain the result of thread execution.
 */
public class ThreadC implements Callable<String> {
    public String call() throws Exception{
        try {
            Thread.sleep(500L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("This is thread C" );
        return "thread C";
    }
}
