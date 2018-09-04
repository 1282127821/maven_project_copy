/**
 * @fileName:TestOptional
 * @author:xy
 * @date:2018/8/10
 * @description:
 */
package com.xy.optional;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.*;
import java.util.function.Consumer;

/**
 * @fileName:TestOptional
 * @author:xy
 * @date:2018/8/10
 * @description:
 */
public class TestOptional {
    public static void main(String[] args) {
        /**
         * optional 有三种创建optional对象的方式。
         * empty这个是根据一个新的指定对象（如 new MyClass()）
         * of给定的对象必须保证不为null，最保险就是new
         * ofNullable 可以为null，如果不为null就是给定的对象，如果为null，相当于使用empty创建option
         *
         * isPresent()是否存在,相当于判空  obj!=null
         * get()注意get方法是获取 泛型对象，而不是optional对象
         *推荐 return user.orElse(null); //而不是 return user.isPresent() ? user.get() : null;
         * return user.orElse(UNKNOWN_USER);
         * orElse判断调用它的对象的泛型对象是否为null，不为就得到该对象，为null就使用orElse方法里面指定默认对象
         * ifPresent如果存在 ，则做一些事（使用lambda）
         * user.ifPresent(System.out::println);
         *
         * //而不要下边那样
         * if (user.isPresent()) {
         *  System.out.println(user.get());
         * }
         *
         * map是一个级联操作，可以一直往里面解析。总是获取上一次操作的值。
         * 开始m是instant1对象，然后下一个map就是list对象，然后就是一个string对象。
         */
        Optional<MyClass> empty = Optional.empty();
        Optional<MyClass> instant1 = Optional.of(MyClass.getInstant(1));
        Optional<MyClass> instant2 = Optional.ofNullable(MyClass.getInstant(0));
        int aa=1;
        System.out.println(aa);
        System.out.println(instant2.isPresent());
        System.out.println(instant1.get());
//        System.out.println(empty.get());//报错因为没有可以获得的对象
        System.out.println(instant2.orElse(null));

        instant1.ifPresent((a) -> {
            System.out.println(a.age);
        });//lambda注意a类型自动推断的.只要使用这个就表示传递的是当前对象

        System.out.println(instant1.map((m) -> m.getList()).map((n) -> n.get(0)).map((x) -> x.toUpperCase()));


        /**
         * 这里顺便总结下  lambda里面的四宗引用
         * 构造引用  静态引用  对象引用  特殊引用
         * lambda表达式可以替代方法引用；或者说方法引用是lambda的一种特例，方法引用不可以控制传递参数。
         * 相当于()->{xxx}  .而他不能这样的lambda (x,y)->{xxx}。
         * 我发现了规律就是，对象，静态，构造引用必须和lambda调用的那个方法拥有同样的参数(顺序也得一样)
         * 如forEach的lambda是accept(T t)然后我们的list是Strig也就是这个T 就是String类型的。
         * 那么我们写引用的方法（就是 ::后面的方法名）的参数必须是String的。
         * 当然特殊引用就有一点特殊啦：
         * 所以上面为嘛说是lambda的特殊（完全可以用lambda代替），因为他必须保证lambda方法内部会去调用我们方法引用调用
         * 的方法，并且参数也要一致（其实我们写的引用相当于(x,y)->{xxx}的xxx，但是这个xxx必须是和lambda方法参数一致）
         * 如accept（T t） 那么我们的方法引用的方法也必须是 （这里拿string举例） method(String s)
         * 这也就是它的特殊。1,lambda方法内部必须做操作。2，lambda方法参数和方法引用参数 保持一致(特殊引用特殊点)！
         *特殊引用由于特殊单独拿出来:
         * 首先它的格式是 类名::方法名
         * 其中他特点就是 引用的方法的参数可以比lambda方法参数少一个。但是少的这一个是有讲究的，他是根据我们提供的类名 自动
         *new了一个该对象，换句话说最终内部还是两个，只是我们编译的时候少一个，所以少的这一个必须是 该类的对象。并且约定
         * 少的那一个必须是第一个参数（有点类似可变形参和普通参数混用，必须把可变形参放第一个位置）。否则内部不知道你是少的
         * 哪一个，所以规定死了只能少第一个。这个主要是防止这种情况 method（lambda a，lambda b）然后我
         * syso（a.hashCode()）  ,  syso(b.getClass()) (伪代码哈)这样如果你不规定，那么就是说第一，第二都有可能是省略的
         * 这时候得到的结果就不一样了。
         *
         */
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);
        Lambda lambda = new Lambda();
        /**对象引用*/
        list.forEach(lambda::method);
        /** 静态引用*/
        list.forEach(Lambda::staticMethod);
        /** 构造引用*/
        list.forEach(Lambda::new);
        /**特殊引用 */
        new TestSingle().method(Lambda::special);


    }
}

interface ISingle {
    void single(Lambda l, int i);
}

class TestSingle {
    public void method(ISingle single) {
        single.single(new Lambda(),110);
    }
}

class Lambda {
    public Lambda(String s) {
        System.out.println(s + "-contruct");

    }

    public Lambda() {

    }

    public void method(String s) {
        System.out.println(s);
    }

    public static void staticMethod(String s) {
        System.out.println(s + "(*^▽^*)");
    }

    public  void special(int i) {
        System.out.println(i);
    }
}

class MyClass {
    public String name = "xy";
    public int age = 23;
    public static List<String> list = new ArrayList<>();

    public static MyClass getInstant(int status) {
        if (status == 1) {
            list.add("a");
            list.add("b");
            return new MyClass();
        }
        return null;
    }

    public List<String> getList() {
        return list;
    }

    public void test(int... a) {
        List<String> list = Arrays.asList("a", "b", "c");
//        list.forEach(System.out::println);

    }

    public void me() {
        test(1, 2);
    }

}


