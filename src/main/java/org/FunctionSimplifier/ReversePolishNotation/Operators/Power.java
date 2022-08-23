package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;

public class Power implements Operator {
    private final int PRIORITY = 1;

    public Power() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    @Override
    public Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        return null;
    }

    @Override
    public Node evaluate(BranchNode leftNode, BranchNode rightNode) {
        return null;
    }

    @Override
    public Node evaluate(LeafNode leftNode, BranchNode rightNode) {
        return null;
    }

    @Override
    public Node evaluate(BranchNode leftNode, LeafNode rightNode) {
        return null;
    }

    @Override
    public Node evaluate(Node leftNode, Node rightNode) {
        return null;
    }

    public String toString() {
        return "^";
    }
}
