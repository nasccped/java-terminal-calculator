package calculator.evaluator.inputhandler;

/**
 * Handle a single StringBuffer using a to normalize the user input
 *
 * <p>
 * This function is fully static. It'll have a StringBuffer which is
 * responsible for the input normalization storing.
 *
 * It can't handle a full String. Instead, it'll handle the char pushing based
 * on it's expression kind (operator, parenthesis, numeric, space). The String
 * slicing and class method calling is a InputNormalizer responsability!
 *
 * There only 6 out-visible (protected) methods:
 * - prepareInstance: initialize or reset the main object (StringBuffer)
 * - getBufferAsString: return the main object as the expected type (String)
 * - handleSpacePushing: when the char to be pushed is a space
 * - handleOperPushing: when the char to be pushed is a math operator
 * - handleParenPushing: when the char to be pushed is a parenthesis
 * - handleOtherPushing: when the char to be pushed is any other kind (numeric
 *   or invalid)
 *
 * Is this class really necessary?
 *
 * The user can sometimes introduce unexpected inputs. This inputs should be
 * handled using a common human sense, like "2 - 1":
 *
 * - This is a simple expression that results in '1'. When passing this input,
 *   the program evaluator expects to take the first value (2) and run the
 *   given operation (-) using the value on right side (1). That's the common
 *   sense!
 *
 * But, what about " - 1":
 *
 * - The common sense say to get the left value, decide the operation and use
 *   the right value also, but there's no left value. This is an invalid
 *   expression!
 *
 * Finally, look at "-1":
 *
 * - This isn't an invalid expression! This is just a negative integer, a valid
 *   expression actually.
 *
 * We need to handle the entire user input:
 * - this "3 +4 *2"          become this "3 + 4 * 2"
 * - this "5- 2/ 3"          become this "5 - 2 / 3"
 * - this "2 *- 3"           become this "2 * -3"
 * - this "2 * ( - 1.5 + 2)" become this "2 * (-1.5 + 2)"
 * </p>
 */
class StringBufferHandler {

    // used to represent an empty char when trying to get previous one or doing
    // lookup
    private static final char EMPTY_CHAR_SIGN = '\0';
    private static StringBuffer strBuf;
    // stores the latest char of penultimate word in the buffer
    private static char pivot;

    /**
     * Prepare inner singleton
     */
    protected static void prepareInstance() {
        if (strBuf == null) strBuf = new StringBuffer();
        else strBuf.setLength(0);
    }

    protected static String getBufferAsString() { return strBuf.toString(); }

    protected static void handleSpacePushing() {
        switch (getLatestChar()) {
            // if buffer is empty
            case EMPTY_CHAR_SIGN:
            // handle space pushing on next char
            case ' ':
            case '+':
            case '-':
            case '(':
            case ')':
                return;
            default:
                pushChar(' ');
        }
    }

    protected static void handleOperPushing(char oper) {
        switch (getLatestChar()) {
            case EMPTY_CHAR_SIGN:
            case ' ':
            case '(':
                break;
            default:
                pushChar(' ');
        }
        pushChar(oper);
    }

    protected static void handleParenPushing(char paren) {
        // get the pivot (check the class beginning)
        pivot = reverseCharLookup();
        switch (getLatestChar()) {
            case '/':
            case '*':
            case '^':
                pushChar(' ');
                break;
            case '+':
            case '-':
                if (pivot != EMPTY_CHAR_SIGN) pushChar(' '); // push a gap
                break;
            case ' ':
                if (paren == ')') removeCharAt(getLength() - 1);
                break;
            case EMPTY_CHAR_SIGN:
                break;
            default:
                if (paren == '(') pushChar(' ');
        }
        pushChar(paren);
    }

    protected static void handleOtherPushing(char c) {
        // get pivot (check the class beginning)
        pivot = reverseCharLookup();
        switch (getLatestChar()) {
            case ')':
            case '/':
            case '^':
            case '=':
            case '*':
            case '+':
                pushChar(' '); // add gap
                break;
            case '-':
                if (pivot != '-'
                    && pivot != '+'
                    && pivot != '*'
                    && pivot != '/'
                    && pivot != '('
                    && pivot != EMPTY_CHAR_SIGN) pushChar(' ');
        }
        // different approach for '=' signs (also invalid)
        if (getLatestChar() != ' ' && c == '=') pushChar(' ');
        pushChar(c);
    }

    private static boolean isEmpty() { return strBuf.isEmpty(); }

    /**
     * Returns the latest char from buffer object (EMPTY_CHAR_SIGN if buffer is
     * empty).
     */
    private static char getLatestChar() {
        return isEmpty()
            ? EMPTY_CHAR_SIGN
            : strBuf.charAt(getLength() - 1);
    }

    private static void pushChar(char c) { strBuf.append(c); }

    private static int getLength() { return strBuf.length(); }

    private static void removeCharAt(int index) {
        strBuf.deleteCharAt(index);
    }

    /**
     * Get the latest char of the previous word
     *
     * <p>
     * It's used to decide if a gap should be added between the previous char
     * and the current one.
     * </p>
     */
    private static char reverseCharLookup() {
        boolean stopOnNext = false;
        for (int ind = getLength() - 1; ind >= 0; ind--)
            if (getCharAt(ind) == ' ') stopOnNext = true;
            else if (stopOnNext) return getCharAt(ind);
        return EMPTY_CHAR_SIGN;
    }

    private static char getCharAt(int index) {
        return strBuf.charAt(index);
    }
}
