/**
 * @fileName:TestJvm
 * @author:xy
 * @date:2018/7/31
 * @description:
 */
package com.xy.jvm类加载;

import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 *@fileName:TestJvm
 *@author:xy
 *@date:2018/7/31
 *@description:
 */
public class TestJvm {
        public static void main(String[] args){
            System.out.println(ConstClass.value);
        }
}
/** 被动引用情景3
 * 常量在编译阶段会被存入调用类的常量池中，本质上并没有引用到定义常量类类，所以自然不会触发定义常量的类的初始化
 * @author root
 *
 */
class ConstClass{
    static{
        System.out.println("ConstClass init.");
    }
    public final static String value="hello";
}
