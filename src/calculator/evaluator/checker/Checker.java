package calculator.evaluator.checker;

import calculator.evaluator.tokenizer.Token;
import calculator.evaluator.tokenizer.TokenKind;
import calculator.evaluator.ExpressionResult;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;


/**
 * Preparatory checks for normalized input (paren count, token precedence, ...)
 */
public class Checker {

    /**
     * Get indexes of the firs ivalid token in a given list, else return empty
     * array
     */
    public static boolean invalidToken(List<Token> input, ExpressionResult setOn) {
        Optional<Token> invalid = input
            .stream()
            .filter(Token::isInvalid)
            .findFirst();
        if (invalid.isPresent()) {
            setOn.setErrorRange(invalid.get().getIndexRange());
            return true;
        }
        return false;
    }

    /**
     * Get indexes of invalid parenthesis group
     */
    public static boolean missingParenthesis(List<Token> input, ExpressionResult setOn) {
        List<Token> parens = input
            .stream()
            .filter(v ->
                v.getKind() == TokenKind.OPN_PAREN
                || v.getKind() == TokenKind.CLS_PAREN
            ).collect(Collectors.toList());

        if (parens.isEmpty()) return false;

        int parenCount = 0;
        int[] range = {
            0, parens.get(parens.size() - 1).getEndInd()
        };
        boolean isIncreasing;
        for (Token tk : parens) {
            if (tk.getKind() == TokenKind.OPN_PAREN) {
                parenCount++;
                isIncreasing = true;
            } else {
                parenCount--;
                isIncreasing = false;
            }

            if (parenCount < 0) {
                range[0] = tk.getStartInd();
                range[1] = tk.getEndInd();
                break;
            }
            if (parenCount == 1 && isIncreasing) range[0] = tk.getStartInd();
        }
        if (parenCount != 0) {
            setOn.setErrorRange(range);
            return true;
        }
        return false;
    }

    public static boolean invalidOperPos(List<Token> input, ExpressionResult setOn) {
        if (input.isEmpty()) return false;

        int[] range = new int[2];
        Token tk = input.get(input.size() - 1);

        switch (tk.getKind()) {
            case ADD_SIGN:
            case SUB_SIGN:
            case DIV_SIGN:
            case MUL_SIGN:
            case POW_SIGN:
                range[0] = tk.getStartInd();
                range[1] = tk.getEndInd();
                setOn.setErrorRange(range);
                return true;
        }

        tk = input.get(0);

        switch (tk.getKind()) {
            case POW_SIGN:
            case DIV_SIGN:
            case MUL_SIGN:
                range[0] = tk.getStartInd();
                range[1] = tk.getEndInd();
                setOn.setErrorRange(range);
                return true;
            case ADD_SIGN:
            case SUB_SIGN:
                if (input.size() == 1) {
                    range[0] = tk.getStartInd();
                    range[1] = tk.getEndInd();
                    setOn.setErrorRange(range);
                    return true;
                }
                if (input.get(1).getKind() != TokenKind.OPN_PAREN) {
                    range[0] = tk.getStartInd();
                    range[1] = tk.getEndInd();
                    setOn.setErrorRange(range);
                    return true;
                }
        }
        return false;
    }

    public static boolean tokenProbablyMissing(List<Token> input, ExpressionResult setOn) {
        if (input.size() < 2) return false;
        Token curr, prev;
        int[] range = new int[2];
        boolean isErr = false;
        for (int i = 1; i < input.size(); i++) {
            curr = input.get(i);
            prev = input.get(i - 1);
            // number followed by open parenthesis
            if (
                prev.isNumeric()
                && curr.getKind() == TokenKind.OPN_PAREN) isErr = true;
            // operator followed by close parenthesis
            else if (
                prev.isOper()
                && curr.getKind() == TokenKind.CLS_PAREN) isErr = true;
            // open parenthesis followed by close parenthesis (empty parenthesis)
            else if (
                prev.getKind() == TokenKind.OPN_PAREN
                && curr.getKind() == TokenKind.CLS_PAREN) isErr = true;
            // number followed by a number
            else if (
                prev.isNumeric() && curr.isNumeric()) isErr = true;
            // operator followed by an operator
            else if (
                curr.isOper() && prev.isOper()) isErr = true;

            // if any of the conditions above
            if (isErr) {
                range[0] = prev.getIndexRange()[0];
                range[1] = curr.getIndexRange()[1];
                setOn.setErrorRange(range);
                return true;
            }
        }
        return false;
    }
}
