package pp.block6.cp;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.ThreadNumber;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentRunner.class)
public class LockFreeStackTest {
    private static final int AMOUNT_OF_THREADS = 10000;
    MyLockFreeStack stack;

    @Before
    public void setup() {
         stack = new MyLockFreeStack();
    }


    @Test
    @Threaded(count = AMOUNT_OF_THREADS)
    public void addItems(@ThreadNumber int threadNumber) {
        stack.push(threadNumber);
        stack.pop();
    }


    @After
    public void testCount() {
        int size = stack.getLength();
        Assert.assertEquals(0, size);
    }

}
