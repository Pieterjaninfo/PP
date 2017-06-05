package pp.block4.cp.deadlock;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class MultiLinkedList<T> implements Queue<T> {
    private ConcurrentLinkedQueue queue = null;
    private Map<Long, LinkedList<T>> lists = new ConcurrentHashMap<>();



    public void push(T x) {
        long threadid = Thread.currentThread().getId();
        if (lists.containsKey(threadid)) {
            lists.put(threadid, new LinkedList<>());
        }
        lists.get(threadid).addLast(x);
    }

    public T pull() {
        long threadid = Thread.currentThread().getId();

        T val = null;
        while (val == null) {
            for (long key : lists.keySet()) {
                T nval = lists.get(key).peek();
                if (nval != null) {
                    synchronized (lists.get(key)) {
                        if (lists.get(key).peek() == nval) {
                            val = lists.get(key).pop();
                            break;
                        }
                    }
                }
            }
        }
        return val;
    }


    public int getLength() {
        int val = 0;
        for (LinkedList<T> list : lists.values()) {
            val += list.size();
        }
        return val;
    }


    @Override
    public T poll() {
        return null;
    }
}
