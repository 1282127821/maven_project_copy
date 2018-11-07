package StopWatch计时器;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @fileName:StopWatchTest
 * @author:xy
 * @date:2018/9/11
 * @description:
 */
public class StopWatchTest {
    /**
     *getSplitTime()永远是从开始到 第几个切入点的时间split(),他必须在split()后才实际有用否则不管多少个getSplitTime()都是一样的
     */
    public static void main(String[] args) throws Exception{
        StopWatch watch=new StopWatch();
        watch.start();//开始计时
        Thread.sleep(1000);
        watch.split();//设置分割点
        System.out.println("第一个分割点"+watch.getSplitTime());//从开始到第几个分割点的时间
        Thread.sleep(1000);
        System.out.println("第一个分割点"+watch.getSplitTime());
        watch.split();
        System.out.println("第二个分割点"+watch.getSplitTime());
        watch.suspend();//暂时中断计时，只有才会继续计时,中间的耗时不进行计算
        System.out.println("暂停前"+watch.getTime());
        Thread.sleep(3000);//虽然停顿3s但是这两个时间一样
        watch.resume();
        System.out.println("暂停后"+watch.getTime());

        watch.reset();//重置,注意重置后必须重新调用start启动,
        watch.start();//这句屏蔽后无法重新计时的，因为上面重置了
        Thread.sleep(1000);
        System.out.println("重置后必须重新start才会重新计时"+watch.getTime());
        watch.stop();//停止,注意停止和 重置不同，重置后还可以再次start()，但是stop后无法再次调用start，否则报错！！！
        //下面是几个状态判断而已
        System.out.println(watch.isStarted());
        System.out.println(watch.isStopped());
        System.out.println(watch.isSuspended());

    }
}
