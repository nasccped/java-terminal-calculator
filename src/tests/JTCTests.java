import nasccped.jtc.utils.IO;
import nasccped.jtc.utils.Visuals;

public class JTCTests {

    private static final IO io = new IO();
    private static final Visuals vsl = new Visuals();

    public static void main(String[] args) {

        vsl.clearTerminal();
        io.println();
        io.println("Welcome to the \u001b[1;31mJTC testing\u001b[0m section!");
        io.println();
    }
}
