package calculator.evaluator.evaluable;

import calculator.evaluator.tree.TreeNode;

/**
 * Exception that should be throwed when trying to divide a number by 0
 *
 * It also holds the TreeNode range to report the user where the expression
 * error occurs.
 */
public class DivisionByZero extends Exception implements Indexable {

    private int[] indexRange;

    public DivisionByZero(TreeNode currentNode) {
        super("");
        this.indexRange = currentNode.getIndexRange();
    }

    @Override
    public int[] getIndexRange() {
        return indexRange;
    }
}
