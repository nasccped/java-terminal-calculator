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

    /**
     * Calculator class 'main' function
     */
    public static void run(IO ioObject) {
        // set and run for testing
        io = ioObject;
        io.println("Log from Calculator class!");
    }
}
