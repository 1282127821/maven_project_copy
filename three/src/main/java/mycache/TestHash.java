package mycache;

/**
 * @fileName:TestHash
 * @author:xy
 * @date:2018/11/11
 * @description:
 */
public class TestHash {
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
}
