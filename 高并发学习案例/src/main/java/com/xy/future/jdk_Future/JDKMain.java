package com.xy.future.jdk_Future;

import java.util.concurrent.*;

/**
 * @fileName:JDKMain
 * @author:xy
 * @date:2018/12/2
 * @description:
 */
@SuppressWarnings("all")
public class JDKMain {
    /**
     * 原理其实和 我们之前写的案例差不多，只不过jdk考虑的更全面，我们只需要考虑真实数据，因为本身
     * future数据立即返回的只是为了立即进行后续操作其他业务操作(不等待)，而我们最终肯定还是需要真实数据
     * 但是jdk肯定不知道我们需要什么数据所以真实数据必须交给我们实现，而他使用上转型(Callable)
     * FutureTask优点类似 我们之前写的Client，只不过我们当时是Client自己new了一个 FutureData对象，而现在我们是直接将
     * MyRealData对象(注意这个是真实对象)给了 FutureTask，理由就是 FutureTask是jdk提供的，MyRealData是我们实现的，所以你总得告诉jdk内部吧
     * 一般要么使用反射要么自己主动给.
     * 然后我们之前是调用Client#request方法，但是现在呢？由于我们的FutureTask是一个线程类，所以我们可以重写线程run方法，然后使用线程池启动
     * 这样也相当于做到了Client#request作用（只不过现在把代码写在run）
     *
     * futureTask.get()
     * futureTask.get(6000, TimeUnit.MILLISECONDS) 这个和futureTask.get()区别就是前者直接去拿，但是这个就是延迟多少时间单位后再去拿，拿不到两者都报错
     * futureTask.isDone() 任务是否真正完成
     * futureTask.isCancelled()判断是否取消
     *futureTask.cancel(true/false); 取消任务 这个着重说一下
     *如果我们的任务还没有开始 那么 就是取消任务，他就会返回 true， 打开① 处注释
     *如果任务已经完成了，那么 取消任务 他就会返回false (你想想我都完成了，你取消啥哦) 打开② 处注释
     *如果任务正在运行ing但是还没有完成   打开注释③
     * . 其实这个验证听曹丹的，开始我的MyRealData直接延迟sleep 5s模拟 耗时，但是这样有一个问题就是一旦sleep
     * 此时就会去执行其他线程。所以后来我就来了一个 循环1000次操作。
     * 此时就可以验证 utureTask.cancel(true/false)了。由于这时候我submit(futureTask)，也就是开启了任务
     * 如果 为true 也就是表示 可能会中断MyRealData 的call()执行,但是如果call()快，就不影响。（由于是多线程  多尝试n次）
     * 如果为false 只有一种，那就是 一定会等待MyRealData 的call()执行完毕  （由于是多线程  多尝试n次）
     * 这也是为嘛取名 mayInterruptIfRunning  可能会中断，而不是 是否中断 ，因为 对于true来说不一定中断
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<String> futureTask=new FutureTask<>(new MyRealData()); //
        ExecutorService executorService = Executors.newFixedThreadPool(1);
//    System.out.println(futureTask.cancel(true)+">>>>>>"); // ①
        executorService.submit(futureTask);
        System.out.println("操作完成");
        System.out.println(futureTask.isDone());
//        System.out.println(futureTask.get(6000, TimeUnit.MILLISECONDS));
//        boolean cancel = futureTask.cancel(true);
//    System.out.println(cancel+">>>>>");
//        System.out.println(futureTask.cancel(true)+">>>>>>");//③
        //这里依旧模拟其他业务操作
//        Thread.sleep(100);
        Thread.sleep(6000);
//        System.out.println(futureTask.cancel(true)+">>>>>>");//②
        System.out.println(futureTask.get());//如果我们的其他业务时间不够，那么这里肯定阻塞，因为while(true)
        System.out.println(futureTask.isDone());
        executorService.shutdown();
    }
}
