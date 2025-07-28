package calculator;

import io.IO;
import calculator.evaluator.Evaluator;
import calculator.evaluator.expression.ExpressionResult;
import calculator.ui.UI;

/**
 * Operation runner class
 *
 * <p>This class is responsible for taking user's input and handle the output.
 *
 * Works as:
 *  - take user input (String)
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
        String input = "";
        ExpressionResult expRes = new ExpressionResult();

        while (!input.equalsIgnoreCase(":quit")) {
            io.clearTerm();
            UI.printPannel(io, expRes);
            io.println();
            input = io.promptedInput("> ");
            Evaluator.setExpressionResult(input, expRes);
        }
    }
}
