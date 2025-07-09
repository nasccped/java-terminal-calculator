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
    private final TokenKind token;
    private final String value;

    public Token(TokenKind token, String value) {
        this.token = token;
        this.value = value;
    }

    public TokenKind getKind() { return token; }

    public String getValue() { return value; }

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
    public String toString() { return token + valueUnwrapper(); }
}
