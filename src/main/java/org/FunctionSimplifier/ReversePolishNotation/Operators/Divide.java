package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.BasicArithmetic.Division;
import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;
import org.FunctionSimplifier.Variable;

public class Divide implements Operator {
    private final int PRIORITY = 2;

    public Divide () { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    @Override
    public Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        Function result = new Division(leftNode.getVariable(), rightNode.getVariable()).evaluate();
        if (result.isVariable())
            return new LeafNode(new Variable(result.toString()));
        else
            return new BranchNode(this, leftNode, rightNode);
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
        if (leftNode.toString().equals("("))
            return applyDistributiveLaw(rightNode, leftNode);
        else
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

    private Node applyDistributiveLaw(LeafNode leafNode, BranchNode node) {
        if (node.getLeftNode() instanceof LeafNode)
            node.setLeftNode(evaluate((LeafNode) node.getLeftNode(), leafNode));
        else
            node.setLeftNode(applyDistributiveLaw(leafNode, (BranchNode) node.getLeftNode()));

        if (node.getRightNode().toString().equals(")"))
            return node.getLeftNode();

        if (node.getRightNode() instanceof LeafNode)
            node.setRightNode(evaluate((LeafNode) node.getRightNode(), leafNode));
        else
            node.setRightNode(applyDistributiveLaw(leafNode, (BranchNode) node.getRightNode()));

        return node;
    }

    public String toString() {
        return "/";
    }
}
