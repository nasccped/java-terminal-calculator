/**
 * The program entrypoint
 *
 * <p>The JavaTermCalc will create a new IO object and call other project
 * operations.</p>
 */
public class JavaTermCalc {

    /**
     * Main IO obj from
     *
     * <p>Should be static and will be used on the entire program (this
     * instance only).</p>
     */
    private static final IO io = new IO(2);

    public static void main(String[] args) {
        String projectName = "JavaTermCalc";
        io.println("Welcome to " + projectName);
        io.println();
        io.setNextPadding(4);
        io.print("This is a ");
        io.print("printing with four ");
        io.println("tab spaces!");
        io.println("And now, it's normal");
    }
}
