package calculator.evaluator.tree;

import calculator.evaluator.evaluable.DivisionByZero;
import calculator.evaluator.evaluable.Evaluable;
import calculator.evaluator.evaluable.NonEvaluableToken;
import calculator.evaluator.tokenizer.Token;
import java.util.List;

/**
 * Expression tree. Most of the code is implemented in TreeNode.java. Take a
 * look at it.
 */
public class Tree implements Evaluable {

    final TreeNode root;

    public Tree(List<Token> input) {
        this.root = new TreeNode(input);
    }

    @Override
    public double evaluate() throws NonEvaluableToken, DivisionByZero {
        try {
            return root.evaluate();
        } catch (Exception e) {
            throw e;
        }
    }
}
