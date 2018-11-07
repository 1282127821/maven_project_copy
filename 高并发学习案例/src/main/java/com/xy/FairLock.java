package com.xy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @fileName:FairLock
 * @author:xy
 * @date:2018/8/25
 * @description:
 */
public class FairLock implements Runnable {
  public static ReentrantLock reentrantLock = new ReentrantLock(true);

  public void run() {
    while (true) {
      try {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName()+":获取锁");
      } finally {
        reentrantLock.unlock();
      }
    }
  }

  public static void main(String[] args) {
      FairLock fairLock=new FairLock();
      Thread t1=new Thread(fairLock,"t1");
      Thread t2=new Thread(fairLock,"t2");
      t1.start();t2.start();
  }
}
