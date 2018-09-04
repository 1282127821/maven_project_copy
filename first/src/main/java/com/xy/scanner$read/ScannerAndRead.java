package com.xy.scanner$read;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @fileName:ScannerAndRead
 * @author:xy
 * @date:2018/8/24
 * @description:
 */
public class ScannerAndRead {
    public static void main(String[] args) throws IOException {
//        Scanner scanner=new Scanner(System.in);
//        String s = scanner.nextLine();
//        System.out.println(s+":验证打印了");
//        int i = scanner.nextInt();
//        System.out.println(i+":验证打印了");
//        int read = System.in.read();
//        System.out.println(read+"：验证打印了");
        /**
         * 下面总结下 lang3 包SystemUtils ，这个包就是和系统属性相关的封装
         * 如 jdk路径 版本  电脑系统  名字取得好根据名字就知道啦
         */
        File javaHome = SystemUtils.getJavaHome();
        System.out.println("jre路径:"+javaHome);
        boolean isJava17 = SystemUtils.IS_JAVA_1_7;
        System.out.println("jdk版本是否是1.7:"+isJava17);
        String fileEncoding = SystemUtils.FILE_ENCODING;
        System.out.println("本文件的编码:"+fileEncoding);
        boolean isOsWindows = SystemUtils.IS_OS_WINDOWS;
        System.out.println("电脑系统:"+isOsWindows);


    }
}
