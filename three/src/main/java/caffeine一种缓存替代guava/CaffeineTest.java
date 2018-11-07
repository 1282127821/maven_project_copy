package caffeine一种缓存替代guava;

import com.github.benmanes.caffeine.cache.*;
import com.google.common.graph.Graph;
import javafx.scene.chart.XYChart;
import org.junit.Test;

import java.security.Key;
import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @fileName:CaffeineTest
 * @author:xy
 * @date:2018/9/18
 * @description:
 */
public class CaffeineTest {
    /**
     * 感觉这玩意有点类似 CacheBuilder 不过我也快忘了！基本一模一样不过这个性能更好！
     *Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).maximumSize(5).build();就是创建一个缓存对象
     * 设置多久没有写 就过期，最多5个缓存。
     */
    @Test
    public void test1(){//手动加载
        Cache<Object, Object> cache = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).maximumSize(5).build();
        //根据key获取 值，如果存在返回该值，否则返回null  present这个是存在!
        Object xy = cache.getIfPresent("xy");
        System.out.println(xy);
        //放入到缓存，类似map，重复则覆盖
        cache.put("xx", "value");
        System.out.println(cache.getIfPresent("xx"));
        //由于缓存就是key-value格式，所以这里提供了一个asMap转换为map
        ConcurrentMap<Object, Object> objectObjectConcurrentMap = cache.asMap();
        System.out.println(objectObjectConcurrentMap);
        //清除某个key的缓存
        cache.invalidate("xx");
        System.out.println(cache.getIfPresent("xx"));
        //get是支持一个 lambda的，他会把lambda的结果返回放到缓存中，返回的是null则value就是null
        /**
         * 注意：如果调用该方法返回NULL（如上面的 createExpensiveGraph 方法），则cache.get返回null，如果调用
         * 该方法抛出异常，则get方法也会抛出异常。
         * 注意这个lambda 的形参其实传递的是 key。通过跟LocalAsyncLoadingCache#get发现的，具体不多说lambda就这么用
         *
         */
        cache.get("yy", (k)->{return k+"(*^▽^*)";});
        System.out.println(cache.getIfPresent("yy"));

    }
    @Test
    public void test2(){//同步加载
        LoadingCache<Object, String> cache = Caffeine.newBuilder().maximumSize(5).expireAfterWrite(10, TimeUnit.SECONDS).build((k) -> {
            return k + "(#^.^#)";
        });
        System.out.println(cache.get("suibian"));
        cache.put("key", "value");
        System.out.println(cache.get("key"));

    }
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        AsyncLoadingCache<Object, String> async = Caffeine.newBuilder().maximumSize(5).expireAfterWrite(15, TimeUnit.SECONDS).buildAsync((k) -> {
            Thread.sleep(5000);
            System.out.println("这里后执行，因为是异步");
            return k+"^_^";
        });
        System.out.println("这里会先执行完毕的，因为异步");
        CompletableFuture<String> completableFuture = async.get("xxx");
        String str = completableFuture.get();
        System.out.println(str+">>>>这里一定会等待，因为get没有就需要使用到默认值的");
        CompletableFuture<String> yyy = async.get("yyy");
        System.out.println(yyy.get()+">>>由于yyy这个key也不存在 所以这里也需要等待");
        //本来缓存对象是一个异步缓存对象，现在转换为同步的
        LoadingCache<Object, String> synchronous = async.synchronous();



    }
    @Test
    public void test4(){
        /**
         * weigher 是一个函数接口，里面的函数就是格局我们的key-value算出 权重大小。
         */
        Cache<Object, Object> cache = Caffeine.newBuilder().maximumWeight(1000).weigher((k,v)->{return 9999;}).build();
        cache.put("xx", "123");
        cache.put("xy", "456");
        System.out.println(cache.getIfPresent("xy"));
        System.out.println(cache.getIfPresent("xx"));
    }
    @Test
    public void test5(){
        //这个是监听器   RemovalListener可以获取到key、value和RemovalCause（删除的原因）
        Cache graphs = Caffeine.newBuilder()
                .removalListener((key,value, cause) ->
                        System.out.printf("(Key %s Value %s)was removed (%s)%n", key,value, cause))
                .build();
        graphs.put("xy", "v");
        graphs.invalidate("xy");

    }
    @Test
    public void test6() throws InterruptedException {
        /*
        refreshAfterWrite(自动刷新缓存) 发现一个很奇怪的问题，如下特点
        这个load是异步的
        如果该key一开始存在，那么当我们第一次调用get的时候只是触发 该refreshAfterWrite周期工作
        如果不存在 那么 当我们第一次调用get的时候就会把返回值作为该key的value，但是注意第一次不会触发周期工作
        第二次get才会触发load方法周期性工作
        总结：
        只有当我们的key存在且调用过get后才会触发refreshAfterWrite开始周期性计时，不存在第一次不会触发的


        貌似第一次是 触发 条件 然后以后如果满足条件就 把保存新值(假设全局变量 newValue) 返回旧值(我们看到的结果)
        如果不满足添加那么就拿上一次的 新值作为返回值(注意此时 新值newValue还是之前的那个 未变动)
        一旦不满足又是重新来，从不满足那里
        经过我测试貌似put也可以作为触发 周期工作条件，只不过他是把put的value作为 newValue的值保存起来了
        而不是把运行load的结果保存到newValue
        但是不管是get还是put 作为触发条件 都是直接把 他们此次的value 作为newValue变量的值保存以便后续使用
        cache.refresh("xy");//强制 刷新缓存
        怎么说呢 其实就是强行执行load方法（不管是否满足刷新时间条件），然后将结果保存到newValue
        我测试过 就是将：
        Thread.sleep(1000);这之前newValue=2
        cache.refresh("xy");//强制刷新缓存 拿到之前newValue=2 修改 newValue=3
        cache.refresh("xy");//强制刷新缓存 拿到之前newValue=3 修改 newValue=4
        System.out.println(cache.get("xy")); //由于这次不满足刷新条件直接拿到newValue=4 不做修改(因为不满足条件）
        本来这一次是不满足刷新条件的，按理应该是 拿到2  现在由于refresh原因 拿到的是4
         */
        // refreshAfterWrite 指定在创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
        LoadingCache<Object, Long> cache = Caffeine.newBuilder().recordStats().maximumSize(10).refreshAfterWrite(2, TimeUnit.SECONDS).build(new CacheLoader<Object, Long>() {
            Long count=0L;
            @Override
            public Long load(Object key) throws Exception {
                System.out.println("进来了");
                count++;
                return count;
            }
        });
        //key存在的结果是 1000 1000 1 1 2  不存在的结果是 1 1 1 2 2
//        cache.put("xy", 1000L);
        Thread.sleep(1000);//这一次并不触发refreshAfterWrite的开始，因为还没有get或者refresh
        System.out.println(cache.get("xy"));  //第一次理解为创建缓存，因为之前没有 xy这个缓存
//        cache.refresh("xy");//强制刷新缓存
        Thread.sleep(3000);
        System.out.println(cache.get("xy"));
        Thread.sleep(1000);
        cache.refresh("xy");//强制刷新缓存
        cache.refresh("xy");//强制刷新缓存
        System.out.println(cache.get("xy"));
        Thread.sleep(3000);
        System.out.println(cache.get("xy"));
        Thread.sleep(3000);
        System.out.println(cache.get("xy"));
        Thread.sleep(5000);
        System.out.println(cache.get("xy"));
        Thread.sleep(3000);
        System.out.println(cache.get("xy"));
        Thread.sleep(5000);
        System.out.println(cache.get("xy"));
        Thread.sleep(1000);
        System.out.println(cache.get("xy"));
        Thread.sleep(4000);
        System.out.println(cache.get("xy"));
        System.out.println("load的次数，这里次数没错，共12,因为1秒的这两不进入load"+cache.stats().loadCount());
    }
@Test
public void test7() throws InterruptedException {//模拟过期 这种expireAfterWrite就是 表示多久没有写操作就过期
    Cache<Object, Object> cache = Caffeine.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).build();
    cache.put("xy", "value");
    System.out.println(cache.getIfPresent("xy"));
//    Thread.sleep(5000);
    Thread.sleep(1000);
    System.out.println(cache.getIfPresent("xy"));
}
@Test
public void test8() throws InterruptedException {
    Cache<Object, Object> cache = Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();
    /*
    本来 我们的expireAfterWrite是懒加载形式的，也就是只有 我们要用到该key，他才会去比较 该key是否过期，过期删除掉
    但是cleanUp只要一调用就会把所有过期的key移除掉，释放掉内存！
     */
    cache.put("xx", "v1");
    cache.put("xy", "v2");
    cache.cleanUp();
    System.out.println(cache.getIfPresent("xy"));
    Thread.sleep(5000);
    System.out.println(cache.getIfPresent("xy"));
}
@Test
public void test9() throws InterruptedException {
    LoadingCache<String, String> cache = Caffeine.newBuilder().recordStats().expireAfterWrite(2, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
        int count = 0;

        @Override
        public String load(String key) throws Exception {
            count++;
//            return null;//用来测试loadFailureCount
            return count+"";
        }
    });
    cache.put("yy", "value");
    cache.get("yy");
    cache.get("xy");
    cache.get("xx");
    cache.get("xy");
    Thread.sleep(3000);
    cache.get("xy");
    cache.get("xx");
    cache.get("yy");
    System.out.println("当重写load后返回进入load的次数:"+cache.stats().loadCount());
    System.out.println("返回当key存在后，命中缓存的次数，也就是我们get的次数(但是注意必须在key存在后" +
            "如这里第一次 get(\"xy\")是不存在的，只有第二次开始才存在,但是注意休眠3s后，这个key游不存在" +
            "所以休眠后的几次get都不算):"
            +cache.stats().hitCount());
    System.out.println("返回当key存在后，get总次数以及命中次数的比率:"+cache.stats().hitRate());
    System.out.println("过期次数"+cache.stats().evictionCount());
    /*
    经过我的发现，我注意到loadSuccessCount和loadFailureCount 特点，当我们使用load方法返回null表示
    这次加载失败，返回非null表示 加载成功
    为什么呢？
    因为 String load 的这个返回值类型 是由LoadingCache<String, String> cache决定的，而我们知道 泛型只能够
    是 引用类型的，而为null表示这个key的值是无意义的！
    而我之前使用过 计算对象大小的如果为null 表示大小为0
    换句话说 get(xx)=null表示 大小为0无意义的！
     */
    System.out.println("调用load成功次数:"+cache.stats().loadSuccessCount());
    System.out.println("调用load失败次数:"+cache.stats().loadFailureCount());
    /*
    averageLoadPenalty()表示 加载新值花费的平均值，用处不大主要是计算性能
     */
    System.out.println(cache.stats().averageLoadPenalty());
}

    /**
     * 同步加载和手动加载区别就是  同步加载会有一个默认值，当这个key不存在的时候 会使用默认值
     * 异步和同步区别在于  同步 的时候创建默认值调用方法如果是一个耗时操作 那么必须等待耗时操作完毕才可以
     * 继续下去，但是 异步的话不需要等待，不过如果异步 使用get的时候 如果 build还未完成必须等待！
     *
     * 注意CompletableFuture这个类是一个 异步处理类，分而治之思想，不仅仅有get方法的(暂时不深入这个)
     * caffeine提供了一个synchronous可以将 异步处理操作变为同步处理
     *
     * 关于缓存移除策略 也是三个和cacheBuilder一样  基于大小 时间 引用（先不了解）
     * 基于 大小有两种一种是 个数 一种是权重
     *
     * 删除具体是什么时候发生，不确定反正不是一到某个点就删除，如按照时间删除不是说到了 那么分钟就被删除，具体在哪个时候我也不确定
     * 如果要自动删除那么肯定要有一个单独线程去进行自动删除操作的，但是并没有
     *
     *
     * 您可以在同一个缓存中同时指定refreshAfterWrite和expireAfterWrite，只有当数据具备刷新条件的时候才会去刷新数据，不会盲目去执行刷新操作。如果数据在刷新后就一直没有被再次查询，那么该数据也会过期。
     * 怎么理解呢？就是说expireAfterWrite设置为10s过期，而refreshAfterWrite设置为 1s刷新一次，如果触发了
     * 刷新后，按道理是1s刷新一次该key，这样岂不是矛盾 因为看似expireAfterWrite永远不会过期，其实不是
     * 如果我们之后(超过过期时间)一直不去访问该key 那么还是会过期.从这个我也发现了refreshAfterWrite是每次
     * get的时候去和上一次真正刷新时间对比看是否满足一次刷新的条件。
     * 如 间隔5s  5 3 2  那么5刷新  由于 3不够  所以累计到2  2+3=5 刷新 ，那么 第三次刷新到第一次刷新中间才够5s
     * 而第二次到第一次不够5s   所以第二次不会刷新
     * 因为我发现就算最后一次我设置为1min(我的刷新间隔为5s)，他还是增加 1 而已
     *
     * 注意一条  如果load 返回值为null表示 此次调用load 失败
     */
}
