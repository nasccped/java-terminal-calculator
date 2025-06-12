import io.IO;

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
        String welcomeTitle = String.format("Welcome to the %s%s%s!",
                                            "\u001b[1;38;5;153m",
                                            "Java-Terminal-Calculator",
                                            "\u001b[0m");
        io.println(welcomeTitle);
        String name = io.promptedInput("Say your name: ");
        if (name.strip().equalsIgnoreCase("Heisenberg")) {
            io.println("\u001b[1;32mWe need to cook\u001b[0m -_-");
        } else {
            io.println("Hi, " + name + "!");
        }
        io.println();
    }
}
