package pp.block4.cp.test;


import pp.block4.cp.deadlock.Deadlock1;

public class Deadlock1Test extends Thread {
    private Deadlock1 dl;

    public Deadlock1Test(Deadlock1 dl) {
        this.dl = dl;
    }

    public static void main(String[] args) {
        Deadlock1 dl = new Deadlock1();
        Deadlock1Test test1 = new Deadlock1Test(dl);
        Deadlock1Test test2 = new Deadlock1Test(dl);
        test1.start();
        test2.start();
    }


    @Override
    public void run() {
        dl.leftRight();
        dl.rightLeft();
    }
}
