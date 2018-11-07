package com.xy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @fileName:可重入锁
 * @author:xy
 * @date:2018/8/25
 * @description:可重入锁
 */
public class  ReetantLockTest implements Runnable{
    public static int i=0;
    public static void main(String[] args) throws InterruptedException {
        ReetantLockTest reetantLockTest=new ReetantLockTest();
        Thread t1=new Thread(reetantLockTest);
        Thread t2=new Thread(reetantLockTest);
        t1.start();
        t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
ReentrantLock reentrantLock=new ReentrantLock();
    public void run() {
        for (int j = 0;j < 10000; j++) {
            reentrantLock.lock();
            reentrantLock.lock();
            try{
                i++;
            }finally{
                reentrantLock.unlock();
                reentrantLock.unlock();
            }
        }
    }
}
