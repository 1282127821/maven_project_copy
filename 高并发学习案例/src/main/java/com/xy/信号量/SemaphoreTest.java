package com.xy.信号量;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @fileName:SemaphoreTest
 * @author:xy
 * @date:2018/8/26
 * @description: 信号量
 */
public class SemaphoreTest implements Runnable{
    Semaphore semaphore=new Semaphore(5);//设置并发量为5个，也就是最多5个一起并发
    public void run(){
        try {
//            System.out.println(semaphore.tryAcquire());
            semaphore.acquire();
            /**
             * 这里延迟，主要是为了semaphore每次打印5个，容易观看效果
             * 记住用完后 一定要释放 信号量，否则一直被占有，那么后面的无法获取，导致只能打印5个
             * 搞定这个后我总算知道 ConcurrentHashMap的原理大概就是使用信号量
             * api解释：
             * acquire表示尝试获取信号，获取不到就 一直阻塞
             * release释放信号
             * acquireUninterruptibly()不响应中断，其他和acquire()差不多
             * tryAcquire(),tryAcquire(ms)尝试获取 信号，获取不到立马返回 false ，获取到返回true，后面一个是尝试在有限时间内
             * 尝试获取，在有限时间获取不到返回false，获取到了返回true
             *
             */
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getId()+"：执行了");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        SemaphoreTest semaphoreTest=new SemaphoreTest();
    for (int i = 0; i < 20; i++) {
        executorService.submit(semaphoreTest);//使用线程池执行任务，任务就是我们的线程类
    }
        /**
         * 记得关闭，一般正常公司项目线程池不会关闭，因为使用线程池就是为了重复利用线程，如果关闭了，就不是重复利用了
         * 但是我这里为了方便测试所以关闭，免得忘了，记住真实项目中不关闭即可！
         */
    executorService.shutdown();
    }
}
