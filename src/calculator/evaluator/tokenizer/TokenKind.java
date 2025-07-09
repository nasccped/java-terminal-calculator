package calculator.evaluator.tokenizer;

/**
 * Token tag for a given input (one per element)
 */
public enum TokenKind {
    POS_INT,
    NEG_INT,
    POS_FLOAT,
    NEG_FLOAT,
    ADD_SIGN,
    SUB_SIGN,
    MUL_SIGN,
    DIV_SIGN,
    POW_SIGN,
    OPN_PAREN,
    CLS_PAREN,
    EMPTY_TOKEN,
    INVALID_TOKEN;

    /**
     * Builds the TokenKind based on single char constraint inputs, such as
     * math operators
     */
    private static TokenKind fromSingleChar(char c) {
        switch (c) {
            case '+': return ADD_SIGN;
            case '-': return SUB_SIGN;
            case '/': return DIV_SIGN;
            case '*': return MUL_SIGN;
            case '^': return POW_SIGN;
            case '(': return OPN_PAREN;
            case ')': return CLS_PAREN;
            case ' ': return EMPTY_TOKEN;
        }
        return (c < '0' || c > '9')
            ? INVALID_TOKEN
            : POS_INT;
    }

    /**
     * Builds the TokenKind based on multi char constraint inputs, such as
     * numbers
     */
    private static TokenKind fromCharSequence(char[] seq) {
        // default token as positive integer
        TokenKind token = POS_INT;
        for (int i = 0; i < seq.length; i++) switch (seq[i]) {
            case '.':
                // if dot is first/last char
                if (i == 0 || i == seq.length - 1) return INVALID_TOKEN;
                // if token not integer (already float = double dot input)
                if (token != POS_INT && token != NEG_INT) return INVALID_TOKEN;
                // update token with ternary
                token = token == POS_INT ? POS_FLOAT : NEG_FLOAT;
                break;
            case '-':
                // if '-' not in beginning
                if (i > 0) return INVALID_TOKEN;
                // update token
                token = NEG_INT;
                break;
            // othercase
            default:
                // if not digit
                if (seq[i] < '0' || seq[i] > '9') return INVALID_TOKEN;
        }
        return token;
    }

    /**
     * Return the TokenKind by calling a constructor function based on the
     * input length
     */
    public static TokenKind from(String value) {
        return value.isEmpty()
            ? EMPTY_TOKEN
            : value.length() == 1
                ? fromSingleChar(value.charAt(0))
                : fromCharSequence(value.toCharArray());
    }
}
