/**
 * @fileName:TestCollects
 * @author:xy
 * @date:2018/7/25
 * @description:
 */
package com.xy.collection;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class TestCollects {
    public static void main(String[] args) {
        List<String> list=new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
        /**
         * Collections.unmodifiableList(list);是构造一个不可变的list集合，返回的就是构造后的list
         * unmodifiableList，但是不影响传递的那个list。
         * 如果我们试图unmodifiableList进行修改，那么就会报错UnsupportedOperationException
         * 按照尿性，还有map set 这里就不一一举例了。
         */
        List<String> unmodifiableList = Collections.unmodifiableList(list);
//        unmodifiableList.add("d");
        System.out.println(list);

        System.out.println("--------------------------------");
        /**
         * CollectionUtils是apache的集合工具类，专门用来做集合计算的
         * isEqualCollection判断集合元素是否相同
         * union取并集
         * intersection取交集
         * subtract取差集
         * disjunction取交集的补集
         * isEmpty、isNotEmpty 目标集合是否为空/不为空
         *
         * 注意差集，顺序不同取到的东西也不一样的。不是数学里面的，这个都是有顺序的前者里面取。
         * 其他的交并补都是数学里面的理解！
         */
        List<String> list1=new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        List<String> list2=new ArrayList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("d");
        Set<String> set1=new HashSet<String>();
        set1.add("b");
        set1.add("a");
        System.out.println( CollectionUtils.isEqualCollection(list1,list2));
        System.out.println( CollectionUtils.isEqualCollection(list1,set1));
        System.out.println( CollectionUtils.union(list1,list2));
        System.out.println( CollectionUtils.intersection(list1,list2));
        System.out.println( CollectionUtils.intersection(list2,list1));
        System.out.println( CollectionUtils.subtract(list1,list2));
        System.out.println( CollectionUtils.subtract(list2,list1));
        System.out.println( CollectionUtils.disjunction(list2,list1));
        System.out.println( CollectionUtils.disjunction(list1,list2));

        System.out.println(CollectionUtils.isEmpty(list1));
        System.out.println(CollectionUtils.isNotEmpty(list1));

        Collection unmodifiableCollection2 = CollectionUtils.unmodifiableCollection(list1);
//        unmodifiableCollection2.add("k");
    }
}
