package com.xy.hello;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @fileName:Main
 * @author:xy
 * @date:2018/8/21
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        System.out.println(helloWorld.getName());
    }
}
