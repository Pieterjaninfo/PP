package pp.block6.cp;

import java.util.concurrent.atomic.AtomicReference;

public class MyLockFreeStack<T> implements LockFreeStackInterface<T> {

    /*
     * Can be made bounded if there is a AtomInteger count where when pushing items,
     * it needs to be atomically incremented/decremented whenever push/pop is called,
     * and also instead of a linked-list idea, it is better to have a object array to store the items.
     */

    /*
     * Lock-free programming problems:
     * ABA problem: thread 1 reads 'A', thread 1 calculates stuff for 'A'
     *              thread 2 changes to 'B', thread 1 fails compareAndSet,
     *              thread 2 changes variable to 'A'
     *      this can go wrong when variable is not just a value (reference)
     */

    AtomicReference<Node<T>> head = new AtomicReference<>();

    @Override
    public void push(T x) {
        Node<T> newHead = new Node<>(x);
        Node<T> oldHead;

        do {
            oldHead = head.get();
            newHead.next = oldHead;
        } while(!head.compareAndSet(oldHead, newHead));
    }

    @Override
    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;

        do {
            oldHead = head.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    @Override
    public int getLength() {
        if (head.get() == null) {
            return 0;
        }

        int length = 1;
        Node<T> node = head.get();
        while (node.next != null) {
            length++;
            node = node.next;
        }
        return length;
    }


    static class Node<T> {
        final T item;
        Node<T> next;
        public Node (T item) {
            this.item = item;
        }
    }
}
