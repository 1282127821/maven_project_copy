package mycache;

import org.junit.Test;

import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * @fileName:CacheTest
 * @author:xy
 * @date:2018/11/11
 * @description:
 */
@SuppressWarnings("all")
public class CacheTest {
  // 大前提，命中缓存，其实主要说的是  命中key
  @Test
  public void test() {
    //      起始数据:{1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=10}
    //      命中一次缓存:{1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=10}
    //      添加新数据:{2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=10, 11=11}
    FIFOCache<Integer, Integer> fifoCache = new FIFOCache<>(10);
    for (int i = 1; i <= 10; i++) {
      fifoCache.put(i, i);
    }
    System.out.println("起始数据:" + fifoCache.toString());
    fifoCache.get(7); // 命中一次缓存
    System.out.println("命中一次缓存:" + fifoCache.toString());
    fifoCache.put(11, 11); // 添加一个新数据，而不是命中缓存
    System.out.println("添加新数据:" + fifoCache.toString());
  }

  @Test
  public void test1() {
    //      起始数据:{1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=10}
    //      命中一次缓存:{1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 8=8, 9=9, 10=10, 7=7}
    //      覆盖数据:{1=1, 2=2, 4=4, 5=5, 6=6, 8=8, 9=9, 10=10, 7=7, 3=333}
    //      添加新数据:{2=2, 4=4, 5=5, 6=6, 8=8, 9=9, 10=10, 7=7, 3=333, 11=11}
    /**
     * public V get(Object key) { Node<K,V> e; if ((e = getNode(hash(key), key)) == null) return
     * null; if (accessOrder) afterNodeAccess(e); return e.value; } 看见没
     * get的源码，就是判断accessOrder，这时候就会进行节点移动
     */
    LRUCache<Integer, Integer> fifoCache = new LRUCache<>(10);
    for (int i = 1; i <= 10; i++) {
      fifoCache.put(i, i);
    }
    System.out.println("起始数据:" + fifoCache.toString());
    fifoCache.get(7); // 命中一次缓存
    System.out.println("命中一次缓存:" + fifoCache.toString());
    fifoCache.put(3, 333); // 覆盖数据，覆盖数据，也算是命中，因为命中是相对于 key来说
    System.out.println("覆盖数据:" + fifoCache.toString());
    fifoCache.put(11, 11); // 添加一个新数据，而不是命中缓存
    System.out.println("添加新数据:" + fifoCache.toString());
  }

  @Test
  public void test2() {
    LFUCache lfuCache = new LFUCache(10);
    for (int i = 1; i <= 10; i++) {
      lfuCache.putCache(i, i);
    }
      TreeMap<LFUCache.Data, Object> map = lfuCache.getMap();
      Object cache = lfuCache.getCache(5);
    System.out.println(cache + "///为什么是null");
    TreeMap<Object, LFUCache.Data> treeMapkey = lfuCache.getTreeMapkey();
    System.out.println(map.get(treeMapkey.get(3)));
      //    System.out.println(lfuCache.getTreeMap().get(5).hitCount);//命中次数
    TreeMap<LFUCache.Data, Object> cacheMap = lfuCache.getMap();
    cacheMap
        .values()
        .forEach(
            (t) -> {
              System.out.print(t + "-");
            });
    System.out.println();
    lfuCache.getCache(5);
    TreeMap<LFUCache.Data, Object> cacheMap2 = lfuCache.getMap();
    cacheMap2
        .values()
        .forEach(
            (t) -> {
              System.out.print(t + "-");
            });
  }

  @Test
  public void test3() {
    HashMap map = new HashMap();
    for (int i = 0; i < 5; i++) {
        TestHash testHash = new TestHash(i, i + "");
        map.put(testHash, i);
    }
    Set set = map.keySet();
    for (Object o : set) {
      System.out.println(o);
    }
    System.out.println("完毕");
  }

  @Test
  public void test4() {
      LFUCache lfuCache = new LFUCache(10);
      TreeMap<LFUCache.Data, Object> map = lfuCache.getMap();
      LFUCache.Data data = new LFUCache.Data(1, 1, 1);
      map.put(data,1 );
    System.out.println(map.get(data));
  }
}
