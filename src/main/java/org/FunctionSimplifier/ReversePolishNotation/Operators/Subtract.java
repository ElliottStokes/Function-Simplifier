package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.BasicArithmetic.Subtraction;
import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;
import org.FunctionSimplifier.Variable;

public class Subtract extends Operator {
    private final int PRIORITY = 4;

    public Subtract() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    @Override
    protected Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        Function result = new Subtraction(leftNode.getVariable(), rightNode.getVariable()).evaluate();
        if (result.isVariable())
            return new LeafNode(new Variable(result.toString()));
        else
            return new BranchNode(this, leftNode, rightNode);
    }

    public String toString() {
        return "-";
    }
}
