package com.xy.future.jdk_Future;

import java.util.concurrent.Callable;

/**
 * @fileName:MyRealData
 * @author:xy
 * @date:2018/12/2
 * @description:
 */
public class MyRealData implements Callable<String> {
    @Override
    public String call() throws Exception {
    for (int i = 0; i < 1000; i++) {
      System.out.println("真实数据运行中..."+i);

    }
        return "延迟后的真实数据";
    }
}
