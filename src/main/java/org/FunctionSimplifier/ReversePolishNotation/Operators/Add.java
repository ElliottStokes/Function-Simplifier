package org.FunctionSimplifier.ReversePolishNotation.Operators;

public class Add implements Operator {
    private final int PRIORITY = 4;

    public Add() { }

    @Override
    public int getPriority() {
        return this.PRIORITY;
    }

    public String toString() {
        return "+";
    }
}
