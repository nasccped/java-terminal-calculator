package nasccped.jtc.calculator;

import nasccped.jtc.utils.IO;

public class Calculator {

    private final IO io = new IO();

    public void showUI() {

        io.println();
        io.println("\u001b[1;35mCalculator\u001b[0m");
        io.println("\u001b[1;35m==========\u001b[0m");
        io.println();
    }
}
