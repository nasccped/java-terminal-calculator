package nasccped.jtc.calculator;

import nasccped.jtc.utils.IO;
import nasccped.jtc.utils.StringUtils;

public class Calculator {

    private final IO io = new IO(2);
    private final StringUtils stut = new StringUtils();

    public void showUI() {

        io.println();
        io.println(stut.concat("\u001b[1;35m",
                               "Calculator",
                               "\u001b[0m"));

        
        io.println(stut.concat("\u001b[1;35m",
                               "==========",
                               "\u001b[0m"));
        io.println();

        io.println("This is the terminal calculator!");
        io.println(stut.concat("I'm still ",
                               "\u001b[1;37m",
                               "working on it!",
                               "\u001b[0m"));

        io.println();
    }
}
