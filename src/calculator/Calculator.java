package calculator;

import io.IO;

/**
 * Operation runner class
 *
 * <p>This class is responsible for taking user's input and handle the output.
 *
 * Works as:
 *  - take user input (String)
 *  - handle input (command | expression)
 *  - display the respective output
 *  </p>
 */
public class Calculator {

    // inner io object
    private static IO io;

    // store polished userInput
    private static String userInput;

    /**
     * Calculator class 'main' function
     */
    public static void run(IO ioObject) {
        io = ioObject;

        // polished String loop testing
        do {
            io.clearTerm();

            io.print("Previous input: ");
            io.println(String.format("\u001b[1;97;44m%s\u001b[0m\n",
                                     userInput == null ? "None" : userInput));

            userInput = io.promptedInput("Say something - "
                                         + "'\u001b[3;92m:quit\u001b[0m' "
                                         + "to exit: ");
        } while (!userInput.equalsIgnoreCase(":quit"));

        // bye log
        io.clearTerm();
        io.println("\nbye\n");
    }
}
