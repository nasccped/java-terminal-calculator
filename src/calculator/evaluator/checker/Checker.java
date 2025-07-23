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
    public static boolean invalidParenthesis(List<Token> input, ExpressionResult setOn) {
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
}
