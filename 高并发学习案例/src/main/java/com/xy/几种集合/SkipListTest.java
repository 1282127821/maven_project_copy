package com.xy.几种集合;

import java.util.DuplicateFormatFlagsException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @fileName:SkipListTest
 * @author:xy
 * @date:2018/9/23
 * @description:
 */
public class SkipListTest {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap<>();
        map.put(2, 2000);
        map.put(1, 1000);
        map.put(3, 3000);
        for (Map.Entry<Integer,Integer> entry: map.entrySet()) {
            Integer key = entry.getKey();
            System.out.println(map.get(key));// 1000  2000  3000 说明跳表是根据 key排序的
        }
//        LinkedBlockingQueue
    }
}
