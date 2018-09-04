/**
 * @fileName:AssertTest
 * @author:xy
 * @date:2018/8/30
 * @description:
 */
public class AssertTest {
    public static void main(String[] args) {
        /**重点！默认 断言是不打开的，必须让虚拟机打开 （但是如果用junit注解 他代码里面帮我们打开了）
         * 打开其实就是设置一个虚拟机参数 -ea 设置了才能正常使用断言，否则就算是false也不会报错！！！
         * 断言 assert 很简单，一般都是在测试用，不推荐正式开发代码使用
         * assert n!=1; 如果 为true 就继续执行下面的代码，否则就抛出断言异常 （不捕获异常）
         * assert n!=1; 如果为true 就继续执行，否则 输出打印 错误提示，然后继续执行下面代码(捕获异常)
          */
        assert 2>1:"出异常后抛出吗?";
        assert 1>2:"出异常后抛出吗?";
        System.out.println("这里会被执行的，因为上面那种捕获了：AssertionError");
        assert 2>1;
        assert 1>2;
        System.out.println("这种不会执行，因为上面抛出了异常");

    }
}
