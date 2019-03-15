package com.dygstudio.testthread.thread;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/15-9:54
 * @Description:
 */
public class ThreadInfo implements Runnable {
    public void run(){
        try {
            Thread.sleep(100000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Thread curThread = Thread.currentThread();
        String curThreadName = curThread.getName();
        System.out.println("This is thread name:"+curThreadName);
        System.out.println("return the "+curThreadName+" of the thread group at active count:"+Thread.activeCount());
        System.out.println("Return the thread id:"+curThread.getId());
        System.out.println("Return the thread priority:"+curThread.getPriority());
        System.out.println("Return the thread state:"+curThread.getState());
        System.out.println("Return the thread is alive:"+curThread.isAlive());
        System.out.println("Return the thread is daemon:"+curThread.isDaemon());
    }
}
