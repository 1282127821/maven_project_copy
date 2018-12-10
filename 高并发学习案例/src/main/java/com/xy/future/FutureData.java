package com.xy.future;

/**
 * @fileName:FutureData
 * @author:xy
 * @date:2018/12/2
 * @description:
 */
@SuppressWarnings("all")
public class FutureData implements Data {
  private RealData realData = null;
  private boolean isReady=false;

    /**
     *这里说一下为嘛需要isReady 是否已经准备好了数据，因为多线程 不确定哪个先执行(setRealData和getData)，如果先执行setRealData
     * 那么就不能wait，因为如果getData后执行 还wait，那么没法唤醒他(因为notifyAll已经执行过)
     * 但是如果是getData先执行，肯定没有数据，此时就必须等待，等待setRealData执行后就需要唤醒他了--因为要得到数据。
     *
     */
    public synchronized void setRealData(RealData realData) {
      if (isReady){//数据准备好了，不需要重复准备
            return;
      }
      this.realData=realData;//能到这里表示 数据第一次准备，准备好后 设置isReady为已准备，且唤醒waait
        isReady=true;
        notifyAll();
    }
    @Override
    public synchronized String getData() {//由于多线程 使用到了全局变量 且写操作(改变isReady的值)，所以需要锁
      while (isReady==false){//数据没有准备好，那就等待，因为调用这个方法 表示必须获取到实际数据
          try {
              wait();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }

        return realData.getData();
    }
}
