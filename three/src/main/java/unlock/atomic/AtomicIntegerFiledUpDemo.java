package unlock.atomic;

import org.junit.Test;

import java.util.Vector;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @fileName:AtomicIntegerFiledUpDemo
 * @author:xy
 * @date:2018/11/10
 * @description:
 */
@SuppressWarnings("all")
public class AtomicIntegerFiledUpDemo {
  class Target {
    int id;
    volatile int
        score; // 如果 他不是volatile就会 --java.lang.IllegalArgumentException: Must be volatile
               // type,如果是private修饰符就也会报错
  }

  private AtomicIntegerFieldUpdater<Target> updater =
      AtomicIntegerFieldUpdater.newUpdater(Target.class, "score");
  private AtomicInteger allScore = new AtomicInteger();
  private int common = 0;

  @Test
  public void test() throws InterruptedException {
    /** 使用AtomicIntegerFieldUpdater，必须保证指定字段是 volatile 修饰的，且不能是 private以及static修饰 */
    final Target target = new Target();
    Thread[] threads = new Thread[1000];
    for (int i = 0; i < 1000; i++) {
      threads[i] =
          new Thread(
              new Runnable() {
                @Override
                public void run() {
                  common += 1; // 这个就会出问题了
                  // 本来score只是一个普通的字段，不可能出现updater==allScore
                  updater.incrementAndGet(
                      target); // updater会对指定对象的字段进行incrementAndGet操作，所以必须是同一个target对象
                  allScore.incrementAndGet(); // 验证用的
                }
              });
      threads[i].start();
    }
    for (int i = 0; i < 1000; i++) {
      threads[i].join(); // 等待其他线程执行完毕，才开始执行主线程
    }
    System.out.println("updater>>" + target.score); // 如果updater==allScore相等代表验证成功！
    System.out.println("allScore>>" + allScore); // 这个 本身就是安全的，用来验证上面的updater的
    System.out.println(common); // 这个大多数情况 和上面两字段不同，因为线程不安全
  }
}
