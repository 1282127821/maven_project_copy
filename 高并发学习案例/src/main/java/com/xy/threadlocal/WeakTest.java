package com.xy.threadlocal;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * @fileName:WeakObj
 * @author:xy
 * @date:2018/11/4
 * @description:
 */
public class WeakTest{
    @Test
    public void test(){
        WeakObj weakObj=new WeakObj("weak1");
        Weak weak=new Weak(12);
        WeakReference<Weak> weakWeakReference=new WeakReference<>(weak);
        weakObj.setWeakWeakReference(weakWeakReference);
        /**
         * 通过实验，我发现如果一个强引用对象 strong1（只平时new的或反射出来的这种），如果被其他 强引用类型引用着 otherstrong1，那么就算这个
         * strong1=null  System.gc(); 这个对象strong1也不会被回收。就比如当我把①打开 你会发现②不为null。因为normalObj是一个强引用对象它内部
         * 引用着weak对象。
         * 但是呢？如果我们把①注释掉，你会发现weak就为null了。因为weak对象只被 一个弱引用对象weakWeakReference使用着。
         * 总结：
         * 如果一个强对象1 被其他 强引用对象2 引用着，那么我们就算释放 这个对象1 ，他还是不能被回收，只是将对象1 的引用 和 该对象地址断开了
         * (内存中还有对象1的地址)，当然我们再释放对象2 这时候 对象1就真的被释放了（因为对象1 只有被对象1的引用和对象2的引用 占用着）
         * 如果一个强对象1 被其他 弱引用对象2 引用着，那么我们释放 这个对象1，就会释放对象1的地址（因为 对象1 只有被 对象1 的引用和对象2的引用
         * 占用着，对象1的引用已经断开了，而由于对象2是一个弱引用 所以他也会被释放）
         *开始我的理解有误区，我一直以为 弱引用是WeakReference<Weak> weakWeakReference=new WeakReference<>(weak);要将weakWeakReference置为null
         * 就会将弱引用处理掉。其实啊 是将 弱引用放入的强对象---weak 对象 置空 如果这个对象被置空 那么弱引用里面的weak也会被置空
         * 但是前提是 该强只被弱引用 占用，因为 如果强对象1，还被其他强对象2占用着（而这个2没有被释放），那么就不会释放，因为1还被 2占用着，而2不是弱引用
         */
//        NormalObj normalObj=new NormalObj("NormalObj1", weak);//①
        weak=null;
        System.gc();
        System.out.println(weakWeakReference.get());//②
//        System.out.println(normalObj.getWeak());
    }
    @Test
    public void test2(){
        /**
         * 这个就是测试 ，某个对象只有弱引用引用（测试就是直接  WeakReference<Weak> weakWeakReference=new WeakReference<>(new Weak(23));这样他就只会被
         * weakWeakReference这个引用占用）
         * 然后我发现只要触发 gc操作(不管有没有标记谁，弱引用占用的对象都会被 置为null)
         *但是 如果我们把① 使用②替换，结果就不是null。因为软引用引用的对象 不仅仅被软引用占用，还被 强对象weak占用着
         */
        WeakObj weakObj2=new WeakObj("weak2");
        WeakObj weakObj3=new WeakObj("weak3");
        WeakReference<Weak> weakWeakReference=new WeakReference<>(new Weak(23));//① weakWeakReference.get()=com.xy.threadlocal.Weak@5d6f64b1
//        Weak weak =new Weak(23);//②
//        WeakReference<Weak> weakWeakReference=new WeakReference<>(weak);//② weakWeakReference.get()=null
        System.out.println(weakWeakReference.get());
        weakObj2.setWeakWeakReference(weakWeakReference);
//        weakObj2=null;
//        weakObj3=null;
        System.gc();
//        System.out.println(weakWeakReference.get());
        System.out.println(weakObj2.getWeakWeakReference().get());
    }
    @Test
    public void test3() throws InterruptedException {
        ThreadLocal<Weak> t=new ThreadLocal<>();
        t.set(new Weak(22));
        System.gc();
        Thread.sleep(3000);
        System.gc();
        Thread.sleep(3000);
        System.gc();
        Thread.sleep(3000);
        System.out.println(t.get());
    }
}

class WeakObj {
    private String name;
    private WeakReference<Weak> weakWeakReference;

    public WeakObj(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public WeakReference<Weak> getWeakWeakReference() {
        return weakWeakReference;
    }

    public void setWeakWeakReference(WeakReference<Weak> weakWeakReference) {
        this.weakWeakReference = weakWeakReference;
    }
}
class NormalObj {
    private String name;
    private Weak weak;

    public NormalObj(String name, Weak weak) {
        this.name = name;
        this.weak = weak;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weak getWeak() {
        return weak;
    }

    public void setWeak(Weak weak) {
        this.weak = weak;
    }
}
class Weak{
    private int age;

    public Weak(int age) {
        this.age = age;
    }
}
//@Test
////    public void test(){
////        ThreadClazz threadClazz=new ThreadClazz();
////        Thread thread=new Thread(threadClazz);
////        thread.start();
////
////        WeakObj weakObj=new WeakObj("weak2");
////
////        WeakReference<Weak> weakWeakReference=new WeakReference<>(new Weak(23));
////        weakObj.setWeakWeakReference(weakWeakReference);
////        weakObj=null;
////        System.gc();
////        System.out.println(weakWeakReference);
////    }
