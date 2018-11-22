package com.xy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @fileName:Main
 * @author:xy
 * @date:2018/11/21
 * @description:
 */
 @Configuration
 @ImportResource("resource/script.xml") // 加载groovybean的配置文件
public class Main {
          public static void main(String[] args) throws InterruptedException {
              AnnotationConfigApplicationContext context =
                      new AnnotationConfigApplicationContext("com.xy");
              DemoService ds = context.getBean(DemoService.class);

              System.out.println(ds.sayHello());
              Thread.sleep(10000);
              //记得修改DemoServiceImpl的内容，开始我修改错了修改了 xml文件的，我说咋不生效
              System.out.println(ds.sayHello());
              context.close();
          }

//  public static void main(String[] args) throws InterruptedException {
//    ApplicationContext context = new ClassPathXmlApplicationContext("resource/script.xml");
//    DemoService demoService = context.getBean(DemoService.class);
//    System.out.println(demoService.sayHello());
//    Thread.sleep(10000);
//    //记得修改DemoServiceImpl的内容，开始我修改错了修改了 xml文件的，我说咋不生效
//    System.out.println(demoService.sayHello());
//  }
}
