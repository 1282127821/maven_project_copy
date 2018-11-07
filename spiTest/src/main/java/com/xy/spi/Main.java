package com.xy.spi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @fileName:Main
 * @author:xy
 * @date:2018/8/26
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("xx");
        list.add("zz");
        list.add("yy");
        for (String l: list) {
            System.out.println("排序前："+l);
        }
        SpiRule.sortByMyRule(list);
        for (String l: list) {
            System.out.println("排序后："+l);
        }
    }
}
