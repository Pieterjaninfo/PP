package pp.block3.cc.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pp.block3.cc.symbol.ErrorChecker;

import java.util.Arrays;
import java.util.List;

public class TestDeclUseErrorChecker {
    private ErrorChecker errorChecker;
    private String path = "src/pp/block3/cc/symbol/";

    @Before
    public void initTable() { this.errorChecker = new ErrorChecker(); }

    @Test
    public void testErrors() {
        List<String> errors1 = yields(path + "prog1");
        List<String> errors2 = yields(path + "prog2");
        List<String> errors3 = yields(path + "prog3");
        List<String> errors4 = yields(path + "prog4");

        Assert.assertEquals(1, errors1.size());
        Assert.assertEquals("Variable 'D:noot' already declared in scope at line 1:16", errors1.get(0));

        Assert.assertEquals(0, errors2.size());
        Assert.assertEquals(2, errors3.size());
        Assert.assertEquals("Variable 'U:noot' used out of scope at line 1:8", errors3.get(0));
        Assert.assertEquals("Variable 'U:random' used out of scope at line 1:16", errors3.get(1));
        Assert.assertEquals(0, errors4.size());

    }

    private List<String> yields(String file) {
        return errorChecker.checkErrors(file);
    }

}
