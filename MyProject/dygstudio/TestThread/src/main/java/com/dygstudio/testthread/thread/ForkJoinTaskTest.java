package com.dygstudio.testthread.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/26-10:29
 * @Description:
 */
public class ForkJoinTaskTest {
    public static void test() throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1,5);
        Future<Integer> result = forkJoinPool.submit(task);
        System.out.println("1~5 最终相加结果"+result.get());
        CountTask task2 = new CountTask(1,100);
        Future<Integer> result2 = forkJoinPool.submit(task2);
        System.out.println("1~100 最终相加的结果："+result2.get());
        CountTask task3 = new CountTask(1,1000);
        Future<Integer> result3 = forkJoinPool.submit(task3);
        System.out.println("1~1000 最终相加的结果："+result3.get());
        CountTask task4 = new CountTask(1,10000);
        Future<Integer> result4 = forkJoinPool.submit(task4);
        System.out.println("1~10000 最终相加的结果："+result4.get());
        System.out.println("Thread Main End!");
    }
}
class CountTask extends RecursiveTask<Integer>{
    private static final long serialVersionUID = 3336021432713606929L;
    private static int splitSize=2;
    private int start,end;
    public CountTask(int start,int end){
        this.start = start;
        this.end = end;
    }

    protected Integer compute(){
        int sum=0;
        boolean canCompute=(end-start)<=splitSize;  //如果任务已经不再需要拆分了，则开始计算
        if(canCompute){
            for(int i=start;i<=end;i++){
                sum=sum+i;
            }
        }
        else{
            int middle = (start+end)/2;  //拆分成两个子任务
            CountTask firstTask = new CountTask(start,middle);
            CountTask secondTask = new CountTask(middle+1,end);
            firstTask.fork();  //开始执行
            secondTask.fork();
            int firstResult = firstTask.join(); //获得第一个子任务的结果，得不到结果，此线程不会往下面执行
            int secondResult = secondTask.join();
            sum = firstResult+secondResult;
        }
        return sum;
    }
}