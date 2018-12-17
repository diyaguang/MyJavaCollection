/**
 * @author: diyaguang
 * @date: 2018/12/14 5:02 PM
 * @description: PACKAGE_NAME
 * @package: PACKAGE_NAME
 * @author: diyaguang
 * @date: 2018/12/14 - 5:02 PM
 * @description:
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @package: PACKAGE_NAME
 * @author: diyaguang
 * @date: 2018/12/14 - 5:02 PM
 * @description:
 */

public class FutureTest {
    public static void test(){
        try(Scanner in = new Scanner(System.in)){
            System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            MatchCounter counter = new MatchCounter(new File(directory),keyword);
            FutureTask<Integer> task = new FutureTask<>(counter);
            Thread t = new Thread(task);
            t.start();
            try{
                System.out.println(task.get()+" matching files.");
            }catch (ExecutionException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                //
            }
        }
    }
}

class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyword;

    public MatchCounter(File directory,String keyword){
        this.directory = directory;
        this.keyword = keyword;
    }

    public Integer call(){
        int count=0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for(File file:files){
                if(file.isDirectory()){
                    MatchCounter counter = new MatchCounter(file,keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                }
                else
                    if(search(file)) count++;
            }
            for(Future<Integer> result:results){
                try{
                    count+=result.get();
                }catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }catch (InterruptedException e){
            //
        }
        return count;
    }

    public boolean search(File file){
        try{
            try(Scanner in = new Scanner(file,"UTF-8")){
                boolean found = false;
                while(!found && in.hasNextLine()){
                    String line = in.nextLine();
                    if(line.contains(keyword)) found = true;
                }
                return found;
            }
        }catch (IOException e){
            return false;
        }
    }
}