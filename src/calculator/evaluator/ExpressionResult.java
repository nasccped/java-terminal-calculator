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
    private int[] errorRange;

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
        this.errorRange = new int[2];
        this.errorRange[0] = -1;
        this.errorRange[1] = -1;
    }

    public String getExpression() { return expression; }

    protected void setExpression(String exp) { expression = exp; }

    public int[] getErrorRange() { return errorRange; }

    public void setErrorRange(int[] newIdxs) {
        errorRange[0] = newIdxs[0];
        errorRange[1] = newIdxs[1];
    }

    public ExpressionStatus getStatus() { return status; }

    protected void setStatus(ExpressionStatus stts) { status = stts; }

    public double getResult() { return result; }

    protected void setResult(double res) { result = res; }
}
