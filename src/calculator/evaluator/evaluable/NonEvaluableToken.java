package calculator.evaluator.evaluable;

import calculator.evaluator.tokenizer.Token;

/**
 * Exception to report evaluation failure when trying to eval an unallowed
 * token! It will stop the program execution but is never expected to happen
 * at runtime, instead, just show me (developer) where and why the error
 * happened.
 */
public class NonEvaluableToken extends RuntimeException {
    public NonEvaluableToken(Token token) {
        super("Only Int and Float tokens can be evaluate to a double value (index, type: "
              + token.toString()
              + ")");
    }
}
