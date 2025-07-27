package calculator.evaluator.evaluable;

/**
 * Get the Token Tree range when a error occurs (usefull to track division
 * by 0).
 */
public interface Indexable {
    public int[] getIndexRange();
}
