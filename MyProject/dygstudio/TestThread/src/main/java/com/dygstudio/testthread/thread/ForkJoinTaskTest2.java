package com.dygstudio.testthread.thread;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/26-14:39
 * @Description:
 */
public class ForkJoinTaskTest2 {
    public static void test(){
        String path = "D:\\MyProject";
        Integer count = new ForkJoinPool().invoke(new CountingTask(Paths.get(path)));
        System.out.println("地址 "+path+" 文件数量："+count);
        System.out.println("Thread main End！");
    }
}

class CountingTask extends RecursiveTask<Integer>{
     private Path dir;
     public CountingTask(Path dir){
         this.dir = dir;
     }
     protected Integer compute(){
         int count=0;
         List<CountingTask> subTasks = new ArrayList<>();
         try {
             DirectoryStream<Path> ds = Files.newDirectoryStream(dir);
             for(Path subPath : ds){
                 if(Files.isDirectory(subPath, LinkOption.NOFOLLOW_LINKS)){
                     subTasks.add(new CountingTask(subPath));
                 }else{
                     count++;
                 }
             }
             if(!subTasks.isEmpty()){
                 for(CountingTask subTask : invokeAll(subTasks)){
                     count+= subTask.join();
                 }
             }
         }catch (IOException e){
             return 0;
         }
         return count;
     }

}
