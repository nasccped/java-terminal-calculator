package calculator.ui;

import io.IO;
import calculator.evaluator.ExpressionStatus;
import calculator.evaluator.ExpressionResult;

/**
 * Panel printing class
 *
 * <p>
 * NOTE: this class will be changed. This is just an experimental
 *       implementation for visual purposes...
 * </p>
 */
public class UI {
    // static escapes for print coloring
    private static final String
        RESET = "\u001b[0m" ,
        RED   = "\u001b[91m",
        GREEN = "\u001b[92m",
        BLUE  = "\u001b[94m",
        WHITE = "\u001b[97m";

    /**
     * UI main function
     *
     * <p>
     * Takes the IO object, unwrap the inner values from the ExpressionResult
     * and print it to the screen.
     * </p>
     */
    public static void printPannel(IO io, ExpressionResult result) {
        // get error indexes
        int errStart = result.getErrorStart(),
            errEnd = result.getErrorEnd();
        // get expression, underscores and status output
        String expressionRow = getExpressionRow(result.getExpression(),
                                                errStart,
                                                errEnd);
        String underscoreRow = getUnderscoreRow(errStart, errEnd);
        String statusRow = getStatusRow(result.getStatus(),
                                        result.getResult());
        // print them
        io.println(expressionRow);
        io.println(underscoreRow);
        io.println(statusRow);
        // reset the escape (if not yet reseted)
        io.print(RESET);
    }

    /**
     * If the error mark should be ignored
     */
    private static boolean avoidErrorMark(int errStart, int errEnd) {
        return (errStart < 0 || errEnd <= errStart);
    }

    private static String getExpressionRow(String expression,
                                           int errStart,
                                           int errEnd) {
        return String.format("%sExpression: %s%s",
                             WHITE,
                             paintExpression(expression,
                                             errStart,
                                             errEnd),
                             RESET);
    }

    /**
     * Paints the given expression by it's error range
     */
    private static String paintExpression(String expression,
                                          int errorStart,
                                          int errorEnd) {
        // if not necessary
        if (avoidErrorMark(errorStart, errorEnd)) return expression;
        // else:
        // create the buffer
        StringBuffer buffer = new StringBuffer(expression);
        // insert end first (NO STRLEN CONFLICTING),
        buffer.insert(errorEnd, WHITE);
        // insert start (red escape)
        buffer.insert(errorStart, RED);
        // return as String
        return buffer.toString();
    }

    /**
     * Gets the expression underscore mark that points to the error
     * (like rust compiler :^D)
     */
    private static String getUnderscoreRow(int errStart, int errEnd) {
        if (avoidErrorMark(errStart, errEnd)) return "";
        StringBuffer buffer = new StringBuffer("            ");
        buffer.append(RED);
        buffer.append("-".repeat(errStart));
        buffer.append("^".repeat(errEnd - errStart));
        buffer.append(RESET);
        return buffer.toString();
    }

    /**
     * Get the output status by using a big ternary oper...
     */
    private static String getStatusRow(ExpressionStatus status,
                                       double valueResult) {
        // if status is OK
        return status.isOk()
            ? String.format("%sstts[ %sOK%s ]: %f%s", WHITE, GREEN, WHITE,
                            valueResult, RESET)
            // but, if status is welcome
            : status.isWelcome()
                ? String.format("%sstts[%sNOTE%s]: %s%s", WHITE, BLUE, WHITE,
                                status.unwrapMessage(), RESET)
                // else:
                : String.format("%sstts[%sFAIL%s]: %s%s", WHITE, RED, WHITE,
                                status.unwrapMessage(), RESET);
    }
}
