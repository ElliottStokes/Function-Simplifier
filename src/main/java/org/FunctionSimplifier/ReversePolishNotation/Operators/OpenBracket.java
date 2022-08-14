package org.FunctionSimplifier.ReversePolishNotation.Operators;

public class OpenBracket implements Operator {
    private final int PRIORITY = 99;

    public OpenBracket() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return "(";
    }
}
