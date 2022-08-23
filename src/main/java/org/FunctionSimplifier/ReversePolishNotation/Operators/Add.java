package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.BasicArithmetic.Addition;
import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;
import org.FunctionSimplifier.Variable;

public class Add implements Operator {
    private final int PRIORITY = 4;

    public Add() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return "+";
    }

    public Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        Function result = new Addition(leftNode.getVariable(), rightNode.getVariable()).evaluate();
        if (result.isVariable())
            return new LeafNode(new Variable(result.toString()));
        else
            return new BranchNode(this, leftNode, rightNode);
    }

    public Node evaluate(BranchNode leftNode, BranchNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

    public Node evaluate(LeafNode leftNode, BranchNode rightNode) {
        return new BranchNode(this, leftNode, rightNode);
    }

    public Node evaluate(BranchNode leftNode, LeafNode rightNode) {
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
