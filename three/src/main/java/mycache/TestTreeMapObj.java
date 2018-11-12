package mycache;

import JavaParser源码解析工具.Test;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @fileName:TestTreeMapObj
 * @author:xy
 * @date:2018/11/12
 * @description:
 */
public class TestTreeMapObj {
   class Inner implements Comparable<Inner>{
        private int id;
        private String name;

        public Inner(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(Inner o) {
            return this.id-o.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            return this.id==((Inner)obj).id;

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    private TreeMap map=new TreeMap();
    public void put(Object k,int v){
        Inner inner = new Inner(v, "" + v);
//        TestHash inner=new TestHash(v, "");
        map.put(inner, v);
    System.out.println(map.get(inner));
    }

    public TreeMap getMap() {
        return map;
    }
}
