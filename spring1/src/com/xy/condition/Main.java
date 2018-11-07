package com.xy.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @fileName:Main
 * @author:xy
 * @date:2018/9/12
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionConfig.class);
        Service service=applicationContext.getBean(Service.class);
        service.print();
    }
}
