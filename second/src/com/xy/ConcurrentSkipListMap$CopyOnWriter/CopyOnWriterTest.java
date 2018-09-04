/**
 * @fileName:CopyOnWriterTest
 * @author:xy
 * @date:2018/8/17
 * @description:
 */
package com.xy.ConcurrentSkipListMap$CopyOnWriter;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @fileName:CopyOnWriterTest
 * @author:xy
 * @date:2018/8/17
 * @description:
 */
public class CopyOnWriterTest {
  public static void main(String[] args) {
    /**
     * CopyOnWriteArrayList CopyOnWriteArraySet 没有map我根据一篇文章，模拟一个CopyOnWriteMap
     * 首先分析CopyOnWrite特点就是：① 当触发写操作的时候 就复制一份之前的集合处理进行增加操作（不影响之前的那个集合） ②写的时候要加锁，因为防止并发 由于
     * 读被加锁，但是写没有加锁，也就是 可以在写的同时读，只不过独到的是 原来的值。 注意：
     * 内存占用问题。因为CopyOnWrite的写时复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的内存，旧的对象和新写入的对象（注意:在复制的时候只是复制容器里的引用，只是在写的时候会创建新对象添加到新容器里，而旧容器的对象还在使用，所以有两份对象内存）。如果这些对象占用的内存比较大，比如说200M左右，那么再写入100M数据进去，内存就会占用300M，那么这个时候很有可能造成频繁的Yong
     * GC和Full GC。之前我们系统中使用了一个服务由于每晚使用CopyOnWrite机制更新大对象，造成了每晚15秒的Full GC，应用响应时间也随之变长。
     *
     * <p>　　针对内存占用问题，可以通过压缩容器中的元素的方法来减少大对象的内存消耗，比如，如果元素全是10进制的数字，可以考虑把它压缩成36进制或64进制。或者不使用CopyOnWrite容器，而使用其他的并发容器，如ConcurrentHashMap。
     *
     * <p>　　数据一致性问题。CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。所以如果你希望写入的的数据，马上能读到，请不要使用CopyOnWrite容器。
     *
     *CopyOnWrite多用于 读多写少的的并发，因为写的时候内存会加大！
     *
     * 由上面两点总结出：CopyOnWrite多用于 ①读多写少的常见  ② 不是希望写入后就立马得到结果（有延迟）
     * <p>public boolean add(E e) { final ReentrantLock lock = this.lock; lock.lock(); try {
     * Object[] elements = getArray(); int len = elements.length; Object[] newElements =
     * Arrays.copyOf(elements, len + 1); newElements[len] = e; setArray(newElements); return true; }
     * finally { lock.unlock(); } }
     */
    CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
    copyOnWriteArrayList.add(1);
    System.out.println(copyOnWriteArrayList.get(0));
    System.out.println("--------------------下面是自己写的模拟一个CopyOnWriteMap-----------------------");

    CopyOnWriteMap copyOnWriteMap = new CopyOnWriteMapImpl();
    copyOnWriteMap.put("xy", 1);
    System.out.println(copyOnWriteMap.get("xy"));
  }
}
