package JavaParser源码解析工具;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;

/**
 * @fileName:Test
 * @author:xy
 * @date:2018/9/7
 * @description:
 */
public class Test extends Thread implements Serializable {
    private String myName;
    private int age;

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String m(){
        return RandomStringUtils.random(10);
    }

}
