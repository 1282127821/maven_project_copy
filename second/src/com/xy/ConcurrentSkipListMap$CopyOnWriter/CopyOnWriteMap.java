/**
 * @fileName:CopyOnWriteMap
 * @author:xy
 * @date:2018/8/17
 * @description:
 */
package com.xy.ConcurrentSkipListMap$CopyOnWriter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @fileName:CopyOnWriteMap
 * @author:xy
 * @date:2018/8/17
 * @description:
 */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
  private volatile Map<K, V> internalMap;

  public CopyOnWriteMap() {
    internalMap = new HashMap<K, V>();
  }

  /**
   * put的时候new一个 map，向这里面添加数据，然后将internalMap引用指向他
   * 注意加锁肯定不是synchronize这个，按照是ReentrantLock这个，但是我这里简洁试试效果
   *
   * @param key
   * @param value
   * @return
   */
  @Override
  public V put(K key, V value) {
    synchronized (this) {
      Map<K, V> tempMap = new HashMap<>(internalMap);
      V val = tempMap.put(key, value);
      internalMap = tempMap;
      return val;
    }
  }

  @Override
  public V get(Object key) {

    return internalMap.get(key);
  }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public V remove(Object key) {
        return null;
    }
}
