package org.FunctionSimplifier.ReversePolishNotation.Operators;

public class Multiply implements Operator {
    private final int PRIORITY = 3;

    public Multiply () { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return "*";
    }
}
