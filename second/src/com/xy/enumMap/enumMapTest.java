/**
 * @fileName:enumMapTest
 * @author:xy
 * @date:2018/8/13
 * @description:
 */
package com.xy.enumMap;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * @fileName:enumMapTest
 * @author:xy
 * @date:2018/8/13
 * @description:
 */
public class enumMapTest {
  public static void main(String[] args) {
      /**
       *enumMap 是一个很好用的枚举map，看名字就知道是操作枚举的。
       */
      Map map=new EnumMap(EnumClass.class);
      map.put(EnumClass.ONE,101);
      map.put(EnumClass.TWO,102);
      map.put(EnumClass.THREE,103);
      System.out.println(map.get(EnumClass.THREE));


  }
}

enum EnumClass {
  /** 1 */
  ONE,
  /** 2 */
  TWO,
  /** 3 */
  THREE
}
