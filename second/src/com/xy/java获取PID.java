package com.xy;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * @fileName:java获取PID
 * @author:xy
 * @date:2018/9/21
 * @description:
 */
public class java获取PID {
    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        System.out.println(name.split("@")[0]);//pid
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystemMXBean.getName());//Windows 10

    }
}
