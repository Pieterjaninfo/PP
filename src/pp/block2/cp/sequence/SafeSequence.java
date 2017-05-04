package pp.block2.cp.sequence;


public class SafeSequence {
    private int value;
//    private static Object key;


    public synchronized int getNext() {
        return value++;
    }

    public int getValue() {
        return value;
    }
}
