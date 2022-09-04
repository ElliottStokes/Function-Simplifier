package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;

public abstract class Operator {
    public abstract int getPriority();

    protected Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

    protected Node evaluate(BranchNode leftNode, BranchNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

    protected Node evaluate(LeafNode leftNode, BranchNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

    protected Node evaluate(BranchNode leftNode, LeafNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

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
}
