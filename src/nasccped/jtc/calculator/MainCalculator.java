package nasccped.jtc.calculator;

import nasccped.jtc.utils.IO;
import nasccped.jtc.utils.StringUtils;
import nasccped.jtc.utils.Visuals;

public class MainCalculator {

    private final IO io = new IO(2);
    private final StringUtils stut = new StringUtils();
    private final CalcUI cui = new CalcUI();
    private final Visuals vsl = new Visuals();

    public void init() {
        cui.showUI();
        io.println("This is the terminal calculator!");
        io.println(stut.concat("I'm still ",
                               vsl.getBodyColorByName("white"),
                               "working on it!",
                               vsl.getBodyColorByName("reset")));
        io.println();
    }
}
