package com.xy;

import java.lang.reflect.Method;

/**
 * @fileName:RefrectTest
 * @author:xy
 * @date:2018/8/24
 * @description:
 */
public class RefrectTest {
    public static void main(String[] args) {
        Class<RefrectTest> refrectTestClass = RefrectTest.class;
        Method[] methods = refrectTestClass.getMethods();
        for (Method m :methods) {
            System.out.println(m.getName());
        }
    }
    public void test(){
        System.out.println("test");
    }
}
