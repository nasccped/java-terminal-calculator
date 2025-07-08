package calculator.evaluator;

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
    OK,
    WELCOME,
    ERR_EMPTY_EXPRESSION,
    TEST_CASE;

    public boolean isOk() { return this == OK; }
    public boolean isWelcome() { return this == WELCOME; }

    public String unwrapMessage() {
        switch (this) {
            case OK:
                return "Is everythin OK with this expression";
            case WELCOME:
                return "Welcome! You can use ':quit' to exit this program";
            case ERR_EMPTY_EXPRESSION:
                return "This is a test...";
        }
        return "FALLBACK STRING";
    }
}
