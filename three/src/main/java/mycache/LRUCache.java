package mycache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @fileName:LRUCache
 * @author:xy
 * @date:2018/11/11
 * @description:
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int SIZE;
    public LRUCache(int size){
        /*
        LinkedHashMap的构造三个参数，第一个表示默认大小，第二个表示加载因子（这两个就是保证扩容时机），第三个表示 是否开启LRU策略
        这个策略对于链表很容易，因为每当我们命中一次map，他就会断开链表把他 头结点（因为新数据从头节点开始添加）。如果我们未命中，而是添加新数据
        那么就会直接往头结点添加，如果此时判断容量满了，那就直接删除尾节点数据。
         */
        super(size,0.75f,true);
        this.SIZE=size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>SIZE;
    }
}
