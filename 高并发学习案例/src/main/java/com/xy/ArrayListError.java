package com.xy;

import java.util.ArrayList;

/**
 * @fileName:ArrayListError
 * @author:xy
 * @date:2018/8/25
 * @description:
 */
public class ArrayListError {
    static ArrayList<Integer> al=new ArrayList<Integer>(10);
    public static class AddThread implements Runnable{
        public void run() {
      for (int i = 0; i < 1000000; i++) {
        al.add(i);
      }
        }
    }
    public static void main(String[] args) throws InterruptedException{
          Thread t1=new Thread(new AddThread());
          Thread t2=new Thread(new AddThread());
          t1.start();t2.start();
          t1.join();t2.join();//这里为了让t1，t2先执行完毕再执行main
        System.out.println(al.size());
    }
}
