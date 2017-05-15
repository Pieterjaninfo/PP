package pp.block3.cc.symbol;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MySymbolTable implements SymbolTable {
    private Deque queue = new ArrayDeque<Set<String>>();

    @Override
    public void openScope() {
        this.queue.addFirst(new HashSet<>());
    }

    @Override
    public void closeScope() {
        this.queue.removeLast();
    }

    @Override
    public boolean add(String id) {
//        this.queue.getLast().add





        return false;
    }

    @Override
    public boolean contains(String id) {
        return false;
    }
}
