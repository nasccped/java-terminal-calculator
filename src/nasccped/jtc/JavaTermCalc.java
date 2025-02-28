package nasccped.jtc;

import nasccped.jtc.calculator.Calculator;
import nasccped.jtc.utils.Visuals;

public class JavaTermCalc {

    private static final Calculator calc = new Calculator();
    private static final Visuals vsl = new Visuals();

    public static void main(String[] args) {

        vsl.clearTerminal();
        calc.showUI();
    }
}
