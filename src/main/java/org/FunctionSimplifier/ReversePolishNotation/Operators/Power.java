package org.FunctionSimplifier.ReversePolishNotation.Operators;

public class Power implements Operator {
    private final int PRIORITY = 1;

    public Power() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return "^";
    }
}
