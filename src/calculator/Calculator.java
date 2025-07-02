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
    private static String userInput = "";

    /**
     * Calculator class 'main' function
     */
    public static void run(IO ioObject) {
        io = ioObject;
        String tokenString;

        // polished String loop testing
        do {
            io.clearTerm();
            io.println("Current Input: " + userInput);
            io.println();
            for (Tokenizer.TokenType token :
                 Tokenizer.getTokenListFromString(userInput)) {
                tokenString = (token == Tokenizer.TokenType.INVALID_TOKEN
                                ? "\u001b[1;91m" : "\u001b[1;92m")
                              + token + "\u001b[0m";
                io.println("\u001b[1;93m > " + tokenString);
            }

            io.println();
            userInput = io.promptedInput("Use ':quit' to abort: ");
        } while (!userInput.equalsIgnoreCase(":quit"));

    }
}
