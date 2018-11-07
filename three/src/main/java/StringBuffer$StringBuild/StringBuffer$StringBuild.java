package StringBuffer$StringBuild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @fileName:StringBuffer$StringBuild
 * @author:xy
 * @date:2018/9/6
 * @description:
 */
public class StringBuffer$StringBuild {
    public static void main(String[] args) {
        Map<Integer,List<Integer>> map=new HashMap();
        List<Integer> integers = map.computeIfAbsent(1, (k) -> new ArrayList<>());
        System.out.println(integers);
//          StringBuffer
//        StringBuilder
    }
}
