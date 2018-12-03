package com.xy.Disruptor学习;

import com.lmax.disruptor.WorkHandler;

/**
 * @fileName:Consumer
 * @author:xy
 * @date:2018/11/24
 * @description: 消费者 貌似有好几种 比如还有EventHandler
 */
public class Consumer2 implements WorkHandler {
  @Override
  public void onEvent(Object o) throws Exception {
    System.out.println("Consumer2类被调用了...");
    Event o1 = (Event) o;
    System.out.println(o1.getData());
  }
}
