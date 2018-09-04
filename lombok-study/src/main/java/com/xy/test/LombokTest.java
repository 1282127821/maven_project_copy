package com.xy.test;

import com.sun.istack.internal.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.junit.Test;

import javax.annotation.Resource;
import javax.jws.WebResult;
import java.util.Date;
import java.util.Scanner;

/**
 * @fileName:LombokTest
 * @author:xy
 * @date:2018/8/21
 * @description:
 */
public class LombokTest {
  public static void main(String[] args) {
    User user = new User();
    user.setName("xy");
    user.setAge(23);
    System.out.println(user);
    user.setInfo(null);
      User yy = new User("yy", 23, "111",new Date());
      System.out.println(yy);
      Man man = new Man("xx", "constant");
      System.out.println(man.getName());

      Accesstor accesstor = new Accesstor();
      accesstor.setName("xxx");
      ExceptionTest exceptionTest=new ExceptionTest();
//      exceptionTest.exception();//测试抛异常的
      LogExample.main("log 日志");
      LogExampleOther.main("slf4j 日志");
      LogExampleCategory.main("commons-logging日志");
      /**
     * shift 是 下一行 ctrl+shift+enter是上一行 不好记住 还是 @Getter @Setter 很简单就是生产对应的get/set如果是 boolen那么就是
     * is开头，注意可以用在类或方法上，类就是作用所有属性，方法就是指定属性
     *
     * @toString 该注解使用在类上，该注解默认生成任何非讲台字段以名称-值的形式输出。 1、如果需要可以通过注释参数includeFieldNames来控制输出中是否包含的属性名称。
     *     2、可以通过exclude参数中包含字段名称，可以从生成的方法中排除特定字段。 3、可以通过callSuper参数控制父类的输出。 表示打印是否包含属性名称
     *     includeFieldNames =true---> User(name=xy, age=23, info=null)
     *     includeFieldNames=false-->User(xy,23, null) exclude表示排除某字段 callSuper
     *     当继承的时候是否打印父类属性 @EqualsAndHashCode 很简单就是就是指 生成 这两个方法，具体用法类似toString但是注意
     *     ****************************存在继承关系需要设置callSuper参数为true！！！！****************************** @NonNull
     *     就是判空但是具体用法没搞明白(不是notnull) @AllArgsConstructor 根据名称知道就是生产 包括所有参数的一个构造方法 貌似不包括常量（因为我发现
     *     ctrl+0 看不到）
     *     因为final的要么一开始就赋值，要么使用构造方法，如果一开始就赴初始值就不能被改变 @NoArgsConstructor刚好相反生成无参构造 @RequiredArgsConstructor
     *     该注解使用在类上，使用类中所有带有 @NonNull 注解的或者带有 final 修饰的成员变量生成对应的构造方法
     *     原因很简单 @NonNull修饰的不能为null，也就是表示必须赋值的参数，final 修饰的也是一开始要赋值(或通过构造赋值)。所有lombok
     *     认为这些就是必须的参数！！！！ @Data
     *     该注解使用在类上，该注解是最常用的注解，它结合了@ToString，@EqualsAndHashCode， @Getter和@Setter。本质上使用@Data注解，类默认@ToString和@EqualsAndHashCode以及每个字段都有@Setter和@getter。该注解也会生成一个公共构造函数，可以将任何@NonNull和final字段作为参数。
     *     <p>虽然@Data注解非常有用，但是它没有与其他注解相同的控制粒度。@Data提供了一个可以生成静态工厂的单一参数，将staticConstructor参数设置为所需要的名称，Lombok自动生成的构造函数设置为私有，并提供公开的给定名称的静态工厂方法。 @Value
     *     和@Data 类似唯一不同就是他成的 只有get方法没有set方法 @Cleanup
     *     是用来自动关流。该注解使用在属性前，该注解是用来保证分配的资源被释放。在本地变量上使用该注解，任何后续代码都将封装在try/finally中，确保当前作用于中的资源被释放。默认@Cleanup清理的方法为close，可以使用value指定不同的方法名称
     *     反正这个就是用在流对象变量上面的，用来自动关流 @Synchronized
     *     该注解使用在类或者实例方法上，Synchronized在一个方法上，使用关键字可能会导致结果和想要的结果不同，因为多线程情况下会出现异常情况。Synchronized
     *     关键字将在this示例方法情况下锁定当前对象，或者class将方法的对象上多锁定。这可能会导致死锁现象。一般情况下建议锁定一个专门用于此目的的独立锁，而不是允许公共对象进行锁定。该注解也是为了达到该目的。
     *     其实和以前理解的synchronized差不多
     *     类锁对象锁。但是推荐一般自己创建一个对象而不是使用this.因为这个注解有个value属性就是用来指定对象名，注意是字符串形式的对象名
     *     @Accessors @Accessors
     *     主要用于控制生成的getter和setter 主要参数介绍
     *     <p>fluent boolean值，默认为false。此字段主要为控制生成的getter和setter方法前面是否带get/set chain
     *     boolean值，默认false。如果设置为true，setter返回的是此对象，方便链式调用方法 prefix 设置前缀 例如：@Accessors(prefix =
     *     "abc") private String abcAge 当生成get/set方法时，会把此前缀去掉（也就是不包括变量名的abc 如 int abcAge 那么生成方法getAage）
       *    设置 fluent=false（这个是生成get/set方法添加get/set，设置为true就是没有get/set前缀）
       *
       * @Delegate 用在变量上，他会把该变量对应的所有方法都包括进来（该变量的父类方法也包含）
       * 他里面有一个 excludes用来排除某个类里面的方法，比如我这里排除Syn.class后这个类里面原生方法就没了，但是它父类的还存在的
       *
       * @SneakyThrows
       * 该注解使用在方法上，这个注解用在 方法 上，可以将方法中的代码用 try-catch 语句包裹起来，捕获异常并在 catch 中用 Lombok.sneakyThrow(e) 把异常抛出，可以使用 @SneakyThrows(Exception.class) 的形式指定抛出哪种异常。该注解需要谨慎使用
       * 其实就相当于 做了捕获抛出操作.注意 注解里面的value指定的是捕获哪种异常，而不是我猜想的哪种抛出指定异常。
       * 作用不大其实就是省略了try-catch而已，不能自定义抛出哪种异常
       * @log 一系列有很多的，因为日志框架太多了  。基本使用和他一样，可以使用在类或方法上（全局和局部的区别）
       * @Builder 和他名字一样用来构建对象的，
       *
       * 如果我们在构建的时候有的属性没有使用到 那么就是 如下规则  0/false/null  分别对应 数字 boolen  和引用类型
       * 当然我们可以结合builder的内部注解 @Default 达到给我们构建没有使用到的属性 设置默认值，这样我们构建的对象的该字段就有默认值
     */
  }
  @Test
  public void test(){
      /**
       * 由于我们的created属性没有被default注解修饰 构建的时候又没有使用该字段 所以虽然我附初始值但是构建的对象还是么有值
       * 而end字段我用default注解修饰所有构建后使用了默认值
       * name虽然没有使用default，但是我构建对象使用了，所以也有
       * 构建一个对象很简单  类名.builder().字段1(值).字段2(值).build()
       * 可以这么理解使用【类名】提供的类进行对象的构建 【builder()】添加构建参与者(即字段) 【build】根据前面两的信息进行构建对象
       *修改之前构建好的对象 必须设置@Builder(toBuilder = true) 因为默认可以记忆为 不支持修改构建好的对象
       * 格式  对象名.toBuilder().字段1(值).字段2(值).build()
       * 其实很好想，因为是对已有的对象重新构建，所以肯定是【对象名】 然后重构参与者 toBuilder(),使用这个我们只要提供修改的字段
       * 之前新建好的 对象的属性（不变动的属性）值已经构建过，就不变 比如我的end属性之前构建设置好了，这里没动它所以end两个对象一样的
       * 然后进行 【build】构建
       *
       */
       //构建一个新对象
      BuilderExample b1 = BuilderExample.builder().name("xxxx").build();
        System.out.println(b1.toString());
        //修改之前构建好的对象
      BuilderExample yyyyy = b1.toBuilder().name("yyyyy").build();
      System.out.println(yyyyy);
      while (true){
      }

  }
}
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(
    includeFieldNames = true,
    exclude = {"info"})
class User {
  @Getter @Setter private String name;
  @Getter @Setter private int age;
  @Getter @Setter @NotNull private String info;
  @NonNull
  private Date date;
  private final String constant="CONSTANT";
}

@RequiredArgsConstructor
@Data
class Man{
    @NonNull
    private String name;
    private final String SEX;
    private int age;

}
@RequiredArgsConstructor
@Value
class WoMan{
    @NonNull
    private String name;
    private final String SEX;
    private int age;

}

class Syn{
    Object obj=new Object();
    private int i;
    @Synchronized("obj")
    public void testSyn(){
        i++;
    }
}
@Data
@Accessors(fluent = false,chain = true,prefix = "abc")
class Accesstor{
    private String abcName;
    private int step;
    private boolean flag;
}
class delegate{
    @lombok.experimental.Delegate(excludes = {Syn.class})
    Syn syn=new Syn();
}
class ExceptionTest{
    @SneakyThrows(NullPointerException.class)
    public void exception(){
        String abc=null;
        System.out.println(abc.toString());
    }
}

@Log
class LogExample {

    public static void main(String... args) {
//        log.error("Something's wrong here");
        log.info(args[0]);
    }
}

@Slf4j
class LogExampleOther {

    public static void main(String... args) {
        log.error("Something else is wrong here");
        log.info(args[0]);
    }
}

@CommonsLog(topic="CounterLog")
class LogExampleCategory {

    public static void main(String... args) {
        log.error("Calling the 'CounterLog' with a message");
        log.info(args[0]);
    }
}
@Builder(toBuilder = true)
@Data
class BuilderExample{
//    @My.Hi(value = "hello")
//    @My(anno = {@MyAnnoUse(value = "")})
//    private long a;

    private long created=System.currentTimeMillis();
    @Builder.Default
    private long end=System.currentTimeMillis();
    private String name;
}

