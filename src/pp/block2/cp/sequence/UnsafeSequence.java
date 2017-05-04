package pp.block2.cp.sequence;

//@NotThreadsafe
public class UnsafeSequence {
    private int value = 0;

    /** Returns a unique value. */
    public int getNext() {
        return value++;
    }

    public int getValue() {
        return value;
    }
}
