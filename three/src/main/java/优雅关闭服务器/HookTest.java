package 优雅关闭服务器;


import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;

/**
 * @fileName:优雅关闭游戏服务器
 * @author:xy
 * @date:2018/8/31
 * @description:
 */
public class HookTest {
    public static void main(String[] args) throws IOException {
        /**
         * 注意钩子只有 正常关闭才会被触发 kill -9，直接点击IDE的红色关闭以及任务管理器 都算直接强制杀死
         * 其实他们三个都是相当于执行 kill -9 pid
         */
        MyThread myThread=new MyThread();
        Thread t1=new Thread(myThread);
        Thread t2=new Thread(myThread);
        Thread t3=new Thread(myThread);
        //注册钩子
        Runtime.getRuntime().addShutdownHook(t1);
        Runtime.getRuntime().addShutdownHook(t2);
        Runtime.getRuntime().addShutdownHook(t3);
        //移除钩子（所以关闭虚拟机的时候不会触发钩子指定的线程）
        Runtime.getRuntime().removeShutdownHook(t3);
        System.out.println("按下enter的时候关闭虚拟机！");
        if (SystemUtils.IS_OS_WINDOWS_10){
            int read = System.in.read();
            if(read==13){
                System.exit(0);/**退出虚拟机，这时候触发钩子，钩子就会执行他的线程*/
            }
        }
    }
}
class MyThread implements  Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
