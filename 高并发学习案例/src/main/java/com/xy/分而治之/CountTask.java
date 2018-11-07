package com.xy.分而治之;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @fileName:CountTask
 * @author:xy
 * @date:2018/9/23
 * @description:
 */
public class CountTask extends RecursiveTask<Long> {
  private static final long TOTLE = 1000L;
  private long start;
  private long end;

  public CountTask(long start, long end) {
    this.start = start;
    this.end = end;
  }
/**
    分而治之 其实本质就是递归，不停调用compute 首先一个submit()将 大任务 提交 会调用compute()
    然后每次fork()就是小任务 调用compute()。 join()就是 将每次 小任务聚合在一起，最终组成一个大任务
 但是注意一定不要把任务分的过细，因为 递归可能会导致 OOM
 下面是我设置虚拟机参数 -Xms1m -Xmx2m   初始设置为CountTask countTask = new CountTask(0L, 200000L)导致如下：
 Exception in thread "ForkJoinPool-1-worker-14" java.lang.OutOfMemoryError: Java heap space
 */
  @Override
  protected Long compute() {
    long sum = 0;
    boolean canCompute = (end - start) < TOTLE; // 如果小于TOTLE就直接完成不用分拆
    if (canCompute) {
      for (long i = start; i <= end; i++) {
        sum += i;
      }
    }else {//需要拆分
        long step=(start+end)/100;//每次分成100份
        ArrayList<CountTask> subTasks=new ArrayList();
        long pos=start;
      for (int i = 0; i < 100; i++ ) {
            //注意既然分成100分那么计算是 0-20 21-41 42-62 63-83...
            long lastOne=pos+step;
            if (lastOne>end)lastOne=end;//防止在最后一次超标
          CountTask subTask=new CountTask(pos, lastOne);
          pos+=step+1;//下一次的起始位置
          subTasks.add(subTask);//这个list是记录每一次 分为了多少个任务的集合，因为子任务还可能被拆分
          subTask.fork();//这个就是执行子任务 他也会调用compute方法的
        }
        for (CountTask countTask : subTasks) {
            sum += countTask.join();
        }
    }
    return sum;
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    CountTask countTask = new CountTask(0L, 2000L);
    // submit后内部会调用countTask对象重写的compute()
    ForkJoinTask<Long> task = forkJoinPool.submit(countTask);
    Long result = task.get();
    System.out.println(result);
  }
  /*
  RecursiveTask 表示有返回值的任务    RecursiveAction 表示无返回值的行为  怎么理解为 有任务在身表示需要返回
   */
}
