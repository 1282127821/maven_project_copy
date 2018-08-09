/**
 * @fileName:RunnableImplObject
 * @author:xy
 * @date:2018/7/27
 * @description:
 */
package com.xy.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *@fileName:RunnableImplObject
 *@author:xy
 *@date:2018/7/27
 *@description:
 */
public class RunnableImplObject {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        // String里定义一段JavaScript代码脚本
        String script = "var obj = new Object(); obj.run = function() { print('run method called'); }";

        // 执行这个脚本
        engine.eval(script);

        // 获取这个我们要用来实现接口的js对象
        Object obj = engine.get("obj");

        Invocable inv = (Invocable) engine;

        // 通过脚本引擎获取Runnable接口对象 该接口的方法已被js对象实现
        /**
         * Runnable r = inv.getInterface(obj, Runnable.class);  这个很重要，obj是我们从脚本获取的，其实就是一个对象使用
         * get方法获取（）put可以放对象到脚本，那么必然get可以从脚本拿对象。
         * 然后这个对象会被改造类似powermock。会让它实现接口，根据getInterface，这个就是用来改造用的。
         * 那么最终obj就是类似java的线程对象了。然后传给Thread，这样当th.start（）,按照线程，就会去调用实现runable接口的
         * run方法，也就是使用obj这个对象（当调用的时候已经是一个线程对象了）
         * 这时候按照script的逻辑当我们使用obj.run就会执行那个函数体。（其实注意这个脚本已经被转换为java语言写的代码了，其实很好理解
         * 不然在jkd1.8他怎么会认识var function()这些java都不认识，肯定要转换）
         * 那么最终他发现 obj.run就会转换为  java里面的 对象调用方法 也就是  obj.run();然后run本身是没有东西，所以他看到你
         * script 里面obj.run = function() { print('run method called');有一坨函数体，那么就会解析到 run方法里面去
         * 所以最终就是按照 线程方式打印这句话。
         *还有 getInterface只能提供接口，其他的都不行
         */
        Runnable r = inv.getInterface(obj, Runnable.class);
        System.out.println(obj.getClass()+"这个obj已经被改造了");
        System.out.println(r.getClass()+"r也是被改造的");

        // 启动一个线程，运行已被脚本实现的Runnable接口
        Thread th = new Thread(r);
        th.start();
    }

}
