package calculator.evaluator.tokenizer;

/**
 * Token kind/value holder
 *
 * <p>
 * This class holds the token kind for the given String symbol.
 * It have other features (getters) when dealing with tree parsing.
 * </p>
 */
public class Token {

    private final TokenKind kind;
    private final String value;
    private final int startInd;
    private final int endInd;

    public Token(TokenKind token, String value, int startIndex) {
        this.kind = token;
        this.value = value;
        this.startInd = startIndex;
        this.endInd = startIndex + value.length();
    }

    public boolean isEmpty() { return kind == TokenKind.EMPTY_TOKEN; }

    public boolean isInvalid() {
        return kind == TokenKind.EMPTY_TOKEN
            || kind == TokenKind.INVALID_TOKEN;
    }

    public boolean isOper() {
        switch (kind) {
            case ADD_SIGN:
            case SUB_SIGN:
            case DIV_SIGN:
            case MUL_SIGN:
            case POW_SIGN:
                return true;
            default:
                return false;
        }
    }

    public TokenKind getKind() { return kind; }

    public String getValue() { return value; }

    public int getStartInd() { return startInd; }

    public int getEndInd() { return endInd; }

    public int[] getIndexRange() {
        int[] result = new int[2];
        result[0] = startInd;
        result[1] = startInd + value.length();
        return result;
    }

    public boolean isNumeric() {
        switch (kind) {
            case POS_INT:
            case NEG_INT:
            case POS_FLOAT:
            case NEG_FLOAT:
                return true;
            default: return false;
        }
    }

    /**
     * Unwrap the value String when calling 'toString'
     */
    private String valueUnwrapper() {
        if (value == null) return "";
        StringBuffer buffer = new StringBuffer("('");
        buffer.append(value);
        buffer.append("')");
        return buffer.toString();
    }

    @Override
    public String toString() {
        return String.format("[%d, %s]", startInd, kind + valueUnwrapper());
    }
}
