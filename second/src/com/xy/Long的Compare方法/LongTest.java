package com.xy.Long的Compare方法;

/**
 * @fileName:LongTest
 * @author:xy
 * @date:2018/9/18
 * @description:
 */
public class LongTest {
    public static void main(String[] args) {
        /**
         * Long#compare这个是 用来比较相同类型的数据前者大于后者返回 1 小于-1 等于0
         * 反正以前面为主。 其他基本类型的封装类 都一样的!
         */
        int one = Long.compare(100, 50);
        int two = Long.compare(50, 100);
        int three = Long.compare(100, 100);
        System.out.println(one);//1
        System.out.println(two);//-1
        System.out.println(three);//0



    }
}
