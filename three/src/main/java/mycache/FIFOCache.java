package mycache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @fileName:FIFOCache
 * @author:xy
 * @date:2018/11/11
 * @description:
 */

/** LinkedHashMap天生自带 FIFO 和FRU 两种。默认是FIFO */
public class FIFOCache<K, V> extends LinkedHashMap<K, V> {
  private final int SIZE;

  public FIFOCache(int size) {
    super();
    this.SIZE = size;
  }
  /*
  下面是重点。就是它，让我们 map保证只能有指定数量容量
  removeEldestEntry这个就是当为true，就移除，但是一般呢？他返回都是false，也就是不会移除任何数据，这是因为removeEldestEntry这个 返回是固定值false
   */

  @Override
  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size()>SIZE;
  }
}
