package 单例;

/**
 * @fileName:Single
 * @author:xy
 * @date:2018/11/19
 * @description:
 */
@SuppressWarnings("all")
public class Single {
    /**
     * 这种方式巧妙地避免了  恶汉模式 调用其他属性就导致 单例对象被实例化(恶汉的一个小缺点)，而对于懒汉模式呢？缺点就是 双重加锁，并发效率低下
     * 这种呢：首先只有我们调用getInstance才会去实例化single对象(弥补了恶汉缺点)。这一种不需要加锁，因为他只会在我们第一次调用getInstance去实例化该对象
     * 因为他是static的属性！ 所以说这一种使用内部类弥补了 懒汉+恶汉 的缺点
     */
    private static class Inner{ //这里加静态是没法子，因为 静态内部类的属性必须是静态类,具体看以前笔记
        private static Single single=new Single();
    }
    //要知道一点 内部类-外部类-其他类  对于外部类来说内部类相当于它的属性而已! private static class Inner相当于private static 属性
    public static Single getInstance(){
        return Inner.single;
    }
}
