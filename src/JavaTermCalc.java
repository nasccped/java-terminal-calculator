import io.IO;
import calculator.Calculator;

/**
 * The program entrypoint
 *
 * <p>The JavaTermCalc will create a new IO object and call other project
 * operations.</p>
 */
public class JavaTermCalc {

    /**
     * Main IO obj
     *
     * <p>Should be static and will be used on the entire program (this
     * instance only).</p>
     */
    private static IO io;

    public static void main(String[] args) {
        try {
            io = IO.builder()        // new keyword isn't necessary
                .defaultPrompt("> ") // the object is created within a
                .identPadding(2)     // static method
                .build();
        } catch (Exception e) {
            System.out.println("Error trying to create the IO object:");
            System.out.println("> \u001b[1;91m" + e.getMessage() + "\u001b[0m");
            return;
        }
        Calculator.run(io);
    }
}
