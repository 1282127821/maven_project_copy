package com.xy.Disruptor学习;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @fileName:Main
 * @author:xy
 * @date:2018/11/25
 * @description:
 */
@SuppressWarnings("all")
public class Main {
    long start = 0;
    long end = 0;
    @Before
    public void before(){
        start = System.currentTimeMillis();
    }
    @After
    public void after(){
        end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
    }
  @Test
  public void test() {
    //    ExecutorService executorService = Executors.newCachedThreadPool();
    // 经过测试，有多少个消费者就至少需要n个线程，否则程序无法继续运行
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    EventFactory eventFactory = new EventFactory();
    /**
     * eventFactory这个怎么说呢？其实就是 用来产生 消息体的，也就是他构造ringBuffer的时候会使用eventFactory创建消息体对象然后放入ringBuffer数组中
     * 也就是此时虽然 生产者还未放入具体消息，但是其实 消息对象创建好了只不过我这里的data变量是null，然后当生产者需要放入消息就是拿到事先准备好的消息体 这个可以验证的，我在
     * 工厂创建消息体的时候先初始化一遍，然后在生产者拿（ringBuffer.get(next);）消息体前打印一遍 你会发现是初始值！！
     *
     * <p>注意size必须的 2的n次方，因为内部为了性能使用的 是 index & (size-1),这个就是定义ringBuffer里面数组大小 executorService指定线程池
     * ProducerType指定的是生产者的类型，两种 单生产者 多生产者 SINGLE MULTI
     */
    Disruptor<Event> disruptor =
        new Disruptor(
            eventFactory, 8, executorService, ProducerType.SINGLE, new BlockingWaitStrategy());
    /**
     * 设置消费者，可以指定n个,当生产者手动设置消息后，disruptor内部就是调用指定的消费者进行消费(就是从RingBuffer拿消息) 我测试过，当有多个消费者，具体调用哪个完全随机的
     */
    disruptor.handleEventsWithWorkerPool(new Consumer(), new Consumer2());
    /*
    启动、、可以理解为启动线程池，平时我们都是自己使用execute(Runable runable)；现在变成了他内部根据我们提供的线程池 启动它的runable类
    通过源码知道consumerInfo.start(executor);其实就是启动我们的线程池。只是可能有一个不好理解 以前我们都是 execute后就执行自己创建的线程，runable
    而现在呢 变成了  我们提供线程池，然后disruptor内部就创建任务线程runable，这个线程内部会去执行执行我们的任务，可能会纠结他如何知道创建多少个runable
    其实这个就是通过ringBufferSize指定的，因为它规定了装任务(event)的个数。所以我们一开始就是创建这个多个任务。然后任务哪里来呢？其实就是根据eventFactory
    产生的。这个东西很好验证，因为他是使用 数据体去填充 环形数组，而我们的 数据体是通过eventFactory产生的，所以我在这里面打印到控制台：
    具体就是我在调用  disruptor.start();下面一句话打一个断点，如果有如下打印就说明 disruptor内部会创建初始任务填充 环形数组!
    Event event = new Event();event.setData(0);System.out.println(event.getData());//发现就是我们的ringBufferSize多大，就是打印多少次 0。这个充分说明了
    通过验证 的确是这样，也就是说平时都是我们主动  给线程池 具体任务runable，现在交给了 disruptor，但是其实他还是根据这个弄得!
     */
    disruptor.start();
    // 下面开始发布消息
    // 获取Disruptor帮我们定义好的 RingBuffer这样 保证生产者消费者都是对同一个 RingBuffer操作(从中读写)
    RingBuffer<Event> ringBuffer = disruptor.getRingBuffer();  //这里打断点 测试 disruptor.start();
    // 让生产者使用指定的ringBuffer
    Productor productor = new Productor(ringBuffer);
    ByteBuffer allocate = ByteBuffer.allocate(8);

    for (int i = 0; i < 5; i++) {
      allocate.putLong(0, 1);
      // 通过字节流传递数据没啥说的，这样效率高。生产者生产消息本质就是往RingBuffer放入消息
      productor.onData(allocate);
    }
    disruptor.shutdown(); // 关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
    executorService.shutdown(); // 关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；
  }
  @Test
  public void test3(){
  }
}
