package calculator.evaluator.inputhandler;

/**
 * Convert the user input from an unexpected format to a Token Parseable one
 *
 * <p>
 * All the job is done using the StringBufferHandler class and it's static
 * methods/attributes. This class will only slice the input into chars and
 * handle the char kind insertion (space, parenthesis, operator, other).
 * </p>
 */
public class InputNormalizer {

    /**
     * Normalize the input using the Singleton pattern (read the
     * StringBufferHandler docstring)
     */
    public static String normalize(String input) {
        // avoid useless work by returning when empty
        if (input.isEmpty()) return input;
        // prepare strBuf instance
        StringBufferHandler.prepareInstance();
        // foreach char
        for (char c : input.toCharArray()) {
            switch (c) {
                // if is space
                case ' ':
                    StringBufferHandler.handleSpacePushing();
                    break;
                // if is parenthesis
                case '(':
                case ')':
                    StringBufferHandler.handleParenPushing(c);
                    break;
                // if is operator
                case '+':
                case '-':
                case '/':
                case '*':
                case '^':
                    StringBufferHandler.handleOperPushing(c);
                    break;
                // else
                default:
                    StringBufferHandler.handleOtherPushing(c);
            }
        }
        // return the final object as String
        return StringBufferHandler.getBufferAsString();
    }
}
