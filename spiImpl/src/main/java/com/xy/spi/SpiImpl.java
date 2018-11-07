package com.xy.spi;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @fileName:SpiImpl
 * @author:xy
 * @date:2018/8/26
 * @description:
 */
public class SpiImpl implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);//排序
    }
}
