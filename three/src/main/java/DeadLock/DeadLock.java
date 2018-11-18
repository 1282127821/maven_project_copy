package DeadLock;

import org.junit.Test;

/**
 * @fileName:DeadLockTest
 * @author:xy
 * @date:2018/11/18
 * @description:
 */
@SuppressWarnings("all")
public class DeadLock extends Thread{
    private static Object lock1=new Object();
    private static Object lock2=new Object();
    private Object tool;
    public DeadLock(Object obj){
        tool=obj;
        if (tool==lock1)
            this.setName("锁1");
        else
            this.setName("锁2");
    }

    @Override
    public void run() {
        if (lock1==tool){//如果是lock1对象 ，则如下锁
            synchronized (lock1){
                try {
                    Thread.sleep(1000);//这个操作，主要是为了确定另外一个线程 拿到一个所
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
          System.out.println("锁1开始执行了");
                }
            }
        }else {
            synchronized (lock2){
                try {
                    Thread.sleep(1000);//这个操作，主要是为了确定另外一个线程 拿到一个所
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("锁2开始执行了");
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        DeadLock d1=new DeadLock(lock1);
        DeadLock d2=new DeadLock(lock2);
        d1.start();
        d2.start();
    }
}
