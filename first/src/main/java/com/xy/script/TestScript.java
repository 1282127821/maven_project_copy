/**
 * @fileName:TestScript
 * @author:xy
 * @date:2018/7/27
 * @description:
 */
package com.xy.script;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *xy
 * 2018年7月27日
 */
public class TestScript {
    /**
     * ScriptEngineManager是用来创建engine的工具
     * ScriptEngine这个是核心，用来解析公式或脚本的（其实groovy就是利用这个解析）
     * eval方法就是用来放将要解析的script
     * put方法是放脚本里面的变量，注意可以是对象
     * 注意engine对象有一个缺点，不能重复执行，就是你想多次执行某个script，必须多次使用eval方法，很浪费
     * 而
     *
     *
     */
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager scriptEngineManager=new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("js");
        engine.put("x",5);
        engine.put("y",10);
        engine.put("o",new Object());
        Object eval = engine.eval("x*2+y");
        Object eval1 = engine.eval("o.getClass()");
        System.out.println(eval);
        System.out.println(eval1);
        String script = "function hello(name) { print('Hello, ' + name); }";
        // 直接执行上面的js函数（这个函数是全局的，在下面的js引擎中依然可以调用，不会执行完就消失）
        engine.eval(script);
        /**
         * // javax.script.Invocable 是一个可选的接口
         *         // 检查脚本引擎是否被实现!
         *         // 注意：JavaScript engine 实现了 Invocable 接口
         *         之所以engine可以强转为Invocable，是因为通过getEngineByName获取的engine是JavaScript的 engine
         *         NashornScriptEngine extends AbstractScriptEngine implements Compilable, Invocable
         *         所以可以先上转型变成ScriptEngine 然后强转为Invocable
         *         NashornScriptEngine n=(NashornScriptEngine)engine;
         *         Invocable inv=n;
         *         上面两句和下面的是一样的。
         * //        Invocable  inv= (Invocable) engine;
         * 这里不得不说我之前总结的，强转那坨只是为了 编译器不报错，真正能否转换成功依靠的是他们俩是否有关系
         * 也就是右边的对象和左边引用类型是否有关系。
         * 所以Invocable  inv= (Invocable) engine;就好理解了。engine真实类型是NashornScriptEngine，所以为了不然编译器报错
         * （Invocable）强转，因为表面上engine是ScriptEngine类，他和Invocable没有父子关系，所以为了让编译器，不报错需要强制转换
         * 但是能否转换成功 编译阶段不能确定。然后运行阶段会判断engine是否和Invocable类型有关系，这里判断Invocable是NashornScriptEngine的
         * 父类，所以可以是上转型关系。不要被ScriptEngine迷惑，因为这个engine对象真实类型是NashornScriptEngine
         * 所以要学会透过现象看本质！！！！
         *
         */
        System.out.println(engine.getClass());
        NashornScriptEngine n=(NashornScriptEngine)engine;
        Invocable inv=n;
//        Invocable  inv= (Invocable) engine;
        System.out.println(inv.getClass());
        Object o = inv.invokeFunction("hello","xx");//看名字就知道是专用来调用函数的

//        engine.eval("var o =new Object();" +
//                "o" )
    }
}
