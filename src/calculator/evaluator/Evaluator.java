package calculator.evaluator;

import calculator.evaluator.inputhandler.InputNormalizer;

/**
 * The expression evaluator entrypoint.
 *
 * <p>
 * This class have just one function (responsible to run all the input and
 * setter stuff).
 * </p>
 */
public class Evaluator {

    /**
     * Take the user input, evaluate and apply the final result into an extern
     * objects (ExpressionResult - from this same package).
     */
    public static void setExpressionResult(String express,
                                           ExpressionResult setOn) {
        /**
         * TODO: this is temporary (set sample values).
         *       I need to evaluate and get the expression results to set in
         *       the ExpressionStatus object.
         */

        // set
        setOn.setExpression(InputNormalizer.normalize(express)); // expression
        setOn.setResult(0.0); // result
        setOn.setStatus(ExpressionStatus.TEST_CASE); // status
        setOn.setErrorStart(-1); // err start
        setOn.setErrorEnd(-1); // err end
    }
}
