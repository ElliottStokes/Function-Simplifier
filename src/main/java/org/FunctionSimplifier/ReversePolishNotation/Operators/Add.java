package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.BasicArithmetic.Addition;
import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;
import org.FunctionSimplifier.Variable;

public class Add extends Operator {
    private final int PRIORITY = 4;

    public Add() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return "+";
    }

    @Override
    protected Node evaluate(LeafNode leftNode, LeafNode rightNode) {
        Function result = new Addition(leftNode.getVariable(), rightNode.getVariable()).evaluate();
        if (result.isVariable())
            return new LeafNode(new Variable(result.toString()));
        else
            return new BranchNode(this, leftNode, rightNode);
    }
}
