package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.BasicArithmetic.Multiplication;
import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;

public class Multiply extends Operator {
    private final int PRIORITY = 3;

    public Multiply () { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    @Override
    protected Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        Function result = new Multiplication(leftNode.getVariable(), rightNode.getVariable()).evaluate();
        if (result.isVariable())
            return new LeafNode(result.getVariable());
        else
            return new BranchNode(this, leftNode, rightNode);
    }

    @Override
    protected Node evaluate(LeafNode leftNode, BranchNode rightNode) {
        if (rightNode.getOperator() instanceof OpenBracket)
            return applyDistributiveLaw(leftNode, rightNode);
        else
            return new BranchNode(this, leftNode, rightNode);
    }

    @Override
    protected Node evaluate(BranchNode leftNode, LeafNode rightNode) {
        if (leftNode.getOperator() instanceof OpenBracket)
            return applyDistributiveLaw(rightNode, leftNode);
        else
            return new BranchNode(this, leftNode, rightNode);
    }

    private Node applyDistributiveLaw(LeafNode leafNode, BranchNode node) {
        if (node.getLeftNode() instanceof LeafNode)
            node.setLeftNode(evaluate(leafNode, (LeafNode) node.getLeftNode()));
        else
            node.setLeftNode(applyDistributiveLaw(leafNode, (BranchNode) node.getLeftNode()));

        if (node.getRightNode().toString().equals(")"))
            return node.getLeftNode();

        if (node.getRightNode() instanceof LeafNode)
            node.setRightNode(evaluate(leafNode, (LeafNode) node.getRightNode()));
        else
            node.setRightNode(applyDistributiveLaw(leafNode, (BranchNode) node.getRightNode()));

        return node;
    }

    public String toString() {
        return "*";
    }
}
