package unlock;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @fileName:SynchronizeQueue
 * @author:xy
 * @date:2018/11/10
 * @description:
 */
public class SynchronizeQueueTest {
  @Test
  public void test() throws InterruptedException {
    SynchronousQueue<Integer> queue = new SynchronousQueue<>();
    queue.put(1);
    System.out.println("这里执行吗");
  }

  @Test
  public void test1() throws InterruptedException {
    /**
     * 以前还真有点忘了，使用这个队列必须多线程，否则 一直卡住 其实主要是
     * 他是这样的，一般我们知道队列是有大小的，对于put来说，首先我们会判断是否超过队列的限制，如果是那么当前线程就会wait住
     * 对于get来说，我们会去判断队列是否有值，没有当前线程就会wait住 然后就是：get时候如果等待，说明队列为0，所以此时要唤醒 put。反之亦然！
     * 而我们SynchronousQueue的大小为0，换句话说这个队列本身没有容量，也就不可能有值。所以不管是 get还是put都会被wait 但是还有一条很重要
     * get所在线程阻塞，会唤醒put，那么put后面的代码就可以继续运行了，反之亦然！
     *
     * 那么SynchronousQueue是怎么保证顺序的呢？当然默认 构造是 不公平的，也就是使用 TransferStack。
     * public SynchronousQueue(boolean fair) { transferer = (fair)? new TransferQueue() : new TransferStack(); }
     * 所以也就是说SynchronousQueue 队列他本身没有容量，而里面的TransferQueue，TransferStack才是真正用来存储的
     * 比如我连续put 3次，那么首选这三个线程都被阻塞了，这个没话说了。关键是 take的时候，对于公平的TransferQueue，来说没啥就是队列FIFO
     * 但是TransferStack 这个就是非公平的，因为他是 LIFO（后进先出）
     */
    SynchronousQueue<Integer> queue = new SynchronousQueue<>();
    queue.take();
    System.out.println("这里执行吗");
  }

  @Test
  @SuppressWarnings("all")
  public void test3() throws InterruptedException {
    SynchronousQueue<Integer> queue = new SynchronousQueue<>();
    new Thread(new SynQueue1(queue)).start();
    Thread.sleep(1000);
    new Thread(new SynQueue2(queue)).start();
  }
}

class SynQueue1 implements Runnable {
  private SynchronousQueue<Integer> queue;

  public SynQueue1(SynchronousQueue<Integer> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
      try {
          queue.put(1);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
}

class SynQueue2 implements Runnable {
  private SynchronousQueue queue;

  public SynQueue2(SynchronousQueue<Integer> queue) {
      this.queue=queue;
  }
  @Override
  public void run() {
      try {
          Object take = queue.take();
      System.out.println(take);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
}
