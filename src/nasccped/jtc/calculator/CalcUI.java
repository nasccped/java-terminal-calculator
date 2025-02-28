package nasccped.jtc.calculator;
import nasccped.jtc.utils.Visuals;
import nasccped.jtc.utils.IO;
import nasccped.jtc.utils.StringUtils;

public class CalcUI {

    private final IO io = new IO(2);
    private final Visuals vsl = new Visuals();
    private final String calculatorTitle = "Java Terminal Calculator";
    private final StringUtils stut = new StringUtils();

    private void printTitle() {
        // title text
        io.println();
        io.println(stut.concat(vsl.boldMagenta,
                               getTitle(),
                               vsl.resetEscape));

        // title bottom border
        io.println(stut.concat(vsl.boldMagenta,
                               "=".repeat(getTitle().length()),
                               vsl.resetEscape));
        io.println();
    }

    public void showUI() {
        vsl.clearTerminal();
        printTitle();
    }

    private String getTitle() {
        return calculatorTitle;
    }
}
