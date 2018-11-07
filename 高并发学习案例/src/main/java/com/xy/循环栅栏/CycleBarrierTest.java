package com.xy.循环栅栏;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @fileName:CycleBarrierTest
 * @author:xy
 * @date:2018/8/26
 * @description:循环栅栏
 */
public class CycleBarrierTest {
  public static void main(String[] args) {
    /** 循环栅栏的特点就是 线程达到指定个数后就会触发 栅栏对象里面的对象(线程对象)的run方法 */
    /** 士兵总数 */
    final int N = 10;
    boolean flag = false;
    Thread[] allSoldier = new Thread[N];
    CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(false, N));
    System.out.println("紧急铃声,开始集合队伍");
    for (int i = 0; i < N; i++) {
      /** 注意这里每个cyclicBarrier都不一样，因为new BarrierRun */
      allSoldier[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
      allSoldier[i].start();
      /** 这里中断 [0]线程，那么普通线程由于中断发生中断异常 */
      if (i == 5) {
        allSoldier[0].interrupt();
      }
    }
  }
}
/** 表示战士 */
class Soldier implements Runnable {
  private String soldier;
  private CyclicBarrier cyclicBarrier;

  public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
    this.soldier = soldier;
    this.cyclicBarrier = cyclicBarrier;
  }

  public void run() {
    try {
      /** 等待所有士兵集合完毕 */
      cyclicBarrier.await();
      /** 开始工作 */
      doWork();
      /** 等待所有士兵完成任务 */
      cyclicBarrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }

  private void doWork() throws InterruptedException {
    /** 模拟士兵做任务耗时 */
    Thread.sleep(new Random().nextInt(10 * 1000));
    System.out.println(soldier + ":完成任务");
  }
}
/** 每次调用 await()后，线程数目达到指定值(此处10个)后触发 */
class BarrierRun implements Runnable {
  /** true为全部完成任务标记，false为全部集合完毕 */
  private boolean flag;

  private int N;

  public BarrierRun(boolean flag, int n) {
    this.flag = flag;
    N = n;
  }

  public void run() {
    if (flag) {
      System.out.println(N + ":个,全部完成任务");
    } else {
      System.out.println(N + ":个,全部集合完毕");
    }
  }
}
