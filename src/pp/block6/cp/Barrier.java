package pp.block6.cp;

/**
 * Interface for a cyclic barrier with locks or wait-notify/await-signal mechanism.
 */
public interface Barrier {
    public int await() throws InterruptedException;
}
