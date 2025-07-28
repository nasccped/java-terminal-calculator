package calculator.evaluator.inputhandler;

import calculator.evaluator.tokenizer.Token;
import calculator.evaluator.tokenizer.TokenKind;

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
        if (input.isEmpty()) return input;
        StringBufferHandler.prepareInstance();
        for (char c : input.toCharArray()) switch (c) {
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
            default:
                StringBufferHandler.handleOtherPushing(c);
        }
        return StringBufferHandler.getBufferAsString();
    }

    /**
     * Convert a normalized input (String) to an easy-read token sequence
     * (String List)
     */
    public static List<Token> strToTokenList(String input) {
        LinkedList<Token> list = new LinkedList<>();
        StringBuffer buffer = new StringBuffer();
        int savedIndex = 0;
        String savedStr;
        for (int i = 0; i < input.length(); i++)
        switch (input.charAt(i)) {
            case ' ':
                savedStr = buffer.toString();
                list.add(new Token(TokenKind.from(savedStr),
                                   savedStr,
                                   savedIndex));
                buffer.setLength(0);
                savedIndex = i + 1;
                break;
            case '+':
            case '/':
            case '*':
            case '^':
            case '(':
            case ')':
                savedStr = buffer.toString();
                list.add(new Token(TokenKind.from(savedStr),
                                   savedStr,
                                   savedIndex));
                savedStr = String.format("%c", input.charAt(i));
                list.add(new Token(TokenKind.from(savedStr), savedStr, i));
                buffer.setLength(0);
                savedIndex = i + 1;
                break;
            default:
                buffer.append(input.charAt(i));
        }
        if (!buffer.isEmpty())
            list.add(new Token(TokenKind.from(buffer.toString()),
                               buffer.toString(),
                               savedIndex));
        return list
            .stream()
            .filter(element -> !element.isEmpty())
            .collect(Collectors.toList());
    }
}
