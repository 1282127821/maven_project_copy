/**
 * @fileName:ImplObject
 * @author:xy
 * @date:2018/7/27
 * @description:
 */
package com.xy.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *@fileName:ImplObject
 *@author:xy
 *@date:2018/7/27
 *@description:
 */
public class ImplObject {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager scriptEngineManager=new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("js");
        engine.eval("var obj=new Object();obj.m=function(){print('obj是Xy对象')};obj.m();");
        Invocable inv = (Invocable) engine;
        Object obj = engine.get("obj");
        inv.getInterface(obj,Xy.class);


    }

}
