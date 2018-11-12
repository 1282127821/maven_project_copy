package mycache;

import JavaParser源码解析工具.Test;

/**
 * @fileName:TestHash
 * @author:xy
 * @date:2018/11/11
 * @description:
 */
public final class TestHash implements Comparable<TestHash>{
    private int id;
    private String name;

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

    public TestHash(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(TestHash o) {
        return this.id-o.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id==((TestHash)obj).id?true:false;
    }
}
