package com.xy.GvavaTest;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @fileName:OrderingTest
 * @author:xy
 * @date:2018/8/21
 * @description:
 */
public class OrderingTest {
  public static void main(String[] args) {
    List<Integer> numbers = new ArrayList<Integer>();
    numbers.add(new Integer(5));
    numbers.add(new Integer(2));
    numbers.add(new Integer(15));
    numbers.add(new Integer(51));
    numbers.add(new Integer(53));
    numbers.add(new Integer(35));
    numbers.add(new Integer(45));
    numbers.add(new Integer(32));
    numbers.add(new Integer(43));
    numbers.add(new Integer(16));
    //    numbers.add(null);//会报错
    System.out.println("起始排序：" + numbers);
    Ordering<Comparable> ordering = Ordering.natural();
    Collections.sort(numbers, ordering);
    System.out.println("自然排序：" + numbers);
    Collections.sort(numbers, ordering.reverse());
    System.out.println("取反排序：" + numbers);
    numbers.add(null);
    Collections.sort(numbers, ordering.nullsFirst());
    System.out.println("null排序：" + numbers);
    /**
     * 其实Ordering 只是定义规则，该怎么排序，他其实实现的是 Comparator(以前我们自己写) 最终还是使用Collections.sort(numbers,
     * natural);进行排序 一般来说集合里面有null就不能够 打印了，因为打印的时候会利用该元素调用方法，会导致空指针异常
     * 但是如果我们排序使用了nullsFirst/nullsLast 也就是null值放在开头/结尾 这时候就会对null特殊处理
     * 注意ordering支持链式编程，注意看返回值是否为ordering是说明支持 usingToString是根据编码表顺序值比较的.不深究毕竟用的少，很少有对字符串排序的，理解为字典顺序
     *
     * <p>常见的静态方法：
     *
     * <p>　　natural()：使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序; 　　usingToString()
     * ：使用toString()返回的字符串按字典顺序进行排序； 　　arbitrary() ：返回一个所有对象的任意顺序， 即compare(a, b) == 0 就是 a == b
     * (identity equality)。 本身的排序是没有任何含义， 但是在VM的生命周期是一个常量。
     */
    ArrayList<Object> list = new ArrayList<>();
    list.add("a");
    list.add("c");
    list.add("b");
    list.add("e");
    list.add("c");
    list.add("2");
    list.add("日本");
    list.add("龍");
    list.add("中国");
    list.add("1");
    Ordering<Object> usingToString = Ordering.usingToString();
    Collections.sort(list, usingToString);
    System.out.println(list);

      Ordering<Object> arbitrary = Ordering.arbitrary();
  }
}
