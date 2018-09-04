package com.xy;

import java.util.concurrent.*;

/**
 * @fileName:ScheduleTask
 * @author:xy
 * @date:2018/8/23
 * @description:
 */
public class ScheduleTask {
  public static void main(String[] args) throws InterruptedException {
      Runnable runnable = new Runnable() {
          @Override
          public void run() {
            System.out.println(System.currentTimeMillis());
          }
      };
      ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
      ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
//      ScheduledFuture<?> scheduledFuture = scheduledExecutorService.schedule(runnable, 5, TimeUnit.SECONDS);
      Thread.sleep(6000);
      System.out.println(scheduledFuture.isDone());
  }
}
