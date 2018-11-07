package com.xy.spi;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @fileName:SpiRule
 * @author:xy
 * @date:2018/8/26
 * @description:
 */
public class SpiRule {
  // 从class path中所有Jar的META-INF目录中搜索，找到合适的类并加载。
  private static ServiceLoader<Comparator> serviceLoader = ServiceLoader.load(Comparator.class);
    @SuppressWarnings("unchecked")
    private static Comparator<String> getCompartor() {
        for(Comparator service : serviceLoader)
        {
            return (Comparator<String>)service;
        }
        return null;
    }
    public static void sortByMyRule(List list){//然后调用者 直接传递list就行，规则定死了
        Collections.sort(list, getCompartor());
    }
}
