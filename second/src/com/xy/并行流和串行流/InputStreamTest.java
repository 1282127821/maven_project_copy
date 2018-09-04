package com.xy.并行流和串行流;

import java.util.ArrayList;
import java.util.List;

/**
 * @fileName:InputStreamTest
 * @author:xy
 * @date:2018/8/21
 * @description:
 */
public class InputStreamTest {
  public static void main(String[] args) {
    List<User> u1 = new ArrayList<>();
    u1.add(new User("xy", 23));
    u1.add(new User("zw", 24));
    u1.add(new User("gy", 100));
    int sum = u1.stream().mapToInt(User::getAge).sum();
    System.out.println(sum);
  }
}

class User {
  private String name;
  private int age;

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
