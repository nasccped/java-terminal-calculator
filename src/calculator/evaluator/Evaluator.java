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

    private static final int[] defaultErrorRange = {-1, -1};

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
        setOn.setErrorRange(defaultErrorRange);

        List<Token> tkList = InputNormalizer.strToTokenList(normalized);

        if (Checker.invalidToken(tkList, setOn)) {
            setOn.setStatus(ExpressionStatus.ERR_INVALID_TOKEN);
            return;
        }
        if (Checker.invalidParenthesis(tkList, setOn)) {
            setOn.setStatus(ExpressionStatus.ERR_MISSING_PAREN);
            return;
        }
        if (Checker.invalidOperPos(tkList, setOn)) {
            setOn.setStatus(ExpressionStatus.ERR_OPER_AT_SIDES);
            return;
        }
        // others tests needed
    }
}
