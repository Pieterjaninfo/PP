package pp.block6.cp;

/**
 * Interface for a lock-free stack.
 */
public interface LockFreeStackInterface<T> {
    public void push(T x);
    public T pop();
    public int getLength();
}
