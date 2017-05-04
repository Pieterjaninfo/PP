package pp.block2.cp.timer;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.Threaded;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pp.block2.cp.queue.SafeLinkedQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@RunWith(ConcurrentRunner.class)
public class TimerTasksTest {

    private static final int AMOUNT_OF_THREADS = 10;
    private static final int AMOUNT_OF_TIMERTASKS = 1000;

    private static int shared = 0;
    private Timer timer;
    private List<TimerTask> timerTasks;

    @Before
    public void before() {
        this.timer = new Timer();
        this.timerTasks = new ArrayList<>();
        fill_timertasks();

    }

    private void fill_timertasks() {
        for (int i = 0; i < AMOUNT_OF_TIMERTASKS; i++) {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    shared++;
                }
            };
            timer.schedule(timerTask, 10);
            timerTasks.add(timerTask);
        }
    }

    @Test
    public void executeTimerTasks() {
        for (TimerTask timerTask : timerTasks) {
            timerTask.run();
        }
    }


    @After
    public void after() {
        System.out.println("The shared variable has been updated " + shared +  " times.");
        Assert.assertEquals(AMOUNT_OF_TIMERTASKS, shared);
    }

}
