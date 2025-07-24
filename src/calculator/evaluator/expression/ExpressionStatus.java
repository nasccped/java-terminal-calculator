package calculator.evaluator.expression;

/**
 * Stores the status of the given expression:
 *
 * <p>
 * When everything goes right, this enum is useless, otherwise, the user need
 * to know what they did wrong.
 *
 * This enum will store possible error causes, such as:
 * - division by 0 ("2 / (1 - 1)")
 * - invalid input ("2 + NaN")
 * - any other error prone...
 *
 * It also have method utilities:
 * - isOk: expression is value
 * - isWelcome: for welcome message loading
 * - unwrapMessage: get some String message based on the enum itself
 *
 * NOTE: this enum and its functions will be used by the UI class, so it must
 *       be public!
 *
 * </p>
 */
public enum ExpressionStatus {
    ERR_EMPTY_EXPRESSION,
    ERR_MISSING_PAREN,
    ERR_INVALID_TOKEN,
    ERR_OPER_AT_SIDES,
    ERR_MISSING_TOKEN,
    OK,
    WELCOME;

    public boolean isOk() { return this == OK; }
    public boolean isWelcome() { return this == WELCOME; }

    public String unwrapMessage() {
        return
            this == ERR_EMPTY_EXPRESSION  ? "Empty expression" :
            this == ERR_INVALID_TOKEN     ? "Invalid token (non numeric/operator)" :
            this == ERR_MISSING_PAREN     ? "Open/Close parenthesis is missing" :
            this == ERR_OPER_AT_SIDES     ? "Expr. can't start or end with operator (only '+' and '-')" :
            this == ERR_MISSING_TOKEN     ? "A token ('42', '-', '(', ...) is probably missing" :
            this == OK                    ? "Is everythin OK with this expression" :
            this == WELCOME               ? "Welcome! You can use ':quit' to exit this program" :
            "FALLBACK String (unexpected behavior - I must fix it!)";
    }
}
