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
         * FIXME: This isn't the final implementation. It's just for test
         * purposes.
         */

        // fix the expression to a normalized String
        String fixedExpression = InputNormalizer.normalize(express);
        // WARN: this is temporary (set sample values)
        setOn.setNewValues(fixedExpression, 9.0, ExpressionStatus.TEST_CASE, 9, 13);
    }
}
