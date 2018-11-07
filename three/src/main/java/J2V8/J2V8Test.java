package J2V8;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.utils.V8Map;
import com.eclipsesource.v8.utils.V8ObjectUtils;
import com.eclipsesource.v8.utils.V8Runnable;
import com.eclipsesource.v8.utils.V8Thread;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @fileName:J2V8Test
 * @author:xy
 * @date:2018/9/10
 * @description:
 */
public class J2V8Test {
  /**
   * 创建v8运行对象 V8 runtime = V8.createV8Runtime(); 创建v8线程对象: 其实 v8线程就是以前我学的线程 前缀加了 V8而已 V8Thread
   * v8Thread = new V8Thread(new V8Runnable() { @Override public void run(V8 v8) {
   *
   * <p>} }); 多线程必须有一定的延迟 我测试过延迟1ms没用 但是1s可以 这个原因貌似是因为守护线程原因，他把他设置为了守护线程！！！
   */
  @Test
  public void test1() {
    V8 runtime = V8.createV8Runtime();
    int result =
        runtime.executeIntegerScript(
            ""
                + "var hello = 'hello, ';\n"
                + "var world = 'world!';\n"
                + "hello.concat(world).length;\n");
    System.out.println(result);
    runtime.release(); // 记得手动释放资源，和流一个道理全局变量
  }

  @Test
  public void test2() throws InterruptedException {
    V8Thread v8Thread =
        new V8Thread(
            (v) -> {
              System.out.println(V8Thread.currentThread().getName());
            });
    V8Thread v8Thread2 =
        new V8Thread(
            (v) -> {
              System.out.println(V8Thread.currentThread().getName());
            });
    v8Thread.start();
    v8Thread2.start();
    //        v8Thread.join();
    //        v8Thread2.join();
    Thread.sleep(1000); // 1ms  1000ms
    //        while (true){}
  }

  /**
   * V8Map的泛型只能定义value的类型，key的类型是固定的 这个在 new V8Map()的构造看得出来。所以key必须是V8Value 具体那些是V8Value ctrl+T就知道了
   * V8Object就是其中一个 map = new HashMap<V8Value, V>(); twinMap = new HashMap<V8Value, V8Value>();
   */
  @Test
  public void test3() throws InterruptedException {
    V8 runtime = V8.createV8Runtime();
    V8Map<String> map = new V8Map(); // 这些类都是 Releasable子类，所以只要注册就可以自动释放资源
    V8Object v8Object = new V8Object(runtime); // 其实这个也可以自动释放资源,只是为了和V8Map对比
    map.put(v8Object, "testMap");
    runtime.registerResource(map); // 注册后 才会去释放map资源我大致看了下源码 resources会遍历去调用release方法
    //    runtime.registerResource(v8Object);
    System.out.println(map.get(v8Object));
    v8Object.release(); // 必须先释放，因为runtime被他引用了
    runtime.release();
  }

  @Test
  public void test4() {
    V8 runtime = V8.createV8Runtime();
    runtime.executeVoidScript(
        ""
            + "var person = {};\n"
            + "var hockeyTeam = {'name' : 'WolfPack'};\n"
            + "person.first = 'Ian';\n"
            + "person['last'] = 'Bull';\n"
            + "person.hockeyTeam = hockeyTeam;\n");
    V8Object person = runtime.getObject("person"); // 获取对象，数组 其他类型类似
    V8Object hockTeam = person.getObject("hockeyTeam");
    System.out.println(hockTeam.getString("name"));
    System.out.println(person.getString("first"));
    System.out.println(Arrays.toString(person.getKeys()));
    person.release();
    hockTeam.release();
    runtime.release(); // 必须先把依赖它的关闭掉
  }

  /**
   * executeVoidScript 表示 执行一个返回值 为void的脚本，其他返回值类推 executeIntegerScript表示执行脚本 把最后一行把当做返回值返回 这里的优点类似
   * groovy，由于了解过所以理解起来对比记忆 但是注意 有返回值 和 是否是 方法为返回值没关系的，比如这里的getPerson就是有返回值的，但是我并不执行脚本的时候返回 而是在真正调用
   * 但是执行 函数最好先 executeVoidScript(也就是不要使用执行脚本有返回值) ，然后executeStringFunction 这样去调用
   * 函数的时候 得到返回值 String i = v8.executeStringScript("'function get(){return 1}'"); 注意 脚本是放在
   * ""里面的，如果要表示字符串那么还需要使用 ‘’或者 \\" 才能表示 脚本里面的字符串的
   */
  @Test
  public void test5() {
    V8 v8 = V8.createV8Runtime();
    String i = v8.executeStringScript("'function get(){return 1}'"); // 表示字符串，有返回值
    v8.executeVoidScript("'function get(){return 1}'"); // 表示字符串,无返回值
    System.out.println(i);
    v8.executeVoidScript(
        "function getPerson(first, last, age) {return {'first':first, 'last':last, 'age':age};}");
    V8Array parameters = new V8Array(v8);
    parameters.push("John");
    parameters.push("Smith");
    parameters.push(7);
    V8Object result = v8.executeObjectFunction("getPerson", parameters);
    System.out.println(result.getString("first"));
    v8.registerResource(parameters);
    v8.registerResource(result);
    v8.release();
  }

  /** V8Array#push表示往数组放值，其中可以放任何类型的值 ，push支持链式编程 V8ObjectUtils 相当于工具类 Arrays 专门用来操作 v8数组的 */
  @Test
  public void test6() {
    V8 v8 = V8.createV8Runtime();
    V8Array v8Array = new V8Array(v8);
    V8Array arr = v8Array.push(0).push(2).push("str");
    System.out.println(V8ObjectUtils.toList(arr));
    System.out.println(V8ObjectUtils.getValue(arr, 2));
    v8.registerResource(v8Array);
    v8.release();
  }

  /** json 其实以前总是说 json对象 json对象(key-value)，所以这里是使用 V8Object，他可以表示json对象 */
  @Test
  public void test7() {
    V8 runtime = V8.createV8Runtime();
    V8Object v8Object = new V8Object(runtime);
    v8Object.add("a", 1);
    v8Object.add("b", "b");
    V8Object json = runtime.getObject("JSON");
    System.out.println(json);
    System.out.println(v8Object.get("a"));
    v8Object.release();
    json.release();
    runtime.release();
  }

  /** 把 js脚本弄成一个单独文件 */
  @Test
  public void test8() throws IOException {
    File file =
        new File(
            "G:\\work\\data\\idea_workspace\\maven_project\\three\\src\\main\\java\\J2V8\\v8js.js");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    StringBuilder sb = new StringBuilder();
    String strLine = null;

    while ((strLine = br.readLine()) != null) {
      sb.append(strLine).append("\r\n"); // 为了保证js的严格“行”属性，这里主动追加\r\n
    }
    br.close();
      V8 v8 = V8.createV8Runtime();
      v8.executeVoidScript(sb.toString());
      String result = v8.executeStringFunction("v8js", null);
      System.out.println(result);
      //释放资源省略
  }
}
