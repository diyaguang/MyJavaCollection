import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @package: PACKAGE_NAME
 * @author: newsu
 * @date: 2018/12/13 - 17:48
 * @description:
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE=10;
    private static final int SEARCH_THREADS=100;
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void test(){
        try(Scanner in = new Scanner(System.in)){
            System.out.print("Enter base directory (e.g. /opt/jdk1.8.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            Runnable enumerator=()->{
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                }
                catch (InterruptedException e){
                    //
                }
            };

            new Thread(enumerator).start();

            for(int i=1;i<=SEARCH_THREADS;i++){
                Runnable search=()->{
                    try{
                        boolean done = false;
                        while(!done){
                            File file = queue.take();
                            if(file==DUMMY){
                                queue.put(file);
                                done=true;
                            }
                            else{
                                search(file,keyword);
                            }
                        }
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    catch (InterruptedException e){
                        //
                    }
                };
                new Thread(search).start();
            }
        }
    }

    public static void enumerate(File directory) throws InterruptedException{
        File[] files = directory.listFiles();
        for(File file:files){
            if(file.isDirectory()) enumerate(file);
            else queue.put(file);
        }
    }

    public static void search(File file,String keyword) throws IOException{
        try(Scanner in=new Scanner(file,"UTF-8")){
            int lineNumber=0;
            while(in.hasNextLine()){
                lineNumber++;
                String line=in.nextLine();
                if(line.contains(keyword))
                    System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
            }
        }
    }
}
