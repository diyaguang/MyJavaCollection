/**
 * @author: diyaguang
 * @date: 2018/12/27 9:55 AM
 * @description: PACKAGE_NAME
 */

import java.util.concurrent.locks.StampedLock;

/**
 * @package: PACKAGE_NAME
 * @author: diyaguang
 * @date: 2018/12/27 - 9:55 AM
 * @description:
 */
public class Point {
    private double x,y;
    private final StampedLock sl = new StampedLock();
    void move(double deltaX,double deltaY){
        long stamp = sl.writeLock();
        try{
            x+=deltaX;
            y+=deltaY;
        }finally {
            sl.unlockWrite(stamp);
        }
    }

    //乐观锁读锁案例
    double distanceFromOrigin(){
        long stamp = sl.tryOptimisticRead(); //获得一个乐观锁
        double currentX = x,currentY=y;
        if(!sl.validate(stamp)){   //检查发出乐观锁后同时是否有其他写锁发生
            stamp = sl.readLock();  //如果没有，则获得一个读悲观锁
            try {
                currentX = x;
                currentY = y;
            }finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX*currentX+currentY*currentY);
    }

    //悲观锁读锁案例
    void moveIfAtOrigin(double newX,double newY){
        long stamp = sl.readLock();
        try {
            while(x==0.0 && y == 0.0){
                long ws = sl.tryConvertToWriteLock(stamp);   //将读锁转化为写锁
                if(ws != 0L){
                    stamp = ws;
                    x=newX;
                    y=newY;
                    break;
                }else{   //如果不能成功转换为写锁
                    sl.unlockRead(stamp);   //显示释放读锁
                    stamp = sl.writeLock();   //显式直接进行写锁，然后再通过循环再试
                }
            }
        }finally {
            sl.unlock(stamp);
        }
    }
}
