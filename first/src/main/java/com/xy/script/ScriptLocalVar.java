/**
 * @fileName:ScriptLocalVar
 * @author:xy
 * @date:2018/7/27
 * @description:
 */
package com.xy.script;

import javax.script.*;

/**
 *@fileName:ScriptLocalVar
 *@author:xy
 *@date:2018/7/27
 *@description:
 */
public class ScriptLocalVar {
    public static void main(String[] args) throws ScriptException {
        /**
         *注意这里只是把它当做局部变量或者是全局变量。并不是我脚本写在方法里面就是局部变量
         * 只是如果我们定义为全局变量那么其他方法可以用，否则不能用，因为我可以写多个函数，这时候就是多个方法。
         */
        ScriptEngineManager scriptEngineManager=new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("js");
        Bindings bindings = engine.createBindings();
        bindings.put("x", "local-var");
        engine.setBindings(bindings,ScriptContext.GLOBAL_SCOPE);
        engine.eval("print(x)");
        /**GLOBAL_SCOPE是全局变量
         * ENGINE_SCOPE是局部变量
         */
    }
}
