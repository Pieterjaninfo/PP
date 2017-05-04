package pp.block2.cp.queue;

import pp.block2.cp.queue.UnsafeLinkedQueue;
import pp.block2.cp.queue.QueueEmptyException;

public class Main {
    public static void main(String[] args) throws QueueEmptyException {
        UnsafeLinkedQueue queue = new UnsafeLinkedQueue();

        System.out.println("Length queue: " + queue.getLength());

        queue.push("Hey");
        queue.push("TOPPA DA MORNING MYLADIES!");
        queue.push(23);


        System.out.println("Item: " + queue.pull());
        System.out.println("Length queue: " + queue.getLength());

        System.out.println("Item: " + queue.pull());
        System.out.println("Length queue: " + queue.getLength());

        System.out.println("Item: " + queue.pull());
        System.out.println("Length queue: " + queue.getLength());

    }
}
