package com.dygstudio.testthread.thread;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/18-14:37
 * @Description:
 */
public class CountThread extends Thread {
    private Count count;
    public CountThread(Count count){
        this.count = count;
    }
    public void run(){
        count.add();
    }
}
