package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;

public class OpenBracket extends Operator {
    private final int PRIORITY = 99;

    public OpenBracket() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    @Override
    protected Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        return leftNode;
    }

    public String toString() {
        return "(";
    }
}
