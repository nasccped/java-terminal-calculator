package calculator;

// type used to return a Token-Collection from a String Stream
import java.util.LinkedList;

/**
 * Responsible for convert a String type in a List of Tokens
 *
 * <p>
 * This class will do the mentioned convertion by using an inner enum and
 * the following methods:
 * - `getTokenFromChar`: return a `TokenType` from a specific char (private)
 * - `getTokenFromString`: return a `TokenType` from a specific String
 *   (private)
 * - `getTokenListFromString`: return a `LinkedList<TokenType>` from a String
 *   (public)
 * </p>
 */
public class Tokenizer {
    /**
     * All available token types (includes non-valid and empty
     * (even if no used))
     */
    public static enum TokenType {
        OPEN_PAREN_TOKEN,
        CLOSE_PAREN_TOKEN,
        INT_TOKEN,
        FLOAT_TOKEN,
        PLUS_TOKEN,
        MINUS_TOKEN,
        MULTIPLY_TOKEN,
        DIVIDE_TOKEN,
        POWER_TOKEN,
        INVALID_TOKEN,
        EMPTY_TOKEN
    }

    /**
     * Get a TokenType by a given char. Usefull when handling input of single
     * char constraint such as math ops (+, -, *, /).
     */
    private static TokenType getTokenFromChar(char input) {
        // switch by all obviously cases
        switch (input) {
            case '(': return TokenType.OPEN_PAREN_TOKEN;
            case ')': return TokenType.CLOSE_PAREN_TOKEN;
            case '+': return TokenType.PLUS_TOKEN;
            case '-': return TokenType.MINUS_TOKEN;
            case '*': return TokenType.MULTIPLY_TOKEN;
            case '/': return TokenType.DIVIDE_TOKEN;
            case '^': return TokenType.POWER_TOKEN;
        }
        // test if in numerical range
        if (input < '0' || input > '9') return TokenType.INVALID_TOKEN;
        return TokenType.INT_TOKEN;
    }

    /**
     * Get a TokenType by a given String. Usefull when handling multiple char
     * input constraint such as numerical values (23, 457, 3.14, ...).
     *
     * <p>
     * NOTE: Even if this function parse a String value to a
     * TokenType, we'll **EVER** handle single value input!
     *
     * We'll EVER handle this:
     * - "243"
     * - "23.1"
     *
     * And NEVER handle this:
     * - "2+1"
     * - "(2)"
     * </p>
     */
    private static TokenType getTokenFromString(String input) {
        // handle easiest scenarios:
        // if empty
        if (input.isEmpty()) return TokenType.EMPTY_TOKEN;
        // if a single char (use char function)
        if (input.length() == 1) return getTokenFromChar(input.charAt(0));
        // if starts with '.' (invalid float)
        if (input.startsWith(".") || input.endsWith(".")) return TokenType.INVALID_TOKEN;
        // token begins as INT
        TokenType tkn = TokenType.INT_TOKEN;
        // fore every char in our input
        for (char c : input.toCharArray()) {
            // if not in numerical range (and not a dot)
            if ((c < '0' || c > '9') && c != '.')
                return TokenType.INVALID_TOKEN;
            // if is a dot but token already is a FLOAT (it means double dot
            // value: "3.1.4")
            if (c == '.' && tkn == TokenType.FLOAT_TOKEN)
                return TokenType.INVALID_TOKEN;
            // if is a dot
            if (c == '.') tkn = TokenType.FLOAT_TOKEN;
        }
        return tkn;
    }

    /**
     * Get a TokenType List by a given String. Usefull when handling entire
     * expressions ("2+1", "2*3.14*17^2", "1/0" ( .-.)   )
     */
    protected
    static LinkedList<TokenType> getTokenListFromString(String input) {
        // gen a new list + StringBuffer
        LinkedList<TokenType> tknList = new LinkedList<>();
        StringBuffer curInp = new StringBuffer();
        // for evary char in our input
        for (char c : input.toCharArray()) {
            switch (c) {
                // if space, handle by the strbuf state
                case ' ':
                    if (curInp.length() == 0) continue;
                    tknList.add(getTokenFromString(curInp.toString()));
                    curInp.setLength(0);
                    break;
                // if is single char input
                case '+':
                case '-':
                case '/':
                case '*':
                case '^':
                case '(':
                case ')':
                    // in case of non empty strbuf
                    if (curInp.length() > 0) {
                        tknList.add(getTokenFromString(curInp.toString()));
                        curInp.setLength(0);
                    }
                    // add token by char
                    tknList.add(getTokenFromChar(c));
                    break;

                // else (numeric or invalid: "3.14", "23", "2invalid1")
                default:
                    curInp.append(c);
                    break;
            }
        }
        // if strbuf not empty: add generated token to list
        if (curInp.length() > 0)
            tknList.add(getTokenFromString(curInp.toString()));
        return tknList;
    }
}
