package org.FunctionSimplifier;

import org.FunctionSimplifier.BasicArithmetic.Multiplication;

public class Function {
    private String functionString;
    private Object body;

    public Function() {
        this.body = new Variable(0);
        this.functionString = this.body.toString();
    }

    public Function(int value) {
        this.body = new Variable(value);
        this.functionString = this.body.toString();
    }

    public Function(double value) {
        this.body = new Variable(value);
        this.functionString = this.body.toString();
    }

    public Function(Variable variable) {
        this.body = variable;
        this.functionString = this.body.toString();
    }

    public Function(Variable variable, String funcString) {
        this.body = variable;
        this.functionString = funcString;
    }

    public Function(Multiplication multiplication) {
        this.body = multiplication;
        this.functionString = this.body.toString();
    }

    public Function(Multiplication multiplication, String funcString) {
        this.body = multiplication;
        this.functionString = funcString;
    }

    public void print() {
        // Print the formatted function
        System.out.println(this.functionString);
    }

    public boolean isVariable() {
        return this.body instanceof Variable;
    }

    public Object getBody() {
        return this.body;
    }

    public String toString() {
        return this.functionString;
    }
}
