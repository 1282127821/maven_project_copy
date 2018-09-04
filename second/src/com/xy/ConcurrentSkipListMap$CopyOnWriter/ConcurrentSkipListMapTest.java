/**
 * @fileName:ConcurrentSkipListMap$CopyOnWriter
 * @author:xy
 * @date:2018/8/17
 * @description:
 */
package com.xy.ConcurrentSkipListMap$CopyOnWriter;

import java.time.Instant;

/**
 *@fileName:ConcurrentSkipListMap$CopyOnWriter
 *@author:xy
 *@date:2018/8/17
 *@description:
 */
public class ConcurrentSkipListMapTest {
    /**
     * 由于 跳表是生成多层链表，当层数越多（分的更明晰，那么查询效率越高），所以它属于  空间换时间。
     * 空间上由于 层数越来越多，而查询效率越来越快
     * 但是由于他层数，所以他不能数据量太大的并发，因为内存是有限的（相对于hash）！这就是空间代价
     */
    public static void main(String[] args) {
        System.out.println((1<<0)+(1<<1));

    }

}
