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

    /**
     * Representation of an empty char
     *
     * <p>
     * This value is used to represent the latest/pivot char when the
     * StringBuffer is empty. It's a good approach because unlike C, taking a
     * String input from the Command Line will just return a String with no
     * '\0' terminator (even if passing this literal value).
     * </p>
     */
    private static final char EMPTY_CHAR_SIGN = '\0';

    /**
     * Our singleton inner object
     *
     * <p>
     * It will store our String data, and then, return the final data as
     * String.
     * </p>
     */
    private static StringBuffer strBuf;

    /**
     * Stores the latest char of the PENULTIM word on the buffer
     *
     * <p>
     * It's required to check if a space between the previous char and the
     * current one should (or not) be added (or remove, also)
     * </p>
     */
    private static char pivot;

    /**
     * Function to prepare inner object Singleton state
     */
    protected static void prepareInstance() {
        // if not initialized
        if (strBuf == null) strBuf = new StringBuffer();
        // else, reset
        else strBuf.setLength(0);
    }

    protected static String getBufferAsString() { return strBuf.toString(); }

    protected static void handleSpacePushing() {
        // switch on latest buffer char
        switch (getLatestChar()) {
            // if buffer is empty
            case EMPTY_CHAR_SIGN:
            // if latest is already a space
            case ' ':
            // chars that should be gap checked on next char pushing
            case '+':
            case '-':
            case '(':
            case ')':
                // get out the function
                return;
            // else case
            default:
                pushChar(' ');
        }
    }

    protected static void handleOperPushing(char oper) {
        // switch the latest char to check if space gap should be added
        switch (getLatestChar()) {
            // if buffer is empty
            case EMPTY_CHAR_SIGN:
            // latest is already space
            case ' ':
            // latest is open pare (don't turn this "(-1 ..." into
            // this "( -1 ..."
            case '(':
                break;
            // else case, add gap
            default:
                pushChar(' ');
        }
        // push the current operator char
        pushChar(oper);
    }

    protected static void handleParenPushing(char paren) {
        // get the pivot (check the class beginning)
        pivot = reverseCharLookup();
        // switch on latest char
        switch (getLatestChar()) {
            // case plus or minus sign
            case '+':
            case '-':
                // if the pivot isn't empty (it means the current buffer isn't
                // '+' or '-')
                if (pivot != EMPTY_CHAR_SIGN) pushChar(' '); // push a gap
                break;
            // if latest is space
            case ' ':
                // and the curchar is closing parenthesis, remove the space:
                // turn this "... + 23 )" into this "... + 23)"
                if (paren == ')') removeCharAt(getLength() - 1);
                break;
        }
        // push the paren
        pushChar(paren);
    }

    protected static void handleOtherPushing(char c) {
        // get pivot (check the class beginning)
        pivot = reverseCharLookup();
        switch (getLatestChar()) {
            // if latest is any of these:
            case ')':
            case '/':
            case '^':
            case '=':
            case '*':
            case '+':
                pushChar(' '); // add gap
                break;
            // if latest plus or minus
            case '-':
                // check if pivot is gap reasonable
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
        // gradually decrease the buffer index
        for (int ind = getLength() - 1; ind >= 0; ind--) {
            // if space found, enable stop and continue
            if (getCharAt(ind) == ' ') {
                stopOnNext = true;
                continue;
            }
            // return only when stop is enabled
            if (stopOnNext) return getCharAt(ind);
        }
        // if outside loop (ind goes out of scope (-1))
        return EMPTY_CHAR_SIGN;
    }

    private static char getCharAt(int index) {
        return strBuf.charAt(index);
    }
}
