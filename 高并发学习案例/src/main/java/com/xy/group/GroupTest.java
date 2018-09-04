package com.xy.group;

/**
 * @fileName:GroupTest
 * @author:xy
 * @date:2018/8/25
 * @description:  线程组
 */
public class GroupTest implements Runnable{
    public static void main(String[] args) {
        /**
         * activeCount 表示当前活动线程个数，但是这是一个估计值，数目大了后就不准确了！！！！
         *list获取当前线程组所有线程的信息
         * destory 就是销毁  你懂得.一把用于执行指定次数后我销毁这个线程组 （我猜测这么用，一般不推荐使用）
         */
        ThreadGroup tg=new ThreadGroup("threadGroup");
          Thread t1=new Thread(tg, new GroupTest(), "t1");
          Thread t2=new Thread(tg, new GroupTest(), "t2");
          t1.start();
          t2.start();
          System.out.println(tg.activeCount());
          tg.list();

    }

    public void run() {
        System.out.println(Thread.currentThread().getThreadGroup().getName()+">>>>>>"+Thread.currentThread().getName());
    }
}
