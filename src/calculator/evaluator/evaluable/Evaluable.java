package calculator.evaluator.evaluable;

/**
 * Evaluable interface: can unwrap double values from tokens, trees or its
 * nodes! Can also throw runtime Exceptions like:
 *
 * - NonEvaluableToken: single Tokens that can't be evaluated (parenthesis or
 *                      math operators - it should be evaluated as a Node)
 * - DivisionByZero: when the divisor of given the expression is zero
 */
public interface Evaluable {
    public double evaluate() throws NonEvaluableToken, DivisionByZero;
}
