package org.FunctionSimplifier.ReversePolishNotation.Operators;

public class CloseBracket implements Operator {
    private final int PRIORITY = 99;

    public CloseBracket() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return ")";
    }
}
