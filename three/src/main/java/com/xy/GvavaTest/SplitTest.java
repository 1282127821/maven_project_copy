package com.xy.GvavaTest;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;

/**
 * @fileName:SplitTest
 * @author:xy
 * @date:2018/8/30
 * @description:
 */
public class SplitTest {
   @Test
    public void test1(){
      /**
     * 注意分割器支持链式操作！！！（非）表示非静态方法，需要依赖其他分割器，本身该方法不具备分割能力 fixedLength 就是返回一个固定的 分割器，按照固定长度进行分割 表示为
     * 每【长度】为一组 (非)limit 这个是非静态方法，也就是 他依赖其他 分割器，主要就是限制分割器最多分割为多少段 123456
     * （如果limit=1）那么就是最多分割为1段，其实就是不进行分割，因为它规定原先的最多分割为1段 如果你分割了就成了2段（一刀两断嘛） on 这个就是按照 正则表达式或者
     * 具体字符进行分割，没啥好讲 onPattern 和on类似只不过他只是用正则表达式 不常用 （非）trimResults 很常用 他构建的 分割器会 自动除掉
     * 开头结尾的字符串，注意中间的不能除掉 他本身不具备分割能力，只是用来去掉[分割后]的 首尾空白.注意是分割后的首尾 " 12 34 567 "（按照2个一组分割）-->[1, 2,
     * 34, 5, 67, ] 之所以第一个是 1 因为 " 1",然后去掉首尾空格
     * Splitter trimResults(CharMatcher trimmer)
     * 返回分离器的行为等同于该分离器，但会删除所有开头或结尾的字符匹配每一个给定的CharMatcher返回字符串。
     * 用法和不带参数类似，只不过是去掉【首尾】的指定字符
     * withKeyValueSeparator  这一种是将分割器分割后每一部分 的分别分成2截，刚好每一个就对应key-value结构
     * 但是注意如果分成2截以上或不能分割为2截就报错，因为不符合key-value结构
     * Splitter.on(" ").withKeyValueSeparator("=").split("userName=Nimo phone=123 address=浙江省杭州市滨江区XXXX");
     *on按照 " " 首先分割为这种格式： userName=Nimo, phone=123, address=浙江省杭州市滨江区XXXX
     * 然后withKeyValueSeparator 就按照 "=" 分割为并封装为map
     * 如果 on分割出现 userName=Nimo=123  或者  userName
     * 那么就无法分割转换为map因为 不符合key-value
     *
     */
    Splitter length = Splitter.fixedLength(5);
       List<String> list = length.splitToList("123456789");
       System.out.println("length:"+list);
       Splitter limit = Splitter.fixedLength(3).limit(2);
       List<String> list2 = limit.splitToList("123456789");
       System.out.println("limit:"+list2);
       Splitter on = Splitter.on("|");
       List<String> list1 = on.splitToList("a|123|xy");
       System.out.println("on:"+list1);
       List<String> list3 = Splitter.fixedLength(2).trimResults().splitToList(" 12 34 567 ");
       System.out.println(list3);

       Map<String,String> map = Splitter.on(" ").withKeyValueSeparator("=").split("userName=Nimo phone=123 address=浙江省杭州市滨江区XXXX");
//       Map<String,String> map = Splitter.on(" ").withKeyValueSeparator("=").split("userNameNimo=123");
       System.out.println(map.toString());
   }
}
