package 映射原理;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @fileName:ORMTest
 * @author:xy
 * @date:2018/9/3
 * @description:
 */
public class ORMTest {
  public static void main(String[] args) {
      User user=new User();
      String field="name";
      String value="xy";
      setFieldValue(user,field,value);
      System.out.println(user.getName());
  }

    private static void setFieldValue(Object obj, String field, Object value){
        char[] chars = field.trim().toCharArray();
        //这个不难理解，char 的ASCII值就是 数字 -32 就是 对应小写字母的大写字母！！
        chars[0]-=32;
        try {
            Method method = obj.getClass().getMethod("set" + String.valueOf(chars), value.getClass());
            method.invoke(obj,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

class User {
  private String name;
  private int age;
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public int getAge() { return age; }
  public void setAge(int age) { this.age = age; }
}
