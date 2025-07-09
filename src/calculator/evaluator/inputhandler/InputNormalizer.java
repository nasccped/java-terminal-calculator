package calculator.evaluator.inputhandler;

import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Convert inputs to an easy-read format (String -> better String, better
 * String -> List<String>)
 *
 * <p>
 * The StringBufferHandler works only with the 'normalize' function. This one
 * is basically char pushing handling.
 *
 * The 'normalizedToList' function is targeted to already normalized String. It
 * can turn char sequence in separeted Token-parseable char sequences:
 * "31 - (21 * 32.3)" -> ["31", "-", "(", "21", "*", "32.3", ")"].
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

    /**
     * Convert a normalized input (String) to an easy-read token sequence
     * (String List)
     */
    public static List<String> normalizedToList(String input) {
        LinkedList<String> list = new LinkedList<>();
        StringBuffer buffer = new StringBuffer();
        for (char c : input.toCharArray()) {
            switch (c) {
                case ' ':
                    list.add(buffer.toString());
                    buffer.setLength(0);
                    break;
                case '+':
                case '/':
                case '*':
                case '^':
                case '(':
                case ')':
                    list.add(buffer.toString());
                    list.add(String.format("%c", c));
                    buffer.setLength(0);
                    break;
                default:
                    buffer.append(c);
            }
        }
        if (!buffer.isEmpty()) list.add(buffer.toString());
        return list.stream()
            .filter(element -> !element.isEmpty())
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
