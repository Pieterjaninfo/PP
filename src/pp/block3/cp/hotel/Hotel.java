package pp.block3.cp.hotel;

import java.util.*;
import java.util.concurrent.locks.*;

class Hotel extends Thread {
    private final static int NR_ROOMS = 10;
    private final Person[] rooms = new Person[NR_ROOMS];
    private final List<Person> queue = new ArrayList<>();
    private final Lock queueLock = new ReentrantLock();

    private boolean occupied(int i) {
        return (rooms[i] != null);
    }

    private int checkIn(Person p) {
        int i = 0;
        while (occupied(i)) {
            i = (i + 1) % NR_ROOMS;
        }
        rooms[i] = p;               //reassignment may occur illegally
        return i;
    }

    private void enter(Person p) {
        queueLock.lock();
        queue.add(p);
        queueLock.unlock();
    }

    // every desk employee should run as a separate thread
    @Override
    public void run() { //hotel should not have
        while (true) {  //polling all the time -wait/notify method
            if (!queue.isEmpty()) { //check must be done synchronized
                queueLock.lock();
                Person guest = queue.remove(0);
                queueLock.unlock(); //in finally clause if person is removed throws exception
                checkIn(guest);
            }
        }
    }
}

//data race; reading writing a piece of data
//race condition; order/matters of execution

class Person { 
    // some appropriate query functions
}

