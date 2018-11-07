package compare;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @fileName:Main
 * @author:xy
 * @date:2018/8/26
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        List<Step> steps=new ArrayList<Step>();
        Step s1=new Step("a1","a2");
        Step s2=new Step("b1","b2");
        steps.add(s1);
        steps.add(s2);
        //对集合对象进行排序
        StepComparator comparator=new StepComparator();
        Collections.sort(steps, comparator);
        if(steps!=null&&steps.size()>0){
            for(Step step:steps){
                System.out.println(step.getAcceptAddress());
                System.out.println(step.getAcceptTime());
            }
        }
    }
}
