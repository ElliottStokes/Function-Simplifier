package org.FunctionSimplifier.ReversePolishNotation.Operators;

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
    public Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        Variable leftNodeVariable = leftNode.getVariable();
        return new LeafNode(new Variable(leftNodeVariable.getLabel(),
                leftNodeVariable.getConstant(),
                leftNodeVariable.getExponent() * rightNode.getVariable().getConstant()));
    }

    public String toString() {
        return "^";
    }
}
