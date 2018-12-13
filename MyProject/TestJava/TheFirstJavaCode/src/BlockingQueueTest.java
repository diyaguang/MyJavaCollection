import java.io.File;
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

            
        }
    }
}
