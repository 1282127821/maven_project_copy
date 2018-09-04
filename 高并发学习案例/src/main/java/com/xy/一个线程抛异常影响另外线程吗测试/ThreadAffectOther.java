package com.xy.一个线程抛异常影响另外线程吗测试;

/**
 * @fileName:ThreadAffectOther
 * @author:xy
 * @date:2018/8/28
 * @description:
 */
public class ThreadAffectOther {
    public static void main(String[] args) {
        T target=new T();
        Thread t1=new Thread(target,"t1");
        Thread t2=new Thread(target,"t2");
        t1.start();t2.start();
        System.out.println("main线程执行了");
    }
}
class T implements Runnable{
    public void run() {
        if (Thread.currentThread().getName().equals("t1")){
            throw new RuntimeException("让t1抛异常，看影响t2吗");
        }
        System.out.println(Thread.currentThread().getName()+":执行了");
    }
}
