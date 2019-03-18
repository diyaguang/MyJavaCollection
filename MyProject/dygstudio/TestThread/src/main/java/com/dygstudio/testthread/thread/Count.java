package com.dygstudio.testthread.thread;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/18-14:34
 * @Description:
 */
public class Count {
    public int num=0;
    public synchronized void add(){
        try {
            Thread.sleep(5l);
        }catch (InterruptedException e){
            //
        }
        num+=1;
        System.out.println(Thread.currentThread().getName()+"-"+num);
    }
}
