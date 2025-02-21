package nasccped.jtc;

import nasccped.jtc.utils.Visuals;
import nasccped.jtc.utils.IO;

public class JavaTermCalc {

    private static final IO io = new IO();
    private static final Visuals vsl = new Visuals();

    public static void main(String[] args) {
        vsl.clearTerminal();
        io.println();
        io.println("  Hello, calculator,");
        io.println("  From a \u001b[1;33mnew branch(dev)\u001b[0m!");
        io.println();
    }
}
