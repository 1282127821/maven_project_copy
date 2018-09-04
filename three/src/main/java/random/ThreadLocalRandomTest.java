package random;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @fileName:ThreadLocalRandom
 * @author:xy
 * @date:2018/8/31
 * @description:
 */
public class ThreadLocalRandomTest {
    @Test
    public void test1(){
        /**
         *
         */
        Random random=new Random();
        random.nextInt(10);

        int i = ThreadLocalRandom.current().nextInt(10);
        System.out.println(i);
    }
}
