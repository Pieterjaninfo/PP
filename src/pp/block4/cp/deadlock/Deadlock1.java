package pp.block4.cp.deadlock;


public class Deadlock1 {
    private final Object left = new Object();
    private final Object right = new Object();


    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                System.out.println("Entered leftRight()");
            }
        }
    }


    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                System.out.println("Entered rightLeft()");
            }
        }
    }
}
