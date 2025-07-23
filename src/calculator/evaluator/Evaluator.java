package calculator.evaluator;

import calculator.evaluator.inputhandler.InputNormalizer;
import calculator.evaluator.checker.Checker;
import calculator.evaluator.tokenizer.Token;
import java.util.List;

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
        String normalized = InputNormalizer.normalize(express);
        setOn.setStatus(ExpressionStatus.OK);
        setOn.setExpression(normalized);
        setOn.setResult(0.0);
        setOn.setErrorStart(-1);
        setOn.setErrorEnd(-1);

        List<Token> tkList = InputNormalizer.strToTokenList(normalized);
        int[] errInds;
        // test tokens
        errInds = Checker.invalidToken(tkList);
        if (errInds.length > 0) {
            setErrorIndexes(setOn, errInds);
            setOn.setStatus(ExpressionStatus.ERR_INVALID_TOKEN);
            return;
        }
        // test parenthesis
        errInds = Checker.invalidParenthesis(tkList);
        if (errInds.length > 0) {
            setErrorIndexes(setOn, errInds);
            setOn.setStatus(ExpressionStatus.ERR_MISSING_PAREN);
            return;
        }
        // others tests needed
    }

    private static void setErrorIndexes(ExpressionResult setOn, int[] values) {
            setOn.setErrorStart(values[0]);
            setOn.setErrorEnd(values[1]);
    }
}
