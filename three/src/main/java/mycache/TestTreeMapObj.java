package mycache;

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
            return this.id>o.id?1:-1;
        }
    }
    private TreeMap map=new TreeMap();
    public void put(Object k,int v){
        Inner inner = new Inner(v, "" + v);
        map.put(inner, v);
    System.out.println(map.size());
    }
}
