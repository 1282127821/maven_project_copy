package mycache;

import java.util.Collections;
import java.util.HashMap;

/**
 * @fileName:LFUCache
 * @author:xy
 * @date:2018/11/12
 * @description:
 */
@SuppressWarnings("all")
public class LFUCache {
  // 这里有一个前提就是treeMapkey和map 是一一对应的
  private HashMap<Object, Data> map = new HashMap<>(); // 其实这里不用treemap只是我开始想法错误，懒得改
  private final int SIZE;

  static class Data implements Comparable<Data> {
    Object key; // ley
    Long times; // value
    int hitCount; // 命中次数

    public Data(Object key, Long times, int hitCount) {
      this.key = key;
      this.times = times;
      this.hitCount = hitCount;
    }

    public void incrCount() {
      this.hitCount += 1;
    }

      public void setTimes(Long times) {
          this.times = times;
      }

      public Object getKey() {
          return key;
      }

      @Override
    public int compareTo(Data o) {//很重要这一点
      if (this.hitCount == o.hitCount) {//相同使用时间戳
        return this.times.compareTo(o.times);
      }
      return this.hitCount - o.hitCount;//不相同直接使用命中率比较
    }

//    @Override
//    public int hashCode() {
//      return (int) key;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//      return this.key == ((Data) obj).key ? true : false;
//    }
  }

  public LFUCache(int size) {
    this.SIZE = size;
  }

  public Object getCache(Object key) {
    if (key == null) return null;
    //     命中缓存，计数器 +1，且返回 value
    Data data = map.get(key);
    data.incrCount();
    data.setTimes(System.nanoTime());
    return map.get(data);
  }

  public void putCache(Object key, Object value) {
    if (map.get(key) != null) { // 说明本身key存在，那么就命中+1，替换结果
      map.get(key).incrCount();
      map.get(key).setTimes(System.nanoTime()); // 命中后，修改最后一次命中时间

    } else { // 不存在，判断是否达到了容量上限
      if (map.size() >= SIZE) {
          removeLeast();// 移除使用最少的
      }
      Data data = new Data(key, System.nanoTime(), 1);
      map.put(key, data);
    }
  }

  private Object removeLeast() {
      Data min = Collections.min(map.values());
      map.remove(min.key);// 移除排名最后，换句话就是 hitCount最小的那个,当然还有时间戳，当都是itCount都是一样的，移除时间最久的
      return min.key;
  }

    public HashMap<Object, Data> getMap() {
        return map;
    }

    public void setMap(HashMap<Object, Data> map) {
        this.map = map;
    }
}
