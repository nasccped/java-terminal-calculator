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
    private static String userInput = null;

    /**
     * Calculator class 'main' function
     */
    public static void run(IO ioObject) {
        io = ioObject;
        String[] splitedInput = new String[0];

        // polished String loop testing
        do {
            io.clearTerm();
            io.println("Current Input: "
                       + (userInput == null ? "" : userInput));
            io.println();

            for (String val : splitedInput) {
                io.println(String.format("'%s' - %s",
                                         val,
                                         Tokenizer.tokenTypeFrom(val)));
            }

            io.println();
            userInput = io.promptedInput("Use ':quit' to abort: ");
            splitedInput = userInput.split(" ");
        } while (!userInput.equalsIgnoreCase(":quit"));

    }
}
