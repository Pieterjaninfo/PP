package pp.block3.cp.lockcoupling;

import pp.block2.cc.Symbol;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLinkedList extends Thread implements List{

    private final LinkedList<Object> list = new LinkedList<>();
    private final Object lock = new Object();

    @Override
    public void insert(int pos, Object val) {
        list.add(pos, val);
    }

    @Override
    public void add(Object val) {
        list.add(val);
    }

    @Override
    public void remove(Object item) {
        list.remove(item);
    }

    @Override
    public void delete(int pos) {
        list.remove(pos);
    }

    @Override
    public int size() {
        return list.size();
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        MyLinkedList l1 = new MyLinkedList();
        MyLinkedList l2 = new MyLinkedList();
        MyLinkedList l3 = new MyLinkedList();

        long time_start = System.currentTimeMillis();
        for (int i = 0; i < 2000000; i++) {
            l3.add(i);
        }
        System.out.println("SEq Adding items stook " + (System.currentTimeMillis() - time_start) + " milliseconds.");


        long time_start2 = System.currentTimeMillis();

        l1.start();
        l2.start();

        try {
            l1.join();
            l2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("THREAD Adding items stook " + (System.currentTimeMillis() - time_start2) + " milliseconds.");




    }
}
