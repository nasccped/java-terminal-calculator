package calculator.evaluator.tree;

import calculator.evaluator.tokenizer.Token;
import java.util.List;

/**
 * Partitionate Token lists to be push into the tree
 */
class ListPartition {

    protected Token mid;
    protected List<Token> left;
    protected List<Token> right;

    protected ListPartition(List<Token> input) {
        // single token input (numeric)
        if (input.size() == 1) {
            this.mid = input.get(0);
            this.left = null;
            this.right = null;
            return;
        }

        int parenDepth = 0,
            lastAddOrSub = -1,
            lastMulOrDiv = -1,
            lastPow = -1;

        // loop tag (can be break within the switch block)
        forloop:
        for (int i = input.size() - 1; i >= 0; i--)
        switch (input.get(i).getKind()) {
            case OPN_PAREN:
                parenDepth++;
                break;
            case CLS_PAREN:
                parenDepth--;
                break;
            case ADD_SIGN:
            case SUB_SIGN:
                if (parenDepth == 0 && lastAddOrSub == -1) {
                    lastAddOrSub = i;
                    break forloop;
                }
                break;
            case MUL_SIGN:
            case DIV_SIGN:
                if (parenDepth == 0 && lastMulOrDiv == -1) lastMulOrDiv = i;
                break;
            case POW_SIGN:
                if (parenDepth == 0 && lastPow == -1) lastPow = i;
                break;
        }
        boolean parenContained =
            lastAddOrSub == -1
            && lastMulOrDiv == -1
            && lastPow == -1;
        if (parenDepth > 0) {
            this.mid = input.get(0);
            this.left = null;
            this.right = input.subList(1, input.size());
            return;
        } else if (parenDepth < 0 || parenContained) {
            this.mid = input.get(input.size() - 1);
            this.left = input.subList(0, input.size() - 1);
            this.right = null;
            return;
        }

        // get mid index (based on math oper priority (INVERSE))
        int targetMidIndex =
            lastAddOrSub >= 0 ? lastAddOrSub :
            lastMulOrDiv >= 0 ? lastMulOrDiv :
            lastPow;

        this.mid = input.get(targetMidIndex);
        this.left = input.subList(0, targetMidIndex);
        this.right = input.subList(targetMidIndex + 1, input.size());
    }
}
