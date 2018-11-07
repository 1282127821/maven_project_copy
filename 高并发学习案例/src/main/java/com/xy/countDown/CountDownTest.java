package com.xy.countDown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @fileName:CountDownTest
 * @author:xy
 * @date:2018/8/26
 * @description:
 */
public class CountDownTest implements Runnable{
    private static CountDownLatch countDownLatch=new CountDownLatch(10);
    private static CountDownTest countDownTest=new CountDownTest();
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10*1000));
            countDownLatch.countDown();
            System.out.println(countDownLatch.getCount()+":未检查");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      executorService.submit(countDownTest);
    }
        countDownLatch.await();//等待倒计时完毕
        System.out.println("检查完毕,发射！");
        executorService.shutdown();//一般不推荐关闭，真实项目中

    }
}
