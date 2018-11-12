package unlock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @fileName:AtomicTest
 * @author:xy
 * @date:2018/11/10
 * @description:
 */
public class AtomicTest {
  @Test
  public void test() {
      /**
       * getAndSet 这个类似  y=i++，或者你记忆为先get后set
       */
    AtomicInteger atomicInteger = new AtomicInteger();
    atomicInteger.set(100);
    System.out.println(atomicInteger.getAndSet(500));
    System.out.println(atomicInteger.get());
  }
}
