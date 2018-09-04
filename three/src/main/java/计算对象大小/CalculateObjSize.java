package 计算对象大小;

import org.apache.lucene.util.RamUsageEstimator;

/**
 * @fileName:CalculateObjSize
 * @author:xy
 * @date:2018/9/1
 * @description:
 */
public class CalculateObjSize {
    /**
     * RamUsageEstimator 这个是一个用来计算 对象大小的一个工具类
     * RamUsageEstimator.sizeOf(a); 这个sizeOf 会计算当前对应以及引用对象树
     * 的总大小（对象树就是计算a对象，但是a引用类b，b又引用c....）
     * humanSizeOf 和sizeOf功能类似，只不过返回值不同，human就是返回人类容易读的模式，说白了就是容易看
     * 因为 可能返回 1234 但是human做出处理变为1.234 MB 数字小了看不出，大了就知道这个的 易读！
     * shallowSizeOf 这个就是 只管当前对象大小，不包括 他引用的对象树！相当于 把对象设置为null后计算
     *其实判断原理很简单，如果一个对象 是null，那么大小就是 0 ！！
     * @param args
     */
    public static void main(String[] args) {
        A a=new A();
        long size = RamUsageEstimator.sizeOf(a);
        System.out.println(size);
        B b=new B();
        a.setbObj(b);
        long sizeOf = RamUsageEstimator.sizeOf(a);
        System.out.println(sizeOf);
        String humanSizeOf = RamUsageEstimator.humanSizeOf(a);
        System.out.println(humanSizeOf);
        long shallowSizeOf = RamUsageEstimator.shallowSizeOf(a);
        System.out.println(shallowSizeOf);
        a=null;
        long testNull = RamUsageEstimator.sizeOf(a);
        System.out.println(testNull);

    }
}
