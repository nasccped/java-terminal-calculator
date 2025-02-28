package nasccped.jtc;

import nasccped.jtc.calculator.Calculator;

public class JavaTermCalc {

    private static final Calculator calc = new Calculator();

    public static void main(String[] args) {

        vsl.clearTerminal();
        calc.showUI();
    }
}
