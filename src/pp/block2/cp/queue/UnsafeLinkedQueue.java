package pp.block2.cp.queue;

public class UnsafeLinkedQueue implements Queue {

    private Node root = null;
    private int length = 0;

    @Override
    public void push(Object x) {
        Node newNode = new Node(x);
        if (getLength() == 0) {
            root = newNode;
        } else {
            Node start = root;
            while (start.getNext() != null) {
                start = start.getNext();
            }
            start.setNext(new Node(x));
        }
        length++;
    }

    @Override
    public Object pull() throws QueueEmptyException {
        if (getLength() == 0) {
            throw new QueueEmptyException();
        }
        Object value = root.value;
        this.root = root.getNext();

        length--;
        return value;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    public int getLengthLinearly() {
        Node start = root;
        if (root == null) {
            return 0;
        }
        int size = 1;
        while (start != null && start.getNext() != null) {
            start = start.getNext();
            size++;
        }
        return size;
    }


    public class Node {
        private Object value;
        private Node next;


        public Node(Object x) {
            this.value = x;
            this.next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


}
