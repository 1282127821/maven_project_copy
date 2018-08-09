/**
 * @fileName:TestCache
 * @author:xy
 * @date:2018/8/3
 * @description:
 */
package com.xy.google.cache;

import com.google.common.cache.CacheBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *@fileName:TestCache
 *@author:xy
 *@date:2018/8/3
 *@description:
 */
public class TestCache {
    public static void main(String[] args) {
        /**
         * CacheBuilder支持链式编程
         * 下面的根据
         * 关于CacheBuilder有一个特点就是，他会维持内存缓存在一个界限如容量限制，时间限制。
         * 当超过界限时，他会自动回收。那么为嘛我们事后还能从CacheBuilder缓存拿到数据呢？
         * 因为他自动回收会放到本地保存。这样当你需要，我又去本地加载到内存中去。所以CacheBuilder也叫做本地缓存！
         */
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.maximumSize(1000).expireAfterAccess(1000,TimeUnit.MINUTES);
//        cacheBuilder.
    }
}
