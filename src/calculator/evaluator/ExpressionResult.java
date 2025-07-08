package calculator.evaluator;

/**
 * Store all the expression output data (expression itself, the result, the
 * status and 'error-at')
 *
 * <p>
 * This class is used by the UI class at `calculator.ui` package. It's usefull
 * when printing not only the final result, but also the expression info.
 * </p>
 */
public class ExpressionResult {

    // expression input (post-normalize)
    private String expression;
    // result of the evaluated expression
    private double result;
    // status of the given expression ( OK | ERR_<ERROR_TYPE> )
    private ExpressionStatus status;

    // error occurs between (start..end) (both -1 if no error)
    private int errorStart;
    private int errorEnd;

    /**
     * Generate a ExpressionResult with its default values
     *
     * <p>
     * This function is used only at Calculator.run() ('calculator' package).
     *
     * It generates default values when the program starts. The inner values
     * can only be changed using the setter function 'setNewValues'.
     * </p>
     */
    public ExpressionResult() {
        this.expression = "";
        this.result = 0.0;
        this.status = ExpressionStatus.WELCOME;
        this.errorStart = -1;
        this.errorEnd = -1;
    }

    public String getExpression() { return expression; }

    public int getErrorStart() { return errorStart; }

    public int getErrorEnd() { return errorEnd; }

    public ExpressionStatus getStatus() { return status; }

    public double getResult() { return result; }

    /**
     * Set new values to this object
     *
     * <p>
     * This function is only used by the 'Evaluator' class of this same
     * package. It explain the reason of the protected visibility. Outside
     * classes can't change this class by his own.
     * </p>
     */
    protected void setNewValues(String newExp,
                                double newRes,
                                ExpressionStatus newStts,
                                int newErrorStrt,
                                int newErrorEnd) {
        expression = newExp;
        result = newRes;
        status = newStts;
        errorStart = newErrorStrt;
        errorEnd = newErrorEnd;
    }

}
