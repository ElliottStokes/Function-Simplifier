package org.FunctionSimplifier.ReversePolishNotation.Operators;

public class Subtract implements Operator {
    private final int PRIORITY = 4;

    public Subtract() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return "-";
    }
}
