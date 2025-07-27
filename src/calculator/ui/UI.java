package calculator.ui;

import io.IO;
import calculator.evaluator.expression.ExpressionStatus;
import calculator.evaluator.expression.ExpressionResult;

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
        int[] errorRange = result.getErrorRange();
        String expressionRow = getExpressionRow(result.getExpression(),
                                                errorRange);
        String underscoreRow = getUnderscoreRow(errorRange);
        String statusRow = getStatusRow(result.getStatus(),
                                        result.getResult());
        io.println(expressionRow);
        io.println(underscoreRow);
        io.println(statusRow);
        io.print(RESET);
    }

    /**
     * If the error mark should be ignored.
     */
    private static boolean avoidErrorMark(int[] errorRange) {
        return (errorRange[0] < 0 || errorRange[1] <= errorRange[0]);
    }

    /**
     * Apply escape styling to the expression (ie, when errors occurs).
     */
    private static String getExpressionRow(String expression,
                                           int[] errorRange) {
        return String.format("%sExpression: %s%s",
                             WHITE,
                             paintExpression(expression, errorRange),
                             RESET);
    }

    /**
     * Paints the given expression by it's error range
     */
    private static String paintExpression(String expression,
                                          int[] errorRange) {
        if (avoidErrorMark(errorRange)) return expression;
        StringBuffer buffer = new StringBuffer(expression);
        buffer.insert(errorRange[1], WHITE);
        buffer.insert(errorRange[0], RED);
        return buffer.toString();
    }

    /**
     * Gets the expression underscore mark that points to the error
     * (like rust compiler :^D)
     */
    private static String getUnderscoreRow(int[] errorRange) {
        if (avoidErrorMark(errorRange)) return "";

        StringBuffer buffer = new StringBuffer("            ");
        buffer.append(RED);
        buffer.append("-".repeat(errorRange[0]));
        buffer.append("^".repeat(errorRange[1] - errorRange[0]));
        buffer.append(RESET);
        return buffer.toString();
    }

    /**
     * Get the output status by using a big ternary oper...
     */
    private static String getStatusRow(ExpressionStatus status,
                                       double valueResult) {
        return status.isOk()
            ? String.format("%sstts[ %sOK%s ]: %f%s", WHITE, GREEN, WHITE,
                            valueResult, RESET)
            : status.isWelcome()
                ? String.format("%sstts[%sNOTE%s]: %s%s", WHITE, BLUE, WHITE,
                                status.unwrapMessage(), RESET)
                : String.format("%sstts[%sFAIL%s]: %s%s", WHITE, RED, WHITE,
                                status.unwrapMessage(), RESET);
    }
}
