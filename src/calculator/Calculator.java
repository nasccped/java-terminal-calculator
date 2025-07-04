package calculator;

import io.IO;
import calculator.inputhandler.InputNormalizer;
import calculator.tokenizer.TokenKind;

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

    /**
     * Calculator class 'main' function
     */
    public static void run(IO ioObject) {
        io = ioObject;
        String inp = "";

        // polished String loop testing
        do {
            io.clearTerm();
            io.println("Current Input: " + inp);
            // io.println("Token Kind: " + );
            io.println();

            io.println();
            inp = InputNormalizer.normalize(io.promptedInput("Use ':quit' to abort: "));
        } while (!inp.equalsIgnoreCase(":quit"));

    }
}
