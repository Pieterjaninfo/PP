package pp.block2.cp.test;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pp.block2.cp.sequence.SafeSequence;

import java.text.SimpleDateFormat;


@RunWith(ConcurrentRunner.class)
public class SimpleDateFormatTest {

	private static final int AMOUNT_OF_THREADS = 50;
	private static final int LOOPS = 100000;

	private SimpleDateFormat dateFormat;

	@Before
	public void before() {
		this.dateFormat = new SimpleDateFormat();
	}

	@Test
	@Threaded(count = AMOUNT_OF_THREADS)
	public void checkCountSafe() {

	}

	@After
	public void after() {
		Assert.assertEquals();
	}

}
