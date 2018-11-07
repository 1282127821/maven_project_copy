package com.xy.分而治之;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @fileName:CollectionsToolTest
 * @author:xy
 * @date:2018/9/23
 * @description:
 */
public class CollectionsToolTest {
    public static void main(String[] args) {
        /*
            在Collections中 很容易将 普通的 集合转换为 线程安全的集合 如：Collections.synchronizedMap
            规律 就是都有一个synchronizedXxx  其实就是这些方法里面都使用了 synchronized
            public V get(Object key) { synchronized (mutex) {return m.get(key);}}
            其他的集合类似
         */
        Map<Object, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        Set<Object> set = Collections.synchronizedSet(new HashSet<>());

    }
}
