package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;
import org.FunctionSimplifier.Variable;

public class Power extends Operator {
    private final int PRIORITY = 1;

    public Power() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    @Override
    protected Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        Variable leftNodeVariable = leftNode.getVariable();
        Variable rightNodeVariable = rightNode.getVariable();
        if (rightNodeVariable.hasLabel())
            return new BranchNode(this, leftNode, rightNode);
        else
            return new LeafNode(new Variable(leftNodeVariable.getLabel(),
                leftNodeVariable.getConstant(),
                leftNodeVariable.getExponent() * rightNodeVariable.getConstant()));
    }

    public String toString() {
        return "^";
    }
}
