package com.xy.future;

import org.junit.Test;

import java.util.concurrent.Future;

/**
 * @fileName:FutureDemo
 * @author:xy
 * @date:2018/12/2
 * @description:future本质
 */
@SuppressWarnings("all")
public class FutureDemo {
  Client client = new Client();

    /**
     * 这里注意一下，假数据和真实数据必须都实现同一个 获取数据的接口，因为我们客户端操作的是 假数据类，最终我们也是使用该对象
     * 获取实际值的(getData)，如果他两没有同一个获取数据的接口，那么最终无法获取假数据。这一点很重要
     * 然后就是要注意  futureData类 其实包含真实数据RealData 的对象
     */
  @Test
  public void test() throws InterruptedException {
    Data data = client.request("hello"); //一开始拿到的数据是 假数据
    System.out.println("操作完毕!");
    Thread.sleep(10000);//模拟当理解返回后进行其他业务操作
    System.out.println(data.getData());//最终必须拿到真实数据，所以如果拿不到必须等
  }
}
