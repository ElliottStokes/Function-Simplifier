package org.FunctionSimplifier.ReversePolishNotation.Operators;

public class Divide implements Operator {
    private final int PRIORITY = 2;

    public Divide () { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return "/";
    }
}
