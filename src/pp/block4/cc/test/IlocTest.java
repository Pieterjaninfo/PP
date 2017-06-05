package pp.block4.cc.test;

//import org.junit.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pp.iloc.Assembler;
import pp.iloc.Simulator;
import pp.iloc.eval.Machine;
import pp.iloc.model.Program;
import pp.iloc.parse.FormatException;

import java.io.File;
import java.io.IOException;

public class IlocTest {

    private Assembler assembler_maxiloc;
    private Assembler assembler_fibreg;
    private Assembler assembler_fibmem;
    private Program prog_maxiloc;
    private Program prog_fibreg;
    private Program prog_fibmem;


    @Before
    public void setup() {
        this.assembler_maxiloc = new Assembler();
        this.assembler_fibreg = new Assembler();
        this.assembler_fibmem = new Assembler();

        File max_iloc_file = new File("src/pp/block4/cc/iloc/max.iloc");
        File file_fibreg = new File("src/pp/block4/cc/iloc/fib-register");
        File file_fibmem = new File("src/pp/block4/cc/iloc/fib-memory");
        try {
            this.prog_maxiloc = assembler_maxiloc.assemble(max_iloc_file);
            this.prog_fibreg = assembler_fibreg.assemble(file_fibreg);
            this.prog_fibmem = assembler_fibmem.assemble(file_fibmem);
        } catch (FormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void asembleTest() {
//        System.out.println(prog_maxiloc.prettyPrint());

        String result = prog_maxiloc.toString().replaceAll("\\s", "");
        String max_iloc = "start: loadI 0 => r_max           // Line 1: max = 0;\n" +
                "       loadI 0 => r_i             // Line 2: int i = 0;\n" +
                "       loadI @alength => r_len\n" +
                "while: cmp_LT r_i,r_len => r_cmp  // Line 3: while (i < a.length)\n" +
                "       cbr r_cmp -> body, end\n" +
                "body:  i2i r_i => r_a             // compute address of a[i]\n" +
                "       multI r_a,4 => r_a         // multiply by size of int\n" +
                "       addI r_a,@a => r_a         // add a's base offset\n" +
                "       loadAO r_arp,r_a => r_ai   // r_ai <- a[i]\n" +
                "       cmp_GT r_ai,r_max => r_cmp // Line 4: if (a[i] > max)\n" +
                "       cbr r_cmp -> then,endif\n" +
                "then:  i2i r_ai => r_max          // Line 5: max = a[i];\n" +
                "endif: addI r_i,1 => r_i          // Line 7: i = i + 1;\n" +
                "       jumpI -> while\n" +
                "end:   out \"Max: \", r_max         // Line 9: out; not \"official ILOC\"";
        Assert.assertEquals(max_iloc.replaceAll("\\s", ""), result);
    }

    @Test
    public void simulationTest_maxiloc() {
        Simulator simulator = new Simulator(prog_maxiloc);
        Machine machine = simulator.getVM();
        machine.setNum("alength", 5);
        machine.init("a", 1, 2, 3, 6, 4);

        simulator.run();
        int result = simulator.getVM().getReg("r_max");
        Assert.assertEquals(6, result);
    }



    @Test
    public void simulationTest_fibreg() {
        Simulator simulator = new Simulator(prog_fibreg);
        Machine machine = simulator.getVM();
        machine.init("n", 10);

        simulator.run();
        int result = simulator.getVM().getReg("r_z");
        Assert.assertEquals(89, result);
    }


    @Test
    public void simulationTest_fibmem() {
        Simulator simulator = new Simulator(prog_fibmem);
        Machine machine = simulator.getVM();
        machine.init("n", 10);
        machine.init("x", 1);
        machine.init("y", 1);
        machine.init("z", 1);

        simulator.run();
        int result = simulator.getVM().getReg("r_z");
        Assert.assertEquals(89, result);
    }




}