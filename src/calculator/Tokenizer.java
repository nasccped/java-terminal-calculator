package calculator;

public class Tokenizer {
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
        EMPTY_TOKEN;

        protected static TokenType from(String input) {
            if (input.length() == 0) return EMPTY_TOKEN;
            if (input.length() == 1) {
                switch (input.charAt(0)) {
                    case '(': return OPEN_PAREN_TOKEN;
                    case ')': return CLOSE_PAREN_TOKEN;
                    case '+': return PLUS_TOKEN;
                    case '-': return MINUS_TOKEN;
                    case '*': return MULTIPLY_TOKEN;
                    case '/': return DIVIDE_TOKEN;
                    case '^': return POWER_TOKEN;
                }
            }

            TokenType tkn = INT_TOKEN;

            for (char c : input.toCharArray()) {
                if ((c < '0' || c > '9') && c != '.') return INVALID_TOKEN;
                if (c == '.' && tkn == FLOAT_TOKEN) return INVALID_TOKEN;
                if (c == '.') tkn = FLOAT_TOKEN;
            }
            return tkn;
        }
    }

    protected static TokenType tokenTypeFrom(String input) {
        return TokenType.from(input);
    }
}
