package mycache;

import org.junit.Test;

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
    lfuCache.putCache(1, "1第二一命中");
    lfuCache.putCache(3, "3第二一命中");
    lfuCache.putCache(10, "10第二一命中");
    lfuCache.getCache(1);
    lfuCache.getCache(10);
    lfuCache.putCache("xy", "xxxxx");
    HashMap<Object, LFUCache.Data> map = lfuCache.getMap();
    for (Object ob : map.keySet()) {
      System.out.println(((LFUCache.Data) map.get(ob)).getKey());
    }
  }

  @Test
  public void test3() {
    TreeMap map = new TreeMap();
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
  public void test5() {
    TestTreeMapObj t = new TestTreeMapObj();
    for (int i = 1; i < 10; i++) {
      t.put(i, i);
    }
    t.put(0, 2222222);
    TestTreeMapObj.Inner o = (TestTreeMapObj.Inner) t.getMap().firstKey();
    TestTreeMapObj.Inner o1 = (TestTreeMapObj.Inner) o;
    System.out.println(o1.getId());
    System.out.println(t.getMap().get(o1));
  }
}
