package com.xy.并行流和串行流;

import java.time.Instant;
import java.util.ArrayList;
import java.util.stream.BaseStream;
import java.util.stream.LongStream;

/**
 * @fileName:Parallel_Serial
 * @author:xy
 * @date:2018/8/20
 * @description:
 */
public class Parallel_Serial {
  public static void main(String[] args) {
    /**
     * 其实lambda 的并行流串行流很简单， stream()返回的就是串行流， parallelStream()就是并行流，使用基本和stream()一样 注意
     * 如果我们使用静态方法去操作并行流串行流 就是使用parallel()方法和sequential()串行化
     * 当然这只是针对 long int double  。如果是stream()流只需要parallelStream就是并行流
     * 而且经过测试 parallel并行 只有数据量大才会体现出 效率高 否则sequential效率好
     * 大致上我测试是  1 亿 是分割门槛  之前 串行流快，之后并行流快
     * 1千万   并行流   23     串行流  7ms
     * 1亿     并行流 39ms   串行流 43ms
     */
    LongStream longStream = LongStream.range(1, 10);
    long sum = longStream.sum();
    System.out.println(sum);

    long start = System.currentTimeMillis();
    LongStream.range(1, 10000000).parallel().sum();
//        LongStream.range(1,10000000).sequential().sum();
    long end = System.currentTimeMillis();
    System.out.println("耗时：" + (end - start) + "ms");
  }
}
