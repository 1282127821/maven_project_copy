package com.xy.deamon;

/**
 * @fileName:DeamonTest
 * @author:xy
 * @date:2018/8/25
 * @description: 守护线程
 */
public class DeamonTest implements Runnable {
    /**
     * 使用守护线程有一个点要注意  就是t.setDaemon(true);//设置为守护线程必须在 start()方法之前设置，因为默认是以普通线程去执行run()
     * 而我们知道start后就会去执行run。就好比安倍晋三以私人身份去祭拜靖国神社，还是以首相身份，这很重要！！
     * 但是你必须先向媒体说明我的祭拜身份！否则战乱啊！
     */
  public static void main(String[] args) throws InterruptedException {
      Thread t=new Thread(new DeamonTest());
      t.setDaemon(true);//设置为守护线程
      t.start();
//      Thread.sleep(3000);
  }

  public void run() {
    while (true) {System.out.println("如果以守护线程运行，我只会运行一次，如果一般线程那么无限次");}
  }
}
