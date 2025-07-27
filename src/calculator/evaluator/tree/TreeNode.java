package calculator.evaluator.tree;

import calculator.evaluator.evaluable.DivisionByZero;
import calculator.evaluator.evaluable.Evaluable;
import calculator.evaluator.evaluable.Indexable;
import calculator.evaluator.evaluable.NonEvaluableToken;
import calculator.evaluator.tokenizer.Token;
import java.util.List;

/**
 * A node that populate the Tree object.
 */
public class TreeNode implements Evaluable, Indexable {

    private Token item;
    private TreeNode itemLeft;
    private TreeNode itemRight;

    /**
     * Create a nodes recursively from a given input by using the ListPartition
     * class.
     */
    protected TreeNode(List<Token> input) {
        ListPartition lp = new ListPartition(input);
        this.itemLeft = null;
        this.itemRight = null;
        this.item = lp.mid;
        if (lp.left != null)
            this.itemLeft = lp.left.isEmpty()
                ? null
                : new TreeNode(lp.left);
        if (lp.right != null)
            this.itemRight = lp.right.isEmpty()
                ? null
                : new TreeNode(lp.right);
    }

    /**
     * Evaluate current node based on the item's TokenKind (if number, get as
     * double, else, run the operation).
     */
    @Override
    public double evaluate() throws DivisionByZero, NonEvaluableToken {
        switch (item.getKind()) {
            case OPN_PAREN: return itemRight.evaluate();
            case CLS_PAREN: return itemLeft.evaluate();
            case ADD_SIGN:
                return
                    (itemLeft == null ? 0 : itemLeft.evaluate())
                    + itemRight.evaluate();
            case SUB_SIGN:
                return itemLeft == null
                    ? -itemRight.evaluate()
                    : itemLeft.evaluate() - itemRight.evaluate();
            case MUL_SIGN: return itemLeft.evaluate() * itemRight.evaluate();
            case DIV_SIGN:
                double right = itemRight.evaluate();
                if (right == 0.0) throw new DivisionByZero(this);
                return itemLeft.evaluate() / right;
            case POW_SIGN: return Math.pow(itemLeft.evaluate(),
                                           itemRight.evaluate());
            default: return item.evaluate();
        }
    }

    /**
     * Returns the index range from the expanded current Node (most left index
     * + most right index).
     */
    @Override
    public int[] getIndexRange() {
        int[] result = item.getIndexRange();
        if (itemLeft != null) result[0] = itemLeft.getIndexRange()[0];
        if (itemRight != null) result[1] = itemRight.getIndexRange()[1];
        return result;
    }
}
