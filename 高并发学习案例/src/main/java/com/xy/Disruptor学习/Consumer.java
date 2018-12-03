package com.xy.Disruptor学习;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @fileName:Consumer
 * @author:xy
 * @date:2018/11/24
 * @description: 消费者 貌似有好几种 比如还有EventHandler
 */

/**
 * 多消费者 多消费者的情况分为两类：
 *
 * 广播：对于多个消费者，每条信息会达到所有的消费者，被多次处理，一般每个消费者业务逻辑不通，用于同一个消息的不同业务逻辑处理--实现WorkHandler
 * 分组：对于同一组内的多个消费者，每条信息只会被组内一个消费者处理，每个消费者业务逻辑一般相同，用于多消费者并发处理一组消息--实现EventHandler
 * 可以理解为 activemq的 点对点通讯(把它们分到一个组)  发布订阅通讯（广播）
 */
public class Consumer implements WorkHandler { // 这里使用的是广播
  @Override
  public void onEvent(Object o) throws Exception {
    System.out.println("Consumer类被调用了...");
    Event o1 = (Event) o;
    System.out.println(o1.getData());
  }
}
