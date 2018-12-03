package com.xy.Disruptor学习;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @fileName:Productor
 * @author:xy
 * @date:2018/11/24
 * @description:
 */
@SuppressWarnings("all")
public class Productor {
  /** 核心就是这个RingBuffer，其实生产者就是往这里面放入一个消息体，然后调用publish发送给消费者 */
  private RingBuffer<Event> ringBuffer;

  public Productor(RingBuffer<Event> ringBuffer) {
    this.ringBuffer = ringBuffer;
  }

  public void onData(ByteBuffer bb) {
      //1.可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽，就是理解为环形数组的下一个角标
    long next = ringBuffer.next();
    try {
        //获取 环形数组的某个消息体，这个 空消息体(没有具体数据的)是一开始装好了的
      Event event = ringBuffer.get(next);
//      System.out.println("初始值："+event.getData());
      event.setData(bb.getLong(0));
    } finally {
      // 注意，最后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；如果某个请求的 sequence
      // 未被提交，将会堵塞后续的发布操作或者其它的,这样的好处就是就算发生异常，消息也必须推送出去
      /**
       * 我发现如果把这个 ringBuffer.publish(next);注释掉，会导致 阻塞了，程序不会继续执行完毕(也就是shutDown不会被执行，
       * 本来他会等待所有任务执行完毕后执行的，这也是为嘛这个要放到finally执行,但是经过我的测试ProducerType.SINGLE不会出现阻塞，ProducerType.MULTI会出现
       * 主要是他们两个底层实现不一样!)
       *
       * <p>开始我以为 调用publish后就会立马随机选择我们的消费者进行消费，其实呢不是，我们知道我们在创建disruptor对象的时候，有指定策略 new
       * BlockingWaitStrategy()，其实呢还有很多策略。 然后我们调用publish(next)，此时我们将要改变的 索引号告诉了策略，
       *下面就是publish后的源码。其实就是将我们的索引号使用cursor保存起来，并且调用 策略(策略就看具体提供的是哪个)。
       * 如果按照我开始想的，也有一种策略对应BusySpinWaitStrategy，这种策略就是一旦我有 消息产生就会立马推送出去，但是这种如果在 真实项目中
       * 就很占cpu性能，因为他一直占据着cpu(死循环)。
       * 可见策略就是为了均衡 提供的不同选择方案，按照适合的选择，但是本质上也是 内部调用 消费者的onEvent()方法，这也是为嘛我们的消费者需要实现
       * WorkHandler/EventHandler 接口，正是由于  内部需要调用。
       * (说到这里我又想起华哥一句话，如果提供的接口只有一两个方法，那么这个接口基本上就是为了让外部实现它然后内部调用他--很经典的一个总结
       * 如果一个接口有很多方法，那么大部分这种接口是内部实现，也就是他只是内部方便扩展提供的接口)
       * <--public void publish(long sequence) {
       *    cursor.set(sequence);
       *    waitStrategy.signalAllWhenBlocking();
       *    }-->
       */
      ringBuffer.publish(next);
    }
  }
}
