package org.FunctionSimplifier.ReversePolishNotation.Operators;

import java.util.Stack;

public class OperatorStack {
    private Stack<Operator> operatorStack;

    public OperatorStack() {
        operatorStack = new Stack<>();
    }

    public void push(Operator op) {
        this.operatorStack.push(op);
    }
}
