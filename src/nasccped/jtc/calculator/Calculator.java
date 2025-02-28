package nasccped.jtc.calculator;

import nasccped.jtc.utils.IO;
import nasccped.jtc.utils.StringUtils;
import nasccped.jtc.utils.Visuals;

public class Calculator {

    private final IO io = new IO(2);
    private final Visuals vsl = new Visuals();
    private final StringUtils stut = new StringUtils();

    public void showUI() {

        io.println();
        io.println(stut.concat(vsl.boldMagenta,
                               "Java Terminal Calculator",
                               vsl.resetEscape));

        
        io.println(stut.concat(vsl.boldMagenta,
                               "========================",
                               vsl.resetEscape));
        io.println();

        io.println("This is the terminal calculator!");
        io.println(stut.concat("I'm still ",
                               vsl.boldWhite,
                               "working on it!",
                               vsl.resetEscape));

        io.println();
    }
}
