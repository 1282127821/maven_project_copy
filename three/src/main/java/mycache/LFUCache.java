package mycache;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @fileName:LFUCache
 * @author:xy
 * @date:2018/11/12
 * @description:
 */
@SuppressWarnings("all")
public class LFUCache {
  // 这里有一个前提就是treeMapkey和map 是一一对应的
  private TreeMap<Object, Data> treeMapkey = new TreeMap<>(); // 其实这里不用treemap只是我开始想法错误，懒得改
  private final TreeMap<Data, Object> map = new TreeMap<Data, Object>();
  private final int SIZE;

  static class Data implements Comparable<Data> {
    Object key; // ley
    Object value; // value
    int hitCount; // 命中次数

    public Data(Object key, Object value, int hitCount) {
      this.key = key;
      this.value = value;
      this.hitCount = hitCount;
    }

    public void incrCount() {
      this.hitCount += 1;
    }

    public void setValue(Object value) {
      this.value = value;
    }

    @Override
    public int compareTo(Data o) {
      return this.hitCount >= o.hitCount ? 1 : -1;
    }
  }

  public LFUCache(int size) {
    this.SIZE = size;
  }

  public Object getCache(Object key) {
    if (key == null) return null;
    //     命中缓存，计数器 +1，且返回 value
    Data data = treeMapkey.get(key);
    data.incrCount();
    System.out.println(map.get(data));
    return map.get(data);
  }

  public void putCache(Object key, Object value) {
    if (treeMapkey.get(key) != null) { // 说明本身key存在，那么就命中+1，替换结果
      treeMapkey.get(key).incrCount();
      treeMapkey.get(key).setValue(null); // 这个应该可以不要，先这样吧

    } else { // 不存在，判断是否达到了容量上限
      if (treeMapkey.size() >= SIZE) {
        removeLeast(); // 移除使用最少的
      }
      Data data = new Data(key, value, 1);
        treeMapkey.put(key, data);
        map.put(data, value);
      System.out.println(map.get(data));
    }
  }

  private void removeLeast() {
    //      Object key = treeMapkey.lastKey();
    //      treeMapkey.remove(key); // 移除排名最后，换句话就是 hitCount最小的那个
    //      map.remove(treeMapkey.get(key));
  }

  public TreeMap<Data, Object> getMap() {
    return map;
  }

  //  public void setMap(TreeMap<Data, Object> map) {
  //    this.map = map;
  //  }

  public TreeMap<Object, Data> getTreeMapkey() {
    return treeMapkey;
  }

  public void setTreeMapkey(TreeMap<Object, Data> treeMapkey) {
    this.treeMapkey = treeMapkey;
  }
}
