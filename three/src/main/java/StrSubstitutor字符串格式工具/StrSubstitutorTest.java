package StrSubstitutor字符串格式工具;

import com.sun.javafx.runtime.SystemProperties;
import org.apache.commons.lang3.AnnotationUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookup;
import org.apache.commons.text.lookup.StringLookupFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @fileName:StrSubstitutorTest
 * @author:xy
 * @date:2018/9/3
 * @description:
 */
public class StrSubstitutorTest {
    /**
     * lang3和 commons-text 的一些我以前没用过的工具
     * RandomStringUtils 是随机生成字符串用的工具类
     * AnnotationUtils 获取注解的工具
     * StringSubstitutor用来 字符串的占位符处理
     * 其中StringSubstitutor#replace支持 map 类型 和Properties类型（他们都是key-value）
     *一般 replace 只支持 ${}  其实他还支持 支持自定义前后缀
     * replaceSystemProperties：是用作获取系统属性的，一开始我从百度找到 java.version 和os.name (os  option system 操作系统)
     * 但是后来我在想这些属性应该是内部内置好的，debug 太难了找不到。后来我一想这是apache的，应该它的获取系统属性的工具类应该可以被他利用吧
     * 结果我就找到了 SystemUtils(lang3的类)，我发现一个特点只要这里面有的 ，都可以转换成对应的小写 用replaceSystemProperties拿到！
     *如 JAVA_RUNTIME_NAME --->${java.runtime.name} 把所有的大写转换为小写，然后下划线变为 点 .
     * 这是我的总结，百度 不是万能的！！
     */
    @SuppressWarnings("")
    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomAlphabetic(5));
        System.out.println(RandomStringUtils.randomNumeric(5));
        String source="你好${name}我来自${country}!";
        Map map=new HashMap();
        map.put("name", "xy");
        map.put("country", "中国");
        String replace = StringSubstitutor.replace(source, map);
        System.out.println(replace);
        String source1="默认只能解析${xxx},但是也可以自定义指定前后缀如：<xy}";
        Map map1=new HashMap();
        map1.put("xy", "肖咏");
        String replace1 = StringSubstitutor.replace(source1, map1, "<", "}");
        System.out.println(replace1);
        String source2="当前系统java版本：${java.version},当前操作系统：${os.name}" +
                "java安装路径：${java.home}，当前项目路径：${user.dir}，运行时名称：${java.runtime.name}";
        String replaceSystemProperties = StringSubstitutor.replaceSystemProperties(source2);
        System.out.println(replaceSystemProperties);
//        SystemUtils
    }
}
