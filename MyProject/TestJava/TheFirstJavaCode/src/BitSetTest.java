import java.util.BitSet;

/**
 * @author: diyaguang
 * @date: 2018/11/30 3:55 PM
 * @description: PACKAGE_NAME
 */
public class BitSetTest {
    public static void testBitSet(){
        int n = 50;
        long start = System.currentTimeMillis();
        BitSet b = new BitSet(n+1);
        int count = 0;
        int i;
        for(i=2;i<=n;i++){
            b.set(i);
        }
        i = 2;
        while(i*i<=n){
            System.out.println("i value: "+i);
            if(b.get(i)){
                count++;
                System.out.println("count value: "+count);
                int k=2*i;
                while(k<=n){
                    System.out.println("k value: "+k);
                    b.clear(k);
                    k+=i;
                }
                System.out.println("=======================================");
            }
            i++;
        }
        while(i<=n){
            if(b.get(i)) count++;
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(count+" primes");
        System.out.println((end-start)+" milliseconds");
    }
}
