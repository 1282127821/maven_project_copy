/**
 * @fileName:Enum常用方法
 * @author:xy
 * @date:2018/8/15
 * @description:
 */
package com.xy.enumSet;

import java.util.Arrays;

/**
 *@fileName:Enum常用方法
 *@author:xy
 *@date:2018/8/15
 *@description:
 */
public class Enum常用方法 {
    public static void main(String[] args) {
        /**EnumClass.values()是把枚举所有的值封装到数组中
         *EnumClass.valueOf(EnumClass.ONE.name());这个valueOf就是根据名称获取枚举，其实没卵用那个
         * EnumClass.ONE.name()是获取枚举名称对应的名称
         *EnumClass.TWO.ordinal()是获取对应枚举值的序号
         */
        EnumClass[] values = EnumClass.values();
        System.out.println(Arrays.toString(values));
        EnumClass enumClass = EnumClass.valueOf(EnumClass.ONE.name());
        System.out.println(enumClass);
        System.out.println(EnumClass.TWO.ordinal());


    }
}
enum EnumClass {
    /** 1 */
    ONE,
    /** 2 */
    TWO,
    /** 3 */
    THREE
}
