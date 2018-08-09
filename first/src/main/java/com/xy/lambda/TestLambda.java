/**
 * @fileName:TestLambda
 * @author:xy
 * @date:2018/8/8
 * @description:
 */
package com.xy.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *@fileName:TestLambda
 *@author:xy
 *@date:2018/8/8
 *@description:
 */
public class TestLambda{
public static void main(String[] args) {
    /**
     * 主要是测试stream流的map作用，开始我一直以为是用来转换成map，其实这里取的意思是一一映射，map本来就是key-value结构
     * 比如我这里就是把之前list每一项都替换为原值+(*^▽^*)  然后将他们转换为set集合
     */
    List<String> list =new ArrayList<String>();
    list.add("a");
    list.add("b");
    list.add("c");
    Collection<Object> collect = list.stream().map(m -> m + "(*^▽^*)").collect(Collectors.toSet());
    System.out.println(collect);

}
}
