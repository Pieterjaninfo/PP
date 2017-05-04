package pp.block2.cp.test;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pp.block2.cp.queue.UnsafeLinkedQueue;
import pp.block2.cp.queue.QueueEmptyException;


@RunWith(ConcurrentRunner.class)
public class UnsafeLinkedQueueTest {

    private static final int AMOUNT_OF_THREADS = 20;
    private static final int LOOPS = 1;

    private UnsafeLinkedQueue queue;

    @Before
    public void before() {
        this.queue = new UnsafeLinkedQueue();
    }

    @Test
    @Threaded(count = AMOUNT_OF_THREADS)
    public void addItems() {
        for (int i = 0; i < LOOPS; i++) {
            queue.push(i);
        }
    }

//    @Test
    @Threaded
    public void obtainItems() {
        fillQueue();
        try {
            queue.pull();
        } catch (QueueEmptyException e) {
            Assert.fail("Attempted to pull from queue whilst queue being empty");
        }
    }

    public void fillQueue() {
        for (int i = 0; i < AMOUNT_OF_THREADS; i++) {
            queue.push(i);
        }
    }

    @After
    public void after() {
        // Data race for length
        Assert.assertEquals(queue.getLengthLinearly(), queue.getLength());

        // Correct amount of items added
        Assert.assertEquals(AMOUNT_OF_THREADS * LOOPS, queue.getLength());
    }

}
