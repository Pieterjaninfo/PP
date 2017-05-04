package pp.block2.cp.test;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pp.block2.cp.sequence.UnsafeSequence;


@RunWith(ConcurrentRunner.class)
public class UnsafeSequenceTest {

	private static final int AMOUNT_OF_THREADS = 50;
	private static final int LOOPS = 10000;

	private UnsafeSequence unsafeSequence;

	@Before
	public void before() {
		this.unsafeSequence = new UnsafeSequence();
	}


	@Test
	@Threaded(count = AMOUNT_OF_THREADS)
	public void checkCountUnsafe() {
		for (int i = 0; i < LOOPS; i++) {
			unsafeSequence.getNext();
		}
	}


	@After
	public void after() {
		Assert.assertEquals(AMOUNT_OF_THREADS * LOOPS, unsafeSequence.getValue());
	}

}
