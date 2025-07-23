package calculator.evaluator.checker;

import calculator.evaluator.tokenizer.Token;
import calculator.evaluator.tokenizer.TokenKind;

import java.util.stream.Collectors;
import java.util.List;


/**
 * Preparatory checks for normalized input (paren count, token precedence, ...)
 */
public class Checker {

    /**
     * Get indexes of the firs ivalid token in a given list, else return empty
     * array
     */
    public static int[] invalidToken(List<Token> input) {
        return input
            .stream()
            .filter(Token::isInvalid)
            .findFirst()
            .map(Token::getIndexRange)
            .orElse(new int[0]);
    }

    /**
     * Get indexes of invalid parenthesis group
     */
    public static int[] invalidParenthesis(List<Token> input) {
        List<Token> parens =input
                .stream()
                .filter(v ->
                    v.getKind() == TokenKind.OPN_PAREN
                    || v.getKind() == TokenKind.CLS_PAREN)
                .collect(Collectors.toList());
        if (parens.isEmpty()) return new int[0];
        int parenCount = 0;
        int[] range = {
            0, parens.get(parens.size() - 1).getEndInd()
        };
        boolean isIncreasing;
        for (Token tk : parens) {
            if (tk.getKind() == TokenKind.OPN_PAREN) {
                parenCount++;
                isIncreasing = true;
            }
            else {
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
        return parenCount == 0 ? new int[0] : range;
    }
}
