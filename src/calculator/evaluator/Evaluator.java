package calculator.evaluator;

import calculator.evaluator.checker.Checker;
import calculator.evaluator.evaluable.DivisionByZero;
import calculator.evaluator.expression.ExpressionResult;
import calculator.evaluator.expression.ExpressionStatus;
import calculator.evaluator.inputhandler.InputNormalizer;
import calculator.evaluator.tokenizer.Token;
import calculator.evaluator.tree.Tree;
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
    private static double result;
    private static Tree expressionTree;

    /**
     * Take the user input, evaluate and apply the final result into an extern
     * objects (ExpressionResult - from this same package).
     */
    public static void setExpressionResult(String express,
                                           ExpressionResult setOn) {
        // set values first (prevent not changed attributes if expresion fails)
        String normalized = InputNormalizer.normalize(express);
        setOn.setStatus(ExpressionStatus.OK);
        setOn.setExpression(normalized);
        setOn.setResult(0.0);
        setOn.setErrorRange(defaultErrorRange);

        List<Token> expressionTokenList = InputNormalizer
            .strToTokenList(normalized);
        // if secure expression check fails, stop function
        if (handleChecks(expressionTokenList, setOn)) return;

        expressionTree = new Tree(expressionTokenList);

        try {
            result = expressionTree.evaluate();
            setOn.setResult(result);
        } catch (DivisionByZero e) {
            setOn.setErrorRange(e.getIndexRange());
            setOn.setStatus(ExpressionStatus.ERR_DIVISION_BY_ZERO);
        }
    }

    /**
     * Do all the check stuff in a separated function + set status for each of
     * them.
     */
    private static boolean handleChecks(List<Token> exprList,
                                        ExpressionResult setOn) {
        if (exprList.isEmpty())
            setOn.setStatus(ExpressionStatus.ERR_EMPTY_EXPRESSION);
        else if (Checker.invalidToken(exprList, setOn))
            setOn.setStatus(ExpressionStatus.ERR_INVALID_TOKEN);
        else if (Checker.missingParenthesis(exprList, setOn))
            setOn.setStatus(ExpressionStatus.ERR_MISSING_PAREN);
        else if (Checker.tokenProbablyMissing(exprList, setOn))
            setOn.setStatus(ExpressionStatus.ERR_MISSING_TOKEN);
        else if (Checker.invalidOperPos(exprList, setOn))
            setOn.setStatus(ExpressionStatus.ERR_OPER_AT_SIDES);
        else return false;

        return true;
    }
}
