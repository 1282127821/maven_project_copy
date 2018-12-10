package com.xy.future;

/**
 * @fileName:Client
 * @author:xy
 * @date:2018/12/2
 * @description:
 */
@SuppressWarnings("all")
public class Client {
    public Data request(String str){
        FutureData futureData=new FutureData();
        new Thread(()->{//分一个线程 处理耗时操作
            RealData realData=new RealData(str);//这里要延迟一会才能构造完毕
            futureData.setRealData(realData);//构造完毕后就set赋值
        }).start();
        return futureData;
    }
}
