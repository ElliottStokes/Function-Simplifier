package org.FunctionSimplifier;

import org.FunctionSimplifier.BasicArithmetic.Addition;
import org.FunctionSimplifier.BasicArithmetic.Arithmetic;
import org.FunctionSimplifier.BasicArithmetic.Multiplication;

public class Function {
    private Object body;

    public Function() {
        this.body = new Variable(0);
    }

    public Function(int value) {
        this.body = new Variable(value);
    }

    public Function(double value) {
        this.body = new Variable(value);
    }

    public Function(Variable variable) {
        this.body = variable;
    }

    public Function(Multiplication multiplication) {
        this.body = multiplication;
    }

    public Function(Addition addition) {
        this.body = addition;
    }

    public Function(Function function) {
        this.body = function;
    }


    public boolean isVariable() {
        return this.body instanceof Variable;
    }
    public Variable getVariable() {
        if (this.isVariable())
            return (Variable) this.body;
        else
            return null;
    }

    public Object getBody() {
        return this.body;
    }

    public String toString() {
        return this.body.toString();
    }

    public Function evaluate() {
        if (this.body instanceof Variable)
            return new Function((Variable) this.body);
        else if (this.body instanceof Multiplication)
            return ((Multiplication) this.body).evaluate();
        else if (this.body instanceof Addition)
            return ((Addition) this.body).evaluate();
        else
            return new Function(0);
    }

    public Variable getRightVariable() {
        return ((Arithmetic) this.body).getRight();
    }

    public Variable getLeftVariable() {
        return ((Arithmetic) this.body).getLeft();
    }
}