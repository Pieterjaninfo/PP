package pp.block2.cp.test;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.ThreadNumber;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pp.block2.cp.sequence.SafeSequence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(ConcurrentRunner.class)
public class SimpleDateFormatTest {

	private static final int AMOUNT_OF_THREADS = 10;

	private SimpleDateFormat dateFormat;
	private static Date date;
	private List<String> dates;

	@Before
	public void before() {
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.dates = new ArrayList<>();
		setupDates();
	}

	private void setupDates() {
		for (int i = 1; i <= AMOUNT_OF_THREADS; i++) {
			String newDate = i + "/01/2010";
			this.dates.add(newDate);
		}
	}

	@Test
	@Threaded(count = AMOUNT_OF_THREADS)
	public void executeThreads(@ThreadNumber int threadNumber) {
		try {
			date = dateFormat.parse(dates.get(threadNumber));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() {
//		Assert.assertEquals();
		System.out.println(date);
	}


}
