package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;

public class OpenBracket implements Operator {
    private final int PRIORITY = 99;

    public OpenBracket() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    @Override
    public Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        return leftNode;
    }

    @Override
    public Node evaluate(BranchNode leftNode, BranchNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

    @Override
    public Node evaluate(LeafNode leftNode, BranchNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

    @Override
    public Node evaluate(BranchNode leftNode, LeafNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

    @Override
    public Node evaluate(Node leftNode, Node rightNode) {
        if (leftNode instanceof LeafNode && rightNode instanceof LeafNode)
            return evaluate((LeafNode) leftNode, (LeafNode) rightNode);
        else if (leftNode instanceof LeafNode)
            return evaluate((LeafNode) leftNode, (BranchNode) rightNode);
        else if (rightNode instanceof LeafNode)
            return evaluate((BranchNode) leftNode, (LeafNode) rightNode);
        else
            return evaluate((BranchNode) leftNode, (BranchNode) rightNode);
    }

    public String toString() {
        return "(";
    }
}
