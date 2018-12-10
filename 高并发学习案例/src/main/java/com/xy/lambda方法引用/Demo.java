package com.xy.lambda方法引用;

import org.junit.Test;

import java.util.List;

/**
 * @fileName:Demo
 * @author:xy
 * @date:2018/12/1
 * @description:
 */
@SuppressWarnings("all")
public class Demo {
    /**
     * 对象方法引用，注意只能是 我们的某个方法引用了 lambda的参数
     * 也就是必须我们这里的 x 被println使用.
     * 由于 成员方法引用无法传递参数(意思是我们无法手动传递，因为写法是System.out::println 这样没法携带参数，只是我们无法传递，内部是可以的)
     * 比如这里的 System.out::println其实就是把 x传递给他了。
     * 判断能否使用 对象方法引用 就是我们调用的那个非静态方法 是否都把 lambda的参数用到了，用到了就可以
     */
    @Test
    public void test(){
        goLambda((x) -> { System.out.println(x);});//这里println可以使用方法引用因为他只需要lambda的参数
        goLambda( System.out::println);
        ObjRef objRef=new ObjRef();
        goLambda((x)->{objRef.say(x, new String("xxx"));});//这一个就无法使用方法引用了，因为我们的say方法需要 除了lambda参数以外还有其他参数
        goLambda2((x,y)->{objRef.say(x,y);});
        goLambda2(objRef::say);//因为lambda都被say用到了所以可以使用引用
    }

    /**
     * 静态引用和对象引用没啥区别 只不过调用的方法是静态方法而已
     */
    @Test
    public void test1(){
        goLambda3((x)->{StaticRef.say(x);});
        goLambda3(StaticRef::say);
    }

    /**
     * 构造引用也不难，其实就是 我new一个对象的时候 需要将 lambda的参数作为构造形参而已
     */
    @Test
    public void test2(){
        goLambda3((x)->{new ConstructRef(x);});
        goLambda3(ConstructRef::new);
    }

    /**
     * 成员方法 引用， 这个有一点特殊，它的写法和静态引用类型，【但是注意】 他调用的是成员方法，也就是非静态方法  类名.method().
     * 但是我们知道 java里面不能这样去调用一个普通方法的。所以这其实是lambda的一种转变，他固定 死你就是使用 lambda的第一个参数作为对象
     * 去调用 它某个方法 ，然后 lambda后面的参数全部作为该方法形参
     */
    @Test
    public void test3(){
        // 看见没 第一个lambda参数作为对象调用方法 ，而该方法的参数为lambda后续参数
        System.out.println(goLambda4((x,y)->{return x.compareToIgnoreCase(y);}));
        System.out.println(goLambda4(String::compareToIgnoreCase));
    }

    /**
     * 总结 除了 成员方法引用  外，其他的几个引用都是lambda的特殊情况，这个情况就是  我们调用的方法(构造也算调用方法哈) 必须有且仅有 lambda 给定的形参
     * 少了多了都不行。也就是  给定的lambda参数 必须都用上，而且他不允许有其他非lambda参数（所以我用了 有且仅有 必须有lambda参数 而且只能有这些参数）
     */
//--------------------------------------------------------------
    public void goLambda(LamBdaInterface lamBdaInterface){
        int x=10;
    lamBdaInterface.method(x);
    }
    public void goLambda2(LamBdaInterface2 lamBdaInterface2){
        int x=10;
        String y="xx";
        lamBdaInterface2.method(x,y);
    }
    public void goLambda3(LamBdaInterface lamBdaInterface){
        int x=10;
        lamBdaInterface.method(x);
    }
    public int goLambda4(LamBdaInterface3 lamBdaInterface3){
        String x="十";
        String y="十";
        return lamBdaInterface3.method(x,y);
    }
}
@FunctionalInterface
interface LamBdaInterface{
    void method(int x);
}
@FunctionalInterface
interface LamBdaInterface2{
    void method(int x,String y);
}
@FunctionalInterface
interface LamBdaInterface3{
    int method(String x,String y);
}
class ObjRef{
    public void say(Object o1,Object o2){
    System.out.println(o1);
    System.out.println(o2);
    }
}
class StaticRef{
    public static void say(Object o1){
        System.out.println(o1);
    }
}
class ConstructRef{
    private int age;
    public ConstructRef(int age) {
        this.age=age;
    System.out.println("验证我被调用了!");
    }
}
