package pp.block2.cp.test;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pp.block2.cp.sequence.SafeSequence;


@RunWith(ConcurrentRunner.class)
public class SafeSequenceTest {

	private static final int AMOUNT_OF_THREADS = 50;
	private static final int LOOPS = 100000;

	private SafeSequence safeSequence;

	@Before
	public void before() {
		this.safeSequence = new SafeSequence();
	}

	@Test
	@Threaded(count = AMOUNT_OF_THREADS)
	public void checkCountSafe() {
		for (int i = 0; i < LOOPS; i++) {
			safeSequence.getNext();
		}
	}

	@After
	public void after() {
		Assert.assertEquals(AMOUNT_OF_THREADS * LOOPS, safeSequence.getValue());
	}

}
